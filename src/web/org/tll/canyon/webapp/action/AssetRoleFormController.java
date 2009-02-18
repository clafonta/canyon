package org.tll.canyon.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetRole;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.AssetRoleManager;
import org.tll.canyon.webapp.util.MessageUtil;


public class AssetRoleFormController extends BaseFormController {
    private AssetRoleManager assetRoleManager = null;
    private AssetDetailManager assetDetailManager = null;

    public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
        this.assetDetailManager = assetDetailManager;
    }
    public void setAssetRoleManager(AssetRoleManager assetRoleManager) {
        this.assetRoleManager = assetRoleManager;
    }

    public AssetRoleFormController() {
        setCommandName("assetRole");
        setCommandClass(AssetRole.class);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String assetDetailId = request.getParameter("assetDetailId");
        
        AssetRole assetRole = null;

        if (!StringUtils.isEmpty(id)) {
            assetRole = assetRoleManager.getAssetRole(id);
        } else {
            AssetDetail assetDetail = assetDetailManager.getAssetDetail(assetDetailId);
            assetRole = new AssetRole();
            assetRole.setAssetDetail(assetDetail);
        }

        return assetRole;
    }

    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AssetRole assetRole = (AssetRole) command;
        boolean isNew = (assetRole.getId() == null);
        Locale locale = request.getLocale();
        String successView = "editAssetDetail.html?view=true&id=" + assetRole.getAssetDetailId();
        if (request.getParameter("delete") != null) {
            // TODO: is this the best way to delete?
            //assetRole.setAssetDetail(null);
            //assetRole.setAssetDetailId(null);
            //assetRoleManager.saveAssetRole(assetRole);
            assetRoleManager.removeAssetRole(assetRole.getId().toString());
            MessageUtil.saveMessage(request, getText("assetRole.deleted", locale));
        } else {
            assetRoleManager.saveAssetRole(assetRole);
            String key = (isNew) ? "assetRole.added" : "assetRole.updated";
            MessageUtil.saveMessage(request, getText(key, locale)); 
        }
        
        return new ModelAndView(new RedirectView(successView));
    }


}
