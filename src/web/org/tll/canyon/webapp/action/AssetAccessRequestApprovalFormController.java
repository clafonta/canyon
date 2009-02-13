package org.tll.canyon.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.tll.canyon.Constants;
import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.model.AssetAccessRequestStatus;
import org.tll.canyon.service.AssetAccessRequestStatusManager;


/**
 * Handles asset request approval. 
 * 1. Display the request
 * 2.  
 * @author chad.lafontaine
 *
 */
public class AssetAccessRequestApprovalFormController extends BaseFormController {
    
    private AssetAccessRequestStatusManager assetAccessRequestStatusManager = null;    
  

    public AssetAccessRequestApprovalFormController() {
        setCommandName("assetAccessRequestStatus");
        setCommandClass(AssetAccessRequestStatus.class);
    }    

    public void setAssetAccessRequestStatusManager(AssetAccessRequestStatusManager assetAccessRequestStatusManager) {
        this.assetAccessRequestStatusManager = assetAccessRequestStatusManager;
    }
   

    /**
     * 
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        
        String trackId = request.getParameter("trackId");
        AssetAccessRequestStatus assetAccessRequestStatus = assetAccessRequestStatusManager.getAssetAccessRequestStatusByTrackId(trackId);  
        if(assetAccessRequestStatus== null){
          assetAccessRequestStatus = new AssetAccessRequestStatus();
          saveError(request, getText("assetAccessRequestStatus.invalid.request", request.getLocale()));
        }
        return assetAccessRequestStatus;
    }

    /**
     * Manages the following business logic:
     * <pre>
     * 1) Updates track ID.
     * 2) If all asset request approvals are complete and approved, then kicks off a request to asset procurement team (queue email).
     * </pre>
     * @see #sendNotificationToProcessorIfAllApprovalsAreMet(HttpServletRequest, AssetAccessRequestStatus)
     * @see AssetAccessRequest#getQueueEmail()
     */
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        AssetAccessRequestStatus assetAccessRequestStatusForm = (AssetAccessRequestStatus) command;
        AssetAccessRequestStatus assetAccessRequestStatus = this.assetAccessRequestStatusManager.getAssetAccessRequestStatus(new Long(assetAccessRequestStatusForm.getId()).toString()); 
        assetAccessRequestStatus.setApprovalStatus(assetAccessRequestStatusForm.getApprovalStatus());
        assetAccessRequestStatus.setApprovalEmailUniqueTrackId(null);
        this.assetAccessRequestStatusManager.saveAssetAccessRequestStatus(assetAccessRequestStatus);
        // *** BEGIN *** CORE NOTIFICATION FLOW LOGIC HANDLED HERE
        assetAccessRequestStatusManager.processAssetAccessRequestState((assetAccessRequestStatus.getAssetAccessRequestId()).toString(), request.getLocale());
        // *** END *****
        ModelAndView mv = new ModelAndView(getSuccessView());
        mv.addObject(Constants.ASSETACCESSREQUESTSTATUS_KEY, new AssetAccessRequestStatus());        
        saveMessage(request, getText("assetAccessRequestStatus.updated", request.getLocale()));
        
        return mv;
    }
}
