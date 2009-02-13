package org.tll.canyon.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class is used to represent Employee information available in the
 * database.
 * 
 * @hibernate.class table="hr_emp_manager_info"
 */
public class EmployeeInfo extends BaseObject implements Serializable {
	
	private static final long serialVersionUID = 6670942010403192883L;
	public static final String EMP_STATUS_ACTIVE = "A";
	public static final String EMP_STATUS_NOTACTIVE = "N";
	public static final String EMP_TYPE_EMPLOYEE = "E";
	public static final String EMP_TYPE_NOTEMPLOYEE = "N";
	
	// EMP_ID, 7 R
	// EMP_USERID, 7 R
	// EMP_DEPT, 30
	// EMP_EMAIL, 50
	// EMP_STATUS, 1
	// EMP_TYPE, 1
	// EMP_FIRST_NAME, 30
	// EMP_LAST_NAME, 30
	// MANAGER_USERID, 10
	
	private String employeeUserId;
	private String employeeDept;
	private String employeeEmail;
	private String employeeStatus;
	private String employeeType;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeMiddleName;
	private String managerUserId;
    private EmployeeInfo manager;
    
       
    /**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="MANAGER_USERID" outer-join="true"
     */
	public EmployeeInfo getManager() {
		return manager;
	}

	public void setManager(EmployeeInfo manager) {
		this.manager = manager;
	}

	/**
     * @hibernate.id column="EMP_USERID" length="7" 
     */
	public String getEmployeeUserId() {
		return employeeUserId;
	}

	public void setEmployeeUserId(String employeeUserId) {
		this.employeeUserId = employeeUserId;
	}

	/**
	 * @hibernate.property column="EMP_DEPT" length="30"
	 */
	public String getEmployeeDept() {
		return employeeDept;
	}

	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}

	/**
	 * @hibernate.property column="EMP_EMAIL" length="50"
	 */
	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	/**
	 * Values include:
	 * 
	 * <pre>
	 *  A = active
	 *  N = not active
	 * </pre>
	 * 
	 * @hibernate.property column="EMP_STATUS" length="1"
	 */
	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	/**
	 * Values include:
	 * 
	 * <pre>
	 *  E = employee
	 *  N = not employee
	 * </pre>
	 * 
	 * @hibernate.property column="EMP_TYPE" length="1"
	 */
	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	/**
	 * @hibernate.property column="EMP_FIRST_NAME" length="30"
	 */
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	/**
	 * @hibernate.property column="EMP_LAST_NAME" length="30"
	 */
	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	/**
	 * @hibernate.property column="EMP_MIDDLE_NAME" length="30"
	 */
	public String getEmployeeMiddleName() {
		return employeeMiddleName;
	}

	public void setEmployeeMiddleName(String employeeMiddleName) {
		this.employeeMiddleName = employeeMiddleName;
	}

	/**
	 * @hibernate.property column="MANAGER_USERID" length="15"
	 */
	public String getManagerUserId() {
		return managerUserId;
	}

	public void setManagerUserId(String managerUserId) {
		this.managerUserId = managerUserId;
	}

	public String getEmployeeFullname() {
		return (this.employeeLastName + ", " + this.employeeFirstName);
	}
	
	/**
	 * Returns true if user status is active AND has an ID. 
	 */
	public boolean isActive(){
		if(EmployeeInfo.EMP_STATUS_ACTIVE.equals(this.employeeStatus)){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-590285799, 1015326287).append(this.employeeMiddleName).append(
				this.employeeStatus).append(this.employeeUserId).append(
				this.managerUserId).append(this.employeeFirstName).append(
				this.employeeType).append(this.employeeLastName).append(
				this.employeeEmail).append(this.employeeDept).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("employeeFirstName",
				this.employeeFirstName).append("employeeDept",
				this.employeeDept).append("employeeType", this.employeeType)
				.append("employeeFullname", this.getEmployeeFullname()).append(
						"employeeStatus", this.employeeStatus).append(
						"employeeEmail", this.employeeEmail).append(
						"employeeUserId", this.employeeUserId).append(
						"employeeLastName", this.employeeLastName).append(
						"managerUserId", this.managerUserId).append(
						"employeeMiddleName", this.employeeMiddleName)
				.toString();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof EmployeeInfo)) {
			return false;
		}
		EmployeeInfo rhs = (EmployeeInfo) object;
		return new EqualsBuilder().append(
				this.employeeMiddleName, rhs.employeeMiddleName).append(
				this.employeeStatus, rhs.employeeStatus).append(
				this.employeeUserId, rhs.employeeUserId).append(
				this.managerUserId, rhs.managerUserId).append(
				this.employeeFirstName, rhs.employeeFirstName).append(
				this.employeeType, rhs.employeeType).append(
				this.employeeLastName, rhs.employeeLastName).append(
				this.employeeEmail, rhs.employeeEmail).append(
				this.employeeDept, rhs.employeeDept).isEquals();
	}

}
