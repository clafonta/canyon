package org.tll.canyon.model.viewwrappers;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.tll.canyon.model.BaseObject;


public class AssetAccessRequestSearchForm extends BaseObject implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 6442136877095520069L;
    private String employeeUserId;    
    private String employeeEmail;
    
	private String assetAdministrationEmail;
    private Boolean complete = null;
    private Date startCompleteDate;
    private Date endCompleteDate;
    private String startCompleteDateString;
    private String endCompleteDateString;
    private String assetRole;
    private String assetName;
      
    /**
     * JSP Tag helper. Same as method isMethod
     * @return
     */
    public Boolean getComplete(){
        return complete;
    }
    public Boolean isComplete() {
        return complete;
    }
    public void setComplete(boolean complete) {
        this.complete = new Boolean(complete);
    }
    public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
    public Date getStartCompleteDate() {
        return startCompleteDate;
    }
    public void setStartCompleteDate(Date startCompleteDate) {
        this.startCompleteDate = startCompleteDate;
    }
    public Date getEndCompleteDate() {
        return endCompleteDate;
    }
    public void setEndCompleteDate(Date endCompleteDate) {
        this.endCompleteDate = endCompleteDate;
    }
   
    public String getStartCompleteDateString() {
        return startCompleteDateString;
    }
    public void setStartCompleteDateString(String startCompleteDateString) {
        this.startCompleteDateString = startCompleteDateString;
    }
    public String getEndCompleteDateString() {
        return endCompleteDateString;
    }
    public void setEndCompleteDateString(String endCompleteDateString) {
        this.endCompleteDateString = endCompleteDateString;
    }
    public String getAssetRole() {
        return assetRole;
    }
    public void setAssetRole(String assetRole) {
        this.assetRole = assetRole;
    }
    public String getAssetName() {
        return assetName;
    }
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    public String getEmployeeUserId() {
        return employeeUserId;
    }
    public void setEmployeeUserId(String employeeUserId) {
        this.employeeUserId = employeeUserId;
    }
    public String getAssetAdministrationEmail() {
        return assetAdministrationEmail;
    }
    public void setAssetAdministrationEmail(String assetAdministrationEmail) {
        this.assetAdministrationEmail = assetAdministrationEmail;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof AssetAccessRequestSearchForm)) {
            return false;
        }
        AssetAccessRequestSearchForm rhs = (AssetAccessRequestSearchForm) object;
        return new EqualsBuilder().append(this.startCompleteDateString,
                rhs.startCompleteDateString).append(this.assetName, rhs.assetName).append(this.startCompleteDate,
                rhs.startCompleteDate).append(this.assetRole, rhs.assetRole).append(this.endCompleteDateString,
                rhs.endCompleteDateString).append(this.endCompleteDate, rhs.endCompleteDate).append(this.employeeUserId,
                rhs.employeeUserId).append(this.assetAdministrationEmail, rhs.assetAdministrationEmail).append(
                this.complete, rhs.complete).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(10349461, -152165863).append(this.startCompleteDateString)
                .append(this.assetName).append(this.startCompleteDate).append(this.assetRole).append(
                        this.endCompleteDateString).append(this.endCompleteDate).append(this.employeeUserId).append(
                        this.assetAdministrationEmail).append(this.complete).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("assetName", this.assetName).append("endCompleteDateString",
                this.endCompleteDateString).append("complete", this.complete).append("assetAdministrationEmail",
                this.assetAdministrationEmail).append("startCompleteDateString", this.startCompleteDateString).append(
                "assetRole", this.assetRole).append("employeeUserId", this.employeeUserId).append("startCompleteDate",
                this.startCompleteDate).append("complete", this.complete).append("endCompleteDate", this.endCompleteDate)
                .toString();
    }
    

}
