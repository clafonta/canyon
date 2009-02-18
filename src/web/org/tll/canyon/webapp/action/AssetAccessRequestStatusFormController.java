package org.tll.canyon.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.tll.canyon.model.AssetAccessRequestStatus;
import org.tll.canyon.service.AssetAccessRequestStatusManager;
import org.tll.canyon.webapp.util.MessageUtil;


public class AssetAccessRequestStatusFormController extends BaseFormController {
    private AssetAccessRequestStatusManager assetAccessRequestStatusManager = null;

    public void setAssetAccessRequestStatusManager(AssetAccessRequestStatusManager assetAccessRequestStatusManager) {
        this.assetAccessRequestStatusManager = assetAccessRequestStatusManager;
    }

    public AssetAccessRequestStatusFormController() {
        setCommandName("assetAccessRequestStatus");
        setCommandClass(AssetAccessRequestStatus.class);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String assetAccessRequestId = request.getParameter("assetAccessRequestId");        
        AssetAccessRequestStatus assetAccessRequestStatus = null;

        
        if (!StringUtils.isEmpty(id)) {
            // EDIT/UPDATE 
            assetAccessRequestStatus = assetAccessRequestStatusManager.getAssetAccessRequestStatus(id);
        } else {
            // NEW - Let's associate to the appropriate AssetAccessRequest
            assetAccessRequestStatus = new AssetAccessRequestStatus();
            assetAccessRequestStatus.setAssetAccessRequestId(new Long(assetAccessRequestId));
        }

        return assetAccessRequestStatus;
    }

    /**
     * 
     */
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        AssetAccessRequestStatus assetAccessRequestStatus = (AssetAccessRequestStatus) command;      
        Long assetAccessRequestId = assetAccessRequestStatus.getAssetAccessRequestId();
        boolean isNew = (assetAccessRequestStatus.getId() == null);
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            assetAccessRequestStatusManager.removeAssetAccessRequestStatus(assetAccessRequestStatus.getId().toString());
            MessageUtil.saveMessage(request, getText("assetAccessRequestStatus.deleted", locale));
        } else {
            assetAccessRequestStatusManager.saveAssetAccessRequestStatus(assetAccessRequestStatus);
            String key = (isNew) ? "assetAccessRequestStatus.added" : "assetAccessRequestStatus.updated";
            MessageUtil.saveMessage(request, getText(key, locale));            
        }
        return new ModelAndView("redirect:editAssetAccessRequest.html?id=" + assetAccessRequestId.toString());
    }
}
