package org.tll.canyon.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mail.SimpleMailMessage;
import org.tll.canyon.dao.AssetAccessRequestStatusDao;
import org.tll.canyon.dao.AssetRoleDao;
import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.model.AssetAccessRequestStatus;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetRole;
import org.tll.canyon.model.EmployeeInfo;
import org.tll.canyon.model.StatusType;
import org.tll.canyon.service.AssetAccessRequestManager;
import org.tll.canyon.service.AssetAccessRequestStatusManager;
import org.tll.canyon.service.EmployeeInfoManager;
import org.tll.canyon.service.MailEngine;


/**
 * Contains basic persistence logic of 'status' for access approval/verification. In addition, 
 * contains main business logic for email notification processing. 
 *   
 * @author Chad Lafontaine
 * @see #processAssetAccessRequestState(String, Locale) 
 */
public class AssetAccessRequestStatusManagerImpl extends BaseManager implements AssetAccessRequestStatusManager {
    private AssetAccessRequestStatusDao dao = null;
    private AssetRoleDao assetRoleDao = null;
    private AssetAccessRequestManager assetAccessRequestManager = null;
    private EmployeeInfoManager employeeInfoManager = null;
    private MailEngine mailEngine = null;
    private SimpleMailMessage mailMessage = null;
    private MessageSource messageSource = null;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setAssetAccessRequestManager(AssetAccessRequestManager assetAccessRequestManager) {
        this.assetAccessRequestManager = assetAccessRequestManager;
    }

    public void setEmployeeInfoManager(EmployeeInfoManager employeeInfoManager) {
        this.employeeInfoManager = employeeInfoManager;
    }

    public void setMailEngine(MailEngine mailEngine) {
        this.mailEngine = mailEngine;
    }

    public void setMailMessage(SimpleMailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAssetAccessRequestStatusDao(AssetAccessRequestStatusDao dao) {
        this.dao = dao;
    }

    /**
     * @see org.tll.canyon.service.AssetAccessRequestStatusManager#getAssetAccessRequestStatuss(org.tll.canyon.model.AssetAccessRequestStatus)
     */
    public List<AssetAccessRequestStatus> getAssetAccessRequestStatusList(
            final AssetAccessRequestStatus assetAccessRequestStatus) {
        return dao.getAssetAccessRequestStatusList(assetAccessRequestStatus);
    }

    /**
     * @see org.tll.canyon.service.AssetAccessRequestStatusManager#getAssetAccessRequestStatus(String id)
     */
    public AssetAccessRequestStatus getAssetAccessRequestStatus(final String id) {
        return dao.getAssetAccessRequestStatus(new Long(id));
    }

    /**
     * @see org.tll.canyon.service.AssetAccessRequestStatusManager#saveAssetAccessRequestStatus(AssetAccessRequestStatus assetAccessRequestStatus)
     */
    public void saveAssetAccessRequestStatus(AssetAccessRequestStatus assetAccessRequestStatus) {
        dao.saveAssetAccessRequestStatus(assetAccessRequestStatus);
    }

    /**
     * @see org.tll.canyon.service.AssetAccessRequestStatusManager#removeAssetAccessRequestStatus(String id)
     */
    public void removeAssetAccessRequestStatus(final String id) {
        dao.removeAssetAccessRequestStatus(new Long(id));
    }

    /**
     * Gets assetAccessRequestStatus's information based on track ID
     * @param trackId
     * @see AssetAccessRequestStatus#getApprovalEmailUniqueTrackId()
     * @return assetAccessRequestStatus populated assetAccessRequestStatus object
     */
    public AssetAccessRequestStatus getAssetAccessRequestStatusByTrackId(final String trackId) {
        AssetAccessRequestStatus example = new AssetAccessRequestStatus();
        AssetAccessRequestStatus itemToReturn = null;
        example.setApprovalEmailUniqueTrackId(trackId);
        List<AssetAccessRequestStatus> list = dao.getAssetAccessRequestStatusList(example);
        if (list != null && list.size() == 1) {
            itemToReturn = (AssetAccessRequestStatus) list.get(0);
        } else {
            if (log.isDebugEnabled()) {
                if (list != null && list.size() > 0) {
                    log.warn("Too many AssetAccessRequestStatus items (count=" + list.size() + ") available with track id '"
                            + trackId + "'");
                } else {
                    log.debug("No AssetAccessRequestStatus items available with track id '" + trackId + "'");
                }
            } else {
                // 
            }
        }
        return itemToReturn;

    }

    /**
     * Contains business specific logic flow implementation for notification and update of status
     * per associated asset access request. 
     * 
     * @param assetAccessRequestId primary key identifier of <code>AssetAccessRequest</code>
     * @see org.tll.canyon.service.AssetAccessRequestStatusManager#processAssetAccessRequestState(String assetAccessRequestId)
     * @see org.tll.canyon.model.AssetAccessRequest
     */
    public void processAssetAccessRequestState(String assetAccessRequestId, Locale locale) {

        // Step 0)
        createDefaultRequiredAssetApprovalStatusRequest(assetAccessRequestId);
        // Step 1)
        createUniqueEmailTrackIdsPerRequestStatus(assetAccessRequestId);
        // Step 2)
        sendOutEmailNotificationsPerNeededApproval(assetAccessRequestId, locale);
        // Step 3)
        sendNotificationToProcessorIfAllApprovalsAreMet(assetAccessRequestId, locale);
        // Step 4)
        sendOutEmailNotificationOfAccessCreation(assetAccessRequestId, locale);
    }
    
    /**
     * Verifies that the access request has at least 1 associated approval status, the 
     * employee/person-making-request manager's approval status. 
     *  
     * @param assetAccessRequestId
     */
    private void createDefaultRequiredAssetApprovalStatusRequest(String assetAccessRequestId){
        AssetAccessRequest assetAccessRequest = this.assetAccessRequestManager.getAssetAccessRequest(assetAccessRequestId);                    
        EmployeeInfo employeeInfo = assetAccessRequest.getEmployeeInfo();
        boolean managerApprovalPresent = false;
        List<AssetAccessRequestStatus> items = assetAccessRequest.getAssetAccessRequestStatusList();
        if(items==null){
            items = new ArrayList<AssetAccessRequestStatus>();
        }
        for (Iterator<AssetAccessRequestStatus> iterator = items.iterator(); iterator.hasNext();) {
            AssetAccessRequestStatus assetAccessRequestStatus = iterator.next();
            if(assetAccessRequestStatus.getApprovalUserId()!=null 
                    && assetAccessRequestStatus.getApprovalUserId().trim().equalsIgnoreCase(employeeInfo.getManagerUserId())){
                managerApprovalPresent = true;
                break;
            }
        }
        if(!managerApprovalPresent){
                    
            AssetAccessRequestStatus assetAccessRequestStatus = new AssetAccessRequestStatus();
            assetAccessRequestStatus.setApprovalStatus(StatusType.PENDING.toString());
            assetAccessRequestStatus.setAssetAccessRequestId(assetAccessRequest.getId());
            assetAccessRequestStatus.setApprovalUserId(employeeInfo.getManagerUserId());
            items.add(assetAccessRequestStatus);
            saveAssetAccessRequestStatus(assetAccessRequestStatus);
            this.assetAccessRequestManager.saveAssetAccessRequest(assetAccessRequest);
            log.debug("Default manager approval request status added to assetAccessRequestId="+assetAccessRequestId);
        }else {
            log.debug("No manager approval request status added to assetAccessRequestId="+assetAccessRequestId);                 
        }
    
    }

    /**
     * Visits each <code>AssetAccessRequestStatus</code> object associated to <code>AssetAccessRequest</code>. 
     * Each <code>AssetAccessRequestStatus</code> object with non-complete status is assigned a unique email track 
     * identifier. Only <code>AssetAccessRequestStatus</code> items that do <strong>not</strong> have 
     * a status of complete are assigned a track id. 
     * 
     * @param assetAccessRequest
     * @see AssetAccessRequestStatus#getApprovalEmailUniqueTrackId()
     * @return
     */
    private void createUniqueEmailTrackIdsPerRequestStatus(String assetAccessRequestId) {
        AssetAccessRequest assetAccessRequest = this.assetAccessRequestManager.getAssetAccessRequest(assetAccessRequestId);
        List<AssetAccessRequestStatus> items = assetAccessRequest.getAssetAccessRequestStatusList();

        if (items != null && items.size() > 0) {
            for (Iterator<AssetAccessRequestStatus> iterator = items.iterator(); iterator.hasNext();) {
                AssetAccessRequestStatus assetAccessRequestStatus = iterator.next();
                if (!StatusType.COMPLETED.toString().equalsIgnoreCase(assetAccessRequestStatus.getApprovalStatus())) {
                    assetAccessRequestStatus.setApprovalEmailUniqueTrackId(UUID.randomUUID().toString());
                    this.saveAssetAccessRequestStatus(assetAccessRequestStatus);
                }
            }
        } else {
            log.debug("No unique track email IDs created for asset access request of id '" + assetAccessRequest.getId()
                    + "' ");
        }

    }

    /**
     * Iterates through each required approval and sends out email notification. Business logic
     * is as follows:
     * <pre>
     * 1) Only send email to NON-approved/complete status.  
     * </pre>
     * @param request
     * @param assetAccessRequest
     */
    private void sendOutEmailNotificationsPerNeededApproval(String assetAccessRequestId, Locale locale) {
        AssetAccessRequest assetAccessRequest = this.assetAccessRequestManager.getAssetAccessRequest(assetAccessRequestId);
        List<AssetAccessRequestStatus> items = assetAccessRequest.getAssetAccessRequestStatusList();
        StringBuffer msg = new StringBuffer();

        MessageSourceAccessor text = new MessageSourceAccessor(messageSource, locale);
        String approvalLink = null;
        if (items != null && items.size() > 0) {
            for (Iterator<AssetAccessRequestStatus> iterator = items.iterator(); iterator.hasNext();) {
                AssetAccessRequestStatus assetAccessRequestStatus = iterator.next();
                if (!StatusType.COMPLETED.toString().equalsIgnoreCase(assetAccessRequestStatus.getApprovalStatus())) {
                    msg = new StringBuffer();
                    approvalLink = text.getMessage("assetAccessRequest.approval.link")
                            + assetAccessRequestStatus.getApprovalEmailUniqueTrackId();
                    msg.append(text.getMessage("assetAccessRequest.approval.blurb", new Object[] { approvalLink }));
                    String sendToEmailID = assetAccessRequestStatus.getApprovalUserId();
                    EmployeeInfo employeeInfo = employeeInfoManager.getEmployeeInfo(sendToEmailID);
                    mailMessage.setTo(employeeInfo.getEmployeeEmail());
                    String subject = text.getMessage("assetAccessRequest.email.subject");
                    mailMessage.setSubject(subject);
                    mailMessage.setText(msg.toString());
                    mailEngine.send(mailMessage);
                }
            }
        } else {
            log.debug("No email notifications per needed approval sent for assetAccessRequestId = " + assetAccessRequestId);
        }
    }

    /**
     * 
     * @param request
     * @param assetAccessRequestStatus
     * @see AssetAccessRequestStatus#getAssetAccessRequest()
     * @see AssetAccessRequest#getQueueEmail()
     */
    private void sendNotificationToProcessorIfAllApprovalsAreMet(String assetAccessRequestId, Locale locale) {
        AssetAccessRequest assetAccessRequest = this.assetAccessRequestManager.getAssetAccessRequest(assetAccessRequestId);
        List<AssetAccessRequestStatus> items = assetAccessRequest.getAssetAccessRequestStatusList();
        boolean allApproved = true;
        if (items != null && items.size() > 0) {
            for (Iterator<AssetAccessRequestStatus> iterator = items.iterator(); iterator.hasNext();) {
                AssetAccessRequestStatus item = (AssetAccessRequestStatus) iterator.next();
                if (!(StatusType.APPROVED.toString().equalsIgnoreCase(item.getApprovalStatus()))) {
                    allApproved = false;
                    break;
                }
            }
        }
        // HACK?
        // Objects are fairly normalized and this is how we step through
        // the Business/Object relationships to get the appropriate 
        // email address. Can we say, lot's of little SQL calls? Yum.        
        Long assetRoleId = assetAccessRequest.getAssetRoleId();
        AssetRole assetRole = this.assetRoleDao.getAssetRole(assetRoleId);
        AssetDetail assetDetail = assetRole.getAssetDetail();
//        String administrationEmail = assetDetail.getAssetAdminTeamEmail();
//        if (allApproved) {
//            try {
//
//                StringBuffer msg = new StringBuffer();
//                MessageSourceAccessor text = new MessageSourceAccessor(messageSource, locale);
//                String editLink = text.getMessage("assetAccessRequest.edit.link") + assetAccessRequestId;
//                msg.append(text.getMessage("assetAccessRequest.edit.blurb", new Object[] { editLink }));
//                mailMessage.setTo(administrationEmail);
//                String subject = text.getMessage("assetAccessRequest.email.subject");
//                mailMessage.setSubject(subject);
//                mailMessage.setText(msg.toString());
//                mailEngine.send(mailMessage);
//                log.debug("Notification to processor sent to '" + administrationEmail
//                        + "'. All status checks have been approved.");
//            } catch (Exception e) {
//                log.error("Unable to send email. Email: '" +administrationEmail +"'. assetAccessRequestId: " + assetAccessRequest.getId(),e );
//            }
//        } else {
//            log.debug("No notification sent to '" + administrationEmail + "'. Approval(s) is still needed.");
//        }
    }

    /**
     * 
     * @param request
     * @param assetAccessRequest
     */
    private void sendOutEmailNotificationOfAccessCreation(String assetAccessRequestId, Locale locale) {
        AssetAccessRequest assetAccessRequest = this.assetAccessRequestManager.getAssetAccessRequest(assetAccessRequestId);

        if (assetAccessRequest.isComplete()) {

            StringBuffer msg = new StringBuffer();
            MessageSourceAccessor text = new MessageSourceAccessor(messageSource, locale);
            EmployeeInfo employeeInfo = employeeInfoManager.getEmployeeInfo(assetAccessRequest.getEmployeeUserId());
            if (employeeInfo != null) {
                msg.append(text.getMessage("assetAccessRequest.complete.blurb"));
                msg.append(text.getMessage("assetAccessRequest.complete.result", new Object[] {
                        assetAccessRequest.getAssetRole().getAssetDetail().getAssetName(),
                        assetAccessRequest.getAssetRole().getName() }));
                mailMessage.setTo(employeeInfo.getEmployeeEmail());
                String subject = text.getMessage("assetAccessRequest.email.subject");
                mailMessage.setSubject(subject);
                mailMessage.setText(msg.toString());
                mailEngine.send(mailMessage);
            } else {
                log.error("Unable to send out notifications to employee with EmployeeInfo ID: '"
                        + assetAccessRequest.getEmployeeUserId() + "'");
            }

        }
    }

    public void setAssetRoleDao(AssetRoleDao assetRoleDao) {
        this.assetRoleDao = assetRoleDao;
    }

}
