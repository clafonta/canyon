package org.tll.canyon.webapp.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.tll.canyon.Constants;
import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.model.AssetAccessRequestStatus;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.EmployeeInfo;
import org.tll.canyon.model.StatusType;
import org.tll.canyon.service.AssetAccessRequestManager;
import org.tll.canyon.service.AssetAccessRequestStatusManager;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.EmployeeInfoManager;
import org.tll.canyon.service.MailEngine;


/**
 * @author chad.lafontaine
 *
 */
public class AssetAccessRequestFormController extends BaseFormController {
    private AssetAccessRequestStatusManager assetAccessRequestStatusManager = null;
    private AssetAccessRequestManager assetAccessRequestManager = null;
    private AssetDetailManager assetDetailManager = null;
    private EmployeeInfoManager employeeInfoManager = null;

    private final Log log = LogFactory.getLog(AssetAccessRequestFormController.class);
    public void setAssetAccessRequestStatusManager(AssetAccessRequestStatusManager assetAccessRequestStatusManager) {
        this.assetAccessRequestStatusManager = assetAccessRequestStatusManager;
    }
    
    public void setEmployeeInfoManager(EmployeeInfoManager employeeInfoManager) {
        this.employeeInfoManager = employeeInfoManager;
    }

    public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
        this.assetDetailManager = assetDetailManager;
    }

    public void setAssetAccessRequestManager(AssetAccessRequestManager assetAccessRequestManager) {
        this.assetAccessRequestManager = assetAccessRequestManager;
    }

    public AssetAccessRequestFormController() {
        setCommandName("assetAccessRequest");
        setCommandClass(AssetAccessRequest.class);
    }

    public void setMailEngine(MailEngine mailEngine) {
        this.mailEngine = mailEngine;
    }

    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }

    /**
     * There are 2 places of origination for this request:
     * <pre>
     * 1) New Access Request, key value is AssetDetail ID. 
     * 2) Old access request, key value is AssetAccessRequesst ID.
     * </pre>
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        String id = request.getParameter("id");
        String assetDetailId = request.getParameter("assetDetailId");

        AssetDetail assetDetail = null;
        AssetAccessRequest assetAccessRequest = null;

        if (!StringUtils.isEmpty(id)) {
            // 1. This is an EDIT MODE request 
            assetAccessRequest = assetAccessRequestManager.getAssetAccessRequest(id);
            assetDetail = assetAccessRequest.getAssetRole().getAssetDetail();
        } else {

            // 1. This is a NEW MODE request.            
            assetAccessRequest = new AssetAccessRequest(new EmployeeInfo());            
            assetDetail = assetDetailManager.getAssetDetail(assetDetailId);
        }
        
        request.setAttribute(Constants.ASSETDETAIL_KEY, assetDetail);
        request.setAttribute(Constants.ASSETACCESSREQUEST_KEY, assetAccessRequest);
        return assetAccessRequest;
    }

    /**
     * Performs the following checks and actions per new/edited <code>AssetAccessRequestForm</code>
     * creation. 
     * <pre>
     *  1) Persist/delete item per action. 
     *  2) If 'new' or 'changed', then persist information and 
     *  3) Send email to appropriate/needed approvers
     * </pre>
     */
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
          
        String employeeEmail = request.getParameter("employeeEmail");
        String assetAccessRequestId = request.getParameter("id");
        String assetDetailId = request.getParameter("assetDetailId");
        String assetRoleId = request.getParameter("assetRoleId");
        String completeFlag = request.getParameter("complete");        
        AssetAccessRequest assetAccessRequestForm = null;
        EmployeeInfo employeeInfo = null;
        // NEW
        boolean isNew = true;
        if(assetAccessRequestId!=null && assetAccessRequestId.length()>0){
            isNew = false;
        }
        // COMPLETE
        boolean flaggedAsComplete = false;
        if(completeFlag!=null && completeFlag.trim().equalsIgnoreCase("true")){
            flaggedAsComplete = true;
        }
        
        Locale locale = request.getLocale();
        if (!isNew && request.getParameter("delete") != null) {
            assetAccessRequestManager.removeAssetAccessRequest(assetAccessRequestId);
            saveMessage(request, getText("assetAccessRequest.deleted", locale));
            
        } else {

            // 0) VALIDATE EMPLOYEE INFORMATION            
            try {
                employeeInfo =  this.employeeInfoManager.getEmployeeInfoByEmailAddress(employeeEmail);
                if(employeeInfo==null){
                    throw new Exception("Invalid user");
                }
                
            } catch (Exception exception) {
                log.debug("No user information available for '"+employeeEmail+"'");
                this.saveError(request, getText("assetAccessRequest.invalidUserId", employeeEmail, locale));
                ModelAndView mv = new ModelAndView(this.getFormView());
                EmployeeInfo employeeInfoTemp = new EmployeeInfo();
                employeeInfoTemp.setEmployeeEmail(employeeEmail);
                assetAccessRequestForm = new AssetAccessRequest(employeeInfoTemp);                
                mv.addObject(Constants.ASSETACCESSREQUEST_KEY, assetAccessRequestForm);
                AssetDetail assetDetail = assetDetailManager.getAssetDetail(assetDetailId);
                mv.addObject(Constants.ASSETDETAIL_KEY, assetDetail);
                return mv;
            }
            
            // 1) NEW? If so, then by default, append needed approval from manager. 
            if (isNew) {
                assetAccessRequestForm = new AssetAccessRequest(employeeInfo);
                assetAccessRequestForm.setRequestCreateDate(new Date());                
                assetAccessRequestForm.setEmployeeUserId(employeeInfo.getEmployeeUserId());
                
                
            } else {
                assetAccessRequestForm = this.assetAccessRequestManager.getAssetAccessRequest(assetAccessRequestId);
                
            }
            

            // 2) VALIDATE REQUIRED INFO
            if (assetRoleId == null || assetRoleId.trim().length() == 0) {
                this.saveError(request, getText("assetAccessRequest.roleIsRequired", locale));
                ModelAndView mv = new ModelAndView(this.getFormView());
                mv.addObject(Constants.ASSETACCESSREQUEST_KEY, assetAccessRequestForm);
                AssetDetail assetDetail = assetDetailManager.getAssetDetail(assetDetailId);
                mv.addObject(Constants.ASSETDETAIL_KEY, assetDetail);
                return mv;
            } else {
                assetAccessRequestForm.setAssetRoleId(new Long(assetRoleId));
            }
            
            // 3) ENSURE/INFORM USER THAT REQUEST CAN'T BE FLAGGED AS COMPLETE IF NOT ALL APPROVALS ARE APPROVED
            // OR NEW.
            if (isNew && flaggedAsComplete){
                this.saveError(request, getText("assetAccessRequest.invalid.new.complete" , locale));
                ModelAndView mv = new ModelAndView(this.getFormView());
                mv.addObject(Constants.ASSETACCESSREQUEST_KEY, assetAccessRequestForm);
                AssetDetail assetDetail = assetDetailManager.getAssetDetail(assetDetailId);
                mv.addObject(Constants.ASSETDETAIL_KEY, assetDetail);
                return mv;
            }
            
            else if(!isNew && flaggedAsComplete){
                List<AssetAccessRequestStatus> items = assetAccessRequestForm.getAssetAccessRequestStatusList();
                boolean allApproved = true;
                if (items != null && items.size() > 0) {
                    for (Iterator<AssetAccessRequestStatus> iterator = items.iterator(); iterator.hasNext();) {
                        AssetAccessRequestStatus assetAccessRequestStatus = iterator.next();
                        if (!StatusType.COMPLETED.toString().equalsIgnoreCase(assetAccessRequestStatus.getApprovalStatus())) {
                            allApproved = false;
                            break;
                        }
                    }
                }
                if(!allApproved){                    
                    this.saveError(request, getText("assetAccessRequest.invalid.approvals.needed" , locale));
                    ModelAndView mv = new ModelAndView(this.getFormView());
                    mv.addObject(Constants.ASSETACCESSREQUEST_KEY, assetAccessRequestForm);
                    AssetDetail assetDetail = assetDetailManager.getAssetDetail(assetDetailId);
                    mv.addObject(Constants.ASSETDETAIL_KEY, assetDetail);
                    return mv;
                }
            }
            // 4) COMPLETE? 
            if (!isNew && flaggedAsComplete && !assetAccessRequestForm.isComplete()) {
                assetAccessRequestForm.setCompleteDate(new Date());
                assetAccessRequestForm.setComplete(true);
            } else if(!flaggedAsComplete){
                assetAccessRequestForm.setComplete(false);
                assetAccessRequestForm.setCompleteDate(null);
            }
            // 5) OK, IF WE REACH HERE, WE'RE ALL VALID. SAVE/UPDATE REQUEST           
            assetAccessRequestManager.saveAssetAccessRequest(assetAccessRequestForm);
            // 6) *** BEGIN ****
            // CORE NOTIFICATION PROCESS LOGIC
            this.assetAccessRequestStatusManager.processAssetAccessRequestState(new Long(assetAccessRequestForm.getId()).toString(), locale);
            // *** END ***
            String key = (isNew) ? "assetAccessRequest.added" : "assetAccessRequest.updated";
            saveMessage(request, getText(key, locale));            
        }
        return new ModelAndView(getSuccessView());
    }  

    

    

}
