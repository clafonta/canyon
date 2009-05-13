package org.tll.canyon.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetType;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.AssetTypeManager;
import org.tll.canyon.webapp.util.MessageUtil;


public class AssetTypeFormController extends BaseFormController {
    private AssetTypeManager assetTypeManager = null;
    
    public void setAssetTypeManager(AssetTypeManager assetTypeManager) {
        this.assetTypeManager = assetTypeManager;
    }

    public AssetTypeFormController() {
        setCommandName("assetType");
        setCommandClass(AssetType.class);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        
        
        AssetType assetType = null;

        if (!StringUtils.isEmpty(id)) {
            assetType = assetTypeManager.getAssetType(id);
        } else {
            
            assetType = new AssetType();
        
        }

        return assetType;
    }

    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AssetType assetType = (AssetType) command;
        boolean isNew = (assetType.getId() == null);
        Locale locale = request.getLocale();
        String successView = null; //"editAsset.html?view=true&id=" + assetType.getAssetDetailId();
        if (request.getParameter("delete") != null) {
            // TODO: is this the best way to delete?
            //assetType.setAssetDetail(null);
            //assetType.setAssetDetailId(null);
            //assetTypeManager.saveAssetType(assetType);
            assetTypeManager.removeAssetType(assetType.getId().toString());
            MessageUtil.saveMessage(request, getText("assetType.deleted", locale));
            successView = "assetRoles.html"; //"editAsset.html?view=true&id=" + assetType.getAssetDetailId();
        } else {
            assetTypeManager.saveAssetType(assetType);
            String key = (isNew) ? "assetType.added" : "assetType.updated";
            MessageUtil.saveMessage(request, getText(key, locale)); 
            successView = "editAssetType.html?id="+assetType.getId();
            
        }
        
        return new ModelAndView(new RedirectView(successView));
    }


}
