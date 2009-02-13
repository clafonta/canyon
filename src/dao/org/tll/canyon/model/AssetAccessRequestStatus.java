package org.tll.canyon.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @hibernate.class table="asset_access_request_status"
 */
public class AssetAccessRequestStatus extends BaseObject implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 7344370585111376733L;
    private Long id;
    private String approvalUserId;
    private String approvalStatus;
    private Long assetAccessRequestId;
    private AssetAccessRequest assetAccessRequest;
    private String approvalEmailUniqueTrackId;
    private String comment;
    
    
    /**
     * @return Returns the id.
     * @hibernate.id column="id" generator-class="native" unsaved-value="null"
     */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @hibernate.property column="approval_user_id" length="50"
     */
    public String getApprovalUserId() {
        return approvalUserId;
    }
    public void setApprovalUserId(String approvalUserId) {
        this.approvalUserId = approvalUserId;
    }
    
    /**
     * @hibernate.property column="approval_status" length="50"
     */
    public String getApprovalStatus() {
        return approvalStatus;
    }
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    
    /**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="asset_access_request_id" outer-join="true"
     */
    public AssetAccessRequest getAssetAccessRequest() {
        return assetAccessRequest;
    }
    public void setAssetAccessRequest(AssetAccessRequest assetAccessRequest) {
        this.assetAccessRequest = assetAccessRequest;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof AssetAccessRequestStatus)) {
            return false;
        }
        AssetAccessRequestStatus rhs = (AssetAccessRequestStatus) object;
        return new EqualsBuilder().append(this.assetAccessRequest, rhs.assetAccessRequest)
                .append(this.approvalStatus, rhs.approvalStatus).append(this.approvalUserId, rhs.approvalUserId).append(
                        this.id, rhs.id).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1389286153, 1682620021).append(this.assetAccessRequest)
                .append(this.approvalStatus).append(this.approvalUserId).append(this.id).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("approvalStatus", this.approvalStatus).append(
                "approvalUserId", this.approvalUserId).append("assetAccessRequest", this.assetAccessRequest).toString();
    }
    
    /**
     * @hibernate.property column="asset_access_request_id"
     */
    public Long getAssetAccessRequestId() {
        return assetAccessRequestId;
    }
    public void setAssetAccessRequestId(Long assetAccessRequestId) {
        this.assetAccessRequestId = assetAccessRequestId;
    }
    
    /**
     * @hibernate.property column="approval_email_track_id" length="50"
     */
    public String getApprovalEmailUniqueTrackId() {
        return approvalEmailUniqueTrackId;
    }
    public void setApprovalEmailUniqueTrackId(String approvalEmailUniqueTrackId) {
        this.approvalEmailUniqueTrackId = approvalEmailUniqueTrackId;
    }
    
    /**
     * @hibernate.property column="status_comment" length="250"
     */
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
   
    
    
    

}
