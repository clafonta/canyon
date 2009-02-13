package org.tll.canyon.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.tll.canyon.Constants;
import org.tll.canyon.model.AssetAccessRequestStatus;
import org.tll.canyon.service.AssetAccessRequestStatusManager;


public class AssetAccessRequestStatusController implements Controller {
    private final Log log = LogFactory.getLog(AssetAccessRequestStatusController.class);
    private AssetAccessRequestStatusManager assetAccessRequestStatusManager = null;

    public void setAssetAccessRequestStatusManager(AssetAccessRequestStatusManager assetAccessRequestStatusManager) {
        this.assetAccessRequestStatusManager = assetAccessRequestStatusManager;
    }

    /**
     * Supports filtered list view of <code>AssetAccessRequestStatus</code>. 
     * 
     * @see AssetAccessRequestStatus#getId()
     */
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        String assetAccessRequestId = request.getParameter("assetAccessRequestId");

        AssetAccessRequestStatus assetAccessRequestStatus = null;
        if (assetAccessRequestId != null) {
            assetAccessRequestStatus = new AssetAccessRequestStatus();
            assetAccessRequestStatus.setAssetAccessRequestId(new Long(assetAccessRequestId));
        }
        // populate object with request parameters
        BeanUtils.populate(assetAccessRequestStatus, request.getParameterMap());

        List<AssetAccessRequestStatus> assetAccessRequestStatuss = assetAccessRequestStatusManager
                .getAssetAccessRequestStatusList(assetAccessRequestStatus);

        return new ModelAndView("assetAccessRequestStatusList", Constants.ASSETACCESSREQUESTSTATUS_LIST,
                assetAccessRequestStatuss);
    }
}
