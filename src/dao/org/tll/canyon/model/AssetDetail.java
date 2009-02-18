package org.tll.canyon.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @hibernate.class table="asset_detail"
 */
public class AssetDetail extends BaseObject {

    private static final long serialVersionUID = -8922423430356003833L;
    private Long id;
    private String assetName;
    private String assetGroupName;
    private String assetDescription;
    private String assetUsageType;
    private String assetLocation;
    private String assetAddress;
    
    private boolean enabled = true;
    private boolean managerApproval = true;
    private List<AssetHitStat> assetHitStats;
    private List<AssetHistoryNote> assetHistoryNotes;
    private List<AssetRole> assetRoles;
    private Date createDate;
    private String assetAdminTeamName;
    private String assetAdminTeamEmail;
    private String primaryAdminEmployeeId;
    private String secondaryAdminEmployeeId;
    private String primaryOwnerEmployeeId;
    private String secondaryOwnerEmployeeId;
    private EmployeeInfo primaryAdminEmployeeInfo;
    private EmployeeInfo secondaryAdminEmployeeInfo;
    private EmployeeInfo primaryOwnerEmployeeInfo;
    private EmployeeInfo secondaryOwnerEmployeeInfo;
    
    // TRANSIENT HELPER ATTRIBUTES - Used for SEARCH and FORM SUBMISSIONS
    // Concrete values found within respective EmployeeInfo objects
    // **I don't want to create an entire view wrapper for just four fields!
    private String filterPrimaryAdminEmpEmail;
	private String filterSecondaryAdminEmpEmail;
	private String filterPrimaryOwnerEmpEmail;
	private String filterSecondaryOwnerEmpEmail;

	// TRANSIENT
	private List<Issue> issues = null;

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
     * @hibernate.property column="asset_name" length="100" not-null="true" unique="true"
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * @spring.validator type="required"
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * @hibernate.property column="asset_description" length="2000"
     */
    public String getAssetDescription() {
        return assetDescription;
    }
    
    /**
     * 
     */
    public void setAssetGroupName(String assetGroupName) {
        this.assetGroupName = assetGroupName;
    }

    /**
     * @hibernate.property column="asset_group_name" length="100"
     */
    public String getAssetGroupName() {
        return assetGroupName;
    }

    /**
     * @spring.validator type="required"
     */
    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    /**
     * @hibernate.property column="asset_usagetype" length="100"
     */
    public String getAssetUsageType() {
        return assetUsageType;
    }

    public void setAssetUsageType(String assetUsageType) {
        this.assetUsageType = assetUsageType;
    }

    /**
     * @hibernate.property column="asset_location" length="100"
     */
    public String getAssetLocation() {
        return assetLocation;
    }

    public void setAssetLocation(String assetLocation) {
        this.assetLocation = assetLocation;
    }

    /**
     * Address, e.g. IP address.
     * 
     * @hibernate.property column="asset_address" length="100"
     */
    public String getAssetAddress() {
        return assetAddress;
    }

    /**
     * @spring.validator type="required"
     */
    public void setAssetAddress(String assetAddress) {
        this.assetAddress = assetAddress;
    }

    /**
     * @hibernate.property column="asset_enabled" type="yes_no"
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @spring.validator type="required"
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return Returns the asset hit stats.
     * 
     * @hibernate.bag name="assetHitStats" lazy="true" cascade="all"
     * @hibernate.collection-key column="asset_detail_id"
     * @hibernate.collection-one-to-many class="org.tll.canyon.model.AssetHitStat"
     */
    public List<AssetHitStat> getAssetHitStats() {
        return assetHitStats;
    }

    public void setAssetHitStats(List<AssetHitStat> assetHitStats) {
        this.assetHitStats = assetHitStats;
    }
    
    
    /**
     * @return Returns the asset history notes.
     * 
     * @hibernate.bag name="assetHistoryNotes" lazy="true" cascade="all" order-by="create_timestamp"
     * @hibernate.collection-key column="asset_detail_id"
     * @hibernate.collection-one-to-many class="org.tll.canyon.model.AssetHistoryNote"
     */
    public List<AssetHistoryNote> getAssetHistoryNotes() {
        return assetHistoryNotes;
    }

    public void setAssetHistoryNotes(List<AssetHistoryNote> assetHistoryNotes) {
        this.assetHistoryNotes = assetHistoryNotes;
    }
    

    /**
     * @return Returns the asset roles.
     * 
     * @hibernate.bag name="assetRoles" lazy="true" cascade="all"
     * @hibernate.collection-key column="asset_detail_id"
     * @hibernate.collection-one-to-many class="org.tll.canyon.model.AssetRole"
     */
    public List<AssetRole> getAssetRoles() {
        return assetRoles;
    }

    public void setAssetRoles(List<AssetRole> assetRoles) {
        this.assetRoles = assetRoles;
    }

    /**
     * @hibernate.property column="manager_approval_req" type="yes_no"
     */
    public boolean isManagerApproval() {
        return managerApproval;
    }

    public void setManagerApproval(boolean managerApproval) {
        this.managerApproval = managerApproval;
    }

    /**
     * @hibernate.property column="create_date" type="date"
     * 
     * @return String
     */
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @hibernate.property column="admin_team_name" length="50"
     */
	public String getAssetAdminTeamName() {
		return assetAdminTeamName;
	}

	public void setAssetAdminTeamName(String assetAdminTeamName) {
		this.assetAdminTeamName = assetAdminTeamName;
	}

	/**
     * @hibernate.property column="admin_team_email" length="50"
     */
	public String getAssetAdminTeamEmail() {
		return assetAdminTeamEmail;
	}

	public void setAssetAdminTeamEmail(String assetAdminTeamEmail) {
		this.assetAdminTeamEmail = assetAdminTeamEmail;
	}

	/**
     * @hibernate.property column="primary_admin_emp_id" length="50"
     */
	public String getPrimaryAdminEmployeeId() {
		return primaryAdminEmployeeId;
	}
	
	public void setPrimaryAdminEmployeeId(String primaryAdminEmployeeId) {
		this.primaryAdminEmployeeId = primaryAdminEmployeeId;
	}

	/**
     * @hibernate.property column="secondary_admin_emp_id" length="50"
     */
	public String getSecondaryAdminEmployeeId() {
		return secondaryAdminEmployeeId;
	}

	public void setSecondaryAdminEmployeeId(String secondaryAdminEmployeeId) {
		this.secondaryAdminEmployeeId = secondaryAdminEmployeeId;
	}

	/**
     * @hibernate.property column="primary_owner_emp_id" length="50"
     */
	public String getPrimaryOwnerEmployeeId() {
		return primaryOwnerEmployeeId;
	}

	public void setPrimaryOwnerEmployeeId(String primaryOwnerEmployeeId) {
		this.primaryOwnerEmployeeId = primaryOwnerEmployeeId;
	}

	/**
     * @hibernate.property column="secondary_owner_emp_id" length="50"
     */
	public String getSecondaryOwnerEmployeeId() {
		return secondaryOwnerEmployeeId;
	}

	public void setSecondaryOwnerEmployeeId(String secondaryOwnerEmployeeId) {
		this.secondaryOwnerEmployeeId = secondaryOwnerEmployeeId;
	}

	/**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="primary_admin_emp_id" outer-join="true"
     */
	public EmployeeInfo getPrimaryAdminEmployeeInfo() {
		return primaryAdminEmployeeInfo;
	}

	public void setPrimaryAdminEmployeeInfo(EmployeeInfo primaryAdminEmployeeInfo) {
		this.primaryAdminEmployeeInfo = primaryAdminEmployeeInfo;
	}

	/**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="secondary_admin_emp_id" outer-join="true"
     */
	public EmployeeInfo getSecondaryAdminEmployeeInfo() {
		return secondaryAdminEmployeeInfo;
	}

	public void setSecondaryAdminEmployeeInfo(
			EmployeeInfo secondaryAdminEmployeeInfo) {
		this.secondaryAdminEmployeeInfo = secondaryAdminEmployeeInfo;
	}

	/**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="primary_owner_emp_id" outer-join="true"
     */
	public EmployeeInfo getPrimaryOwnerEmployeeInfo() {
		return primaryOwnerEmployeeInfo;
	}

	public void setPrimaryOwnerEmployeeInfo(EmployeeInfo primaryOwnerEmployeeInfo) {
		this.primaryOwnerEmployeeInfo = primaryOwnerEmployeeInfo;
	}

	/**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="secondary_owner_emp_id" outer-join="true"
     */
	public EmployeeInfo getSecondaryOwnerEmployeeInfo() {
		return secondaryOwnerEmployeeInfo;
	}

	public void setSecondaryOwnerEmployeeInfo(
			EmployeeInfo secondaryOwnerEmployeeInfo) {
		this.secondaryOwnerEmployeeInfo = secondaryOwnerEmployeeInfo;
	}

	
	public String getFilterPrimaryAdminEmpEmail() {
		if(this.filterPrimaryAdminEmpEmail== null && this.primaryAdminEmployeeInfo!=null){
			return this.primaryAdminEmployeeInfo.getEmployeeEmail();
		}
		return filterPrimaryAdminEmpEmail;
	}

	/**
     * @spring.validator type="required"
     */
	public void setFilterPrimaryAdminEmpEmail(String filterPrimaryAdminEmpEmail) {
		this.filterPrimaryAdminEmpEmail = filterPrimaryAdminEmpEmail;
	}

	public String getFilterSecondaryAdminEmpEmail() {
		if(this.filterSecondaryAdminEmpEmail==null && this.secondaryAdminEmployeeInfo!=null){
			return this.secondaryAdminEmployeeInfo.getEmployeeEmail();
		}
		return filterSecondaryAdminEmpEmail;
	}

	/**
     * @spring.validator type="required"
     */
	public void setFilterSecondaryAdminEmpEmail(String filterSecondaryAdminEmpEmail) {
		this.filterSecondaryAdminEmpEmail = filterSecondaryAdminEmpEmail;
	}
	
	
	public String getFilterPrimaryOwnerEmpEmail() {
		if(this.filterPrimaryOwnerEmpEmail== null && this.primaryOwnerEmployeeInfo!=null){
			return this.primaryOwnerEmployeeInfo.getEmployeeEmail();
		}
		return filterPrimaryOwnerEmpEmail;
	}

	/**
     * @spring.validator type="required"
     */
	public void setFilterPrimaryOwnerEmpEmail(String filterPrimaryOwnerEmpEmail) {
		this.filterPrimaryOwnerEmpEmail = filterPrimaryOwnerEmpEmail;
	}

	public String getFilterSecondaryOwnerEmpEmail() {
		if(this.filterSecondaryOwnerEmpEmail==null&& this.secondaryOwnerEmployeeInfo!=null){
			return this.secondaryOwnerEmployeeInfo.getEmployeeEmail();
		}
		return filterSecondaryOwnerEmpEmail;
	}

	/**
     * @spring.validator type="required"
     */
	public void setFilterSecondaryOwnerEmpEmail(String filterSecondaryOwnerEmpEmail) {
		this.filterSecondaryOwnerEmpEmail = filterSecondaryOwnerEmpEmail;
	}
	
	
	public List<Issue> getIssues() {
		
		this.issues = new ArrayList<Issue>();
		
		if(this.primaryAdminEmployeeInfo!=null && !this.primaryAdminEmployeeInfo.isActive()){
			this.issues.add(new Issue("Inactive primary admin", Issue.INACTIVE_USER));
		}
		if( this.secondaryAdminEmployeeInfo!=null && !this.secondaryAdminEmployeeInfo.isActive()){
			this.issues.add(new Issue("Inactive secondary admin", Issue.INACTIVE_USER));
		}
		if(this.primaryOwnerEmployeeInfo!=null && !this.primaryOwnerEmployeeInfo.isActive()){
			this.issues.add(new Issue("Inactive primary owner", Issue.INACTIVE_USER));
		}
		if(this.secondaryOwnerEmployeeInfo!=null && !this.secondaryOwnerEmployeeInfo.isActive()){
			this.issues.add(new Issue("Inactive secondary owner", Issue.INACTIVE_USER));
		}
		
		return issues;
	}
	
	/** 
	 * Helper method.
	 * @return
	 */
	public boolean hasIssues(){
		if(this.getIssues().size() > 0){
			return true;
		}
		else {
			return false;
		}
	}
	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AssetDetail)) {
			return false;
		}
		AssetDetail rhs = (AssetDetail) object;
		return new EqualsBuilder()
				.append(this.primaryOwnerEmployeeInfo,
						rhs.primaryOwnerEmployeeInfo)
				.append(this.assetUsageType, rhs.assetUsageType)
				.append(this.managerApproval, rhs.managerApproval)
				.append(this.assetRoles, rhs.assetRoles)
				.append(this.assetAdminTeamEmail, rhs.assetAdminTeamEmail)
				.append(this.assetAdminTeamName, rhs.assetAdminTeamName)
				.append(this.assetHitStats, rhs.assetHitStats)
				.append(this.id, rhs.id)
				.append(this.secondaryAdminEmployeeInfo,
						rhs.secondaryAdminEmployeeInfo)
				.append(this.secondaryOwnerEmployeeId,
						rhs.secondaryOwnerEmployeeId)
				.append(this.assetName, rhs.assetName)
				.append(this.assetLocation, rhs.assetLocation)
				.append(this.primaryAdminEmployeeId, rhs.primaryAdminEmployeeId)
				.append(this.secondaryOwnerEmployeeInfo,
						rhs.secondaryOwnerEmployeeInfo)
				.append(this.secondaryAdminEmployeeId,
						rhs.secondaryAdminEmployeeId)
				.append(this.assetDescription, rhs.assetDescription)
				.append(this.enabled, rhs.enabled)
				.append(this.primaryOwnerEmployeeId, rhs.primaryOwnerEmployeeId)
				.append(this.createDate, rhs.createDate).append(
						this.primaryAdminEmployeeInfo,
						rhs.primaryAdminEmployeeInfo).append(this.assetAddress,
						rhs.assetAddress).isEquals();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("secondaryAdminEmployeeId",
				this.secondaryAdminEmployeeId).append("assetAdminTeamName",
				this.assetAdminTeamName).append("id", this.id).append(
				"assetAddress", this.assetAddress).append("assetLocation",
				this.assetLocation).append("enabled", this.enabled).append(
				"secondaryOwnerEmployeeInfo", this.secondaryOwnerEmployeeInfo)
				.append("primaryAdminEmployeeInfo",
						this.primaryAdminEmployeeInfo).append(
						"primaryAdminEmployeeId", this.primaryAdminEmployeeId)
				.append("assetHitStats", this.assetHitStats).append(
						"assetAdminTeamEmail", this.assetAdminTeamEmail)
				.append("assetName", this.assetName).append("assetUsageType",
						this.assetUsageType).append("secondaryOwnerEmployeeId",
						this.secondaryOwnerEmployeeId).append(
						"primaryOwnerEmployeeId", this.primaryOwnerEmployeeId)
				.append("assetDescription", this.assetDescription).append(
						"secondaryAdminEmployeeInfo",
						this.secondaryAdminEmployeeInfo).append(
						"primaryOwnerEmployeeInfo",
						this.primaryOwnerEmployeeInfo).append(
						"managerApproval", this.managerApproval).append(
						"assetRoles", this.assetRoles).append("createDate",
						this.createDate).toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1192128325, -170164571).append(
				this.assetUsageType).append(this.managerApproval).append(
				this.assetRoles).append(this.assetAdminTeamEmail).append(
				this.assetAdminTeamName).append(this.assetHitStats).append(
				this.id).append(
				this.secondaryAdminEmployeeInfo).append(
				this.secondaryOwnerEmployeeId).append(this.assetName).append(
				this.assetLocation).append(this.primaryAdminEmployeeId).append(
				this.secondaryOwnerEmployeeInfo).append(
				this.secondaryAdminEmployeeId).append(this.assetDescription)
				.append(this.enabled).append(this.primaryOwnerEmployeeId)
				.append(this.createDate).append(this.primaryAdminEmployeeInfo)
				.append(this.assetAddress).toHashCode();
	}
}
