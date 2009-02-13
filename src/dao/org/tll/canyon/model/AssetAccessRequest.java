package org.tll.canyon.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @hibernate.class table="asset_access_request"
 */
public class AssetAccessRequest extends BaseObject {
   
    /**
     * 
     */
    private static final long serialVersionUID = 8112262217695632482L;

    private Long id;
    private Long assetRoleId;
    private AssetRole assetRole;
    private String employeeUserId;
    private EmployeeInfo employeeInfo;
	private List<AssetAccessRequestStatus> assetAccessRequestStatusList;
    private boolean complete;
    private Date completeDate;
    private Date requestCreateDate;

    /**
     * Basic constructor.
     */
    public AssetAccessRequest(){
        
    }
    /**
     * Helper constructor
     * @param employeeInfo
     */
    public AssetAccessRequest(EmployeeInfo employeeInfo){
        this.employeeInfo = employeeInfo;
    }
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
     * @return Returns the asset access status list
     * 
     * @hibernate.bag name="assetAccessRequestStatusList" lazy="true" cascade="all"
     * @hibernate.collection-key column="asset_access_request_id"
     * @hibernate.collection-one-to-many class="org.tll.canyon.model.AssetAccessRequestStatus"
     */
    public List<AssetAccessRequestStatus> getAssetAccessRequestStatusList() {
        return assetAccessRequestStatusList;
    }

    public void setAssetAccessRequestStatusList(List<AssetAccessRequestStatus> assetAccessRequestStatusList) {
        this.assetAccessRequestStatusList = assetAccessRequestStatusList;
    }
     
    /**
     * @hibernate.property column="asset_role_id"
     */
    public Long getAssetRoleId() {
        return assetRoleId;
    }

    /**
     * @spring.validator type="required"
     */
    public void setAssetRoleId(Long assetRoleId) {
        this.assetRoleId = assetRoleId;
    }

    /**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="asset_role_id" outer-join="true"
     */
    public AssetRole getAssetRole() {
        return assetRole;
    }

    public void setAssetRole(AssetRole assetRole) {
        this.assetRole = assetRole;
    }

    /**
     * @hibernate.property column="complete" type="yes_no"
     */
    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * @hibernate.property column="complete_date"  type="date"
     * 
     * @return String
     */
    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    /**
     * @hibernate.property column="create_date"  type="date"
     * 
     * @return String
     */
    public Date getRequestCreateDate() {
        return requestCreateDate;
    }

    public void setRequestCreateDate(Date requestCreateDate) {
        this.requestCreateDate = requestCreateDate;
    }

    /**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="employee_user_id" outer-join="true"
     */
    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }
    
    
    /**
     *   
     * 
     * @hibernate.property column="employee_user_id" length="50"
     */
    public String getEmployeeUserId() {
        return employeeUserId;
    }

    /**
     * @spring.validator type="required"
     */
    public void setEmployeeUserId(String employeeUserId) {
        this.employeeUserId = employeeUserId;
    }
    
        
    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AssetAccessRequest)) {
            return false;
        }
        AssetAccessRequest rhs = (AssetAccessRequest) object;
        return new EqualsBuilder().append(this.requestCreateDate, rhs.requestCreateDate)
                .append(this.assetRole, rhs.assetRole).append(this.assetRoleId, rhs.assetRoleId).append(
                        this.assetAccessRequestStatusList, rhs.assetAccessRequestStatusList).append(this.employeeInfo,
                        rhs.employeeInfo).append(this.employeeUserId, rhs.employeeUserId).append(this.complete, rhs.complete)
                .append(this.completeDate, rhs.completeDate).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-2116943357, -792223157).append(this.requestCreateDate)
                .append(this.assetRole).append(this.assetRoleId).append(this.assetAccessRequestStatusList).append(
                        this.employeeInfo).append(this.employeeUserId).append(
                        this.complete).append(this.completeDate).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("requestCreateDate", this.requestCreateDate).append("completeDate",
                this.completeDate).append("id", this.id).append("complete", this.complete).append("employeeInfoId", this.employeeUserId)
                .append("assetRoleId", this.assetRoleId).append("assetRole", this.assetRole).append(
                        "assetAccessRequestStatusList", this.assetAccessRequestStatusList).append("employeeInfo",
                        this.employeeInfo).toString();
    }
    
    
    
    
    
    
    

}
