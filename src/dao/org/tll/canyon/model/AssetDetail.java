package org.tll.canyon.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @hibernate.class table="asset_detail"
 */
public class AssetDetail extends BaseObject {
                           																																																																																																																																									
    
    private Long id;
    private String assetName;
    private Long assetTypeId;
    private AssetType assetType;
    private List<AssetHitStat> assetHitStats;
    private List<AssetHistoryNote> assetHistoryNotes;
    private List<AssetRole> assetRoles;
    private List<AssetAttributeValue> assetAttributeValueList;
    private Date createDate;
       
 	// TRANSIENT
	private List<Issue> issues = null;

	/**
  * @return Returns the asset attribute value
  * 
  * @hibernate.bag name="assetAttributeValues" lazy="true" cascade="all" 
  * @hibernate.collection-key column="asset_detail_id"
  * @hibernate.collection-one-to-many class="org.tll.canyon.model.AssetAttributeValue"
  */
 public List<AssetAttributeValue> getAssetAttributeValueList() {
		return assetAttributeValueList;
	}

	public void setAssetAttributeValueList(
			List<AssetAttributeValue> assetAttributeValues) {
		this.assetAttributeValueList = assetAttributeValues;
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
	 * @hibernate.property column="asset_type_id"
	 */
    public Long getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(Long assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	/**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="asset_type_id" outer-join="true"
     */
	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
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

    	
	
	public List<Issue> getIssues() {
		
		this.issues = new ArrayList<Issue>();
		
//		if(this.primaryAdminEmployeeInfo!=null && !this.primaryAdminEmployeeInfo.isActive()){
//			this.issues.add(new Issue("Inactive primary admin", Issue.INACTIVE_USER));
//		}
//		if( this.secondaryAdminEmployeeInfo!=null && !this.secondaryAdminEmployeeInfo.isActive()){
//			this.issues.add(new Issue("Inactive secondary admin", Issue.INACTIVE_USER));
//		}
//		if(this.primaryOwnerEmployeeInfo!=null && !this.primaryOwnerEmployeeInfo.isActive()){
//			this.issues.add(new Issue("Inactive primary owner", Issue.INACTIVE_USER));
//		}
//		if(this.secondaryOwnerEmployeeInfo!=null && !this.secondaryOwnerEmployeeInfo.isActive()){
//			this.issues.add(new Issue("Inactive secondary owner", Issue.INACTIVE_USER));
//		}
//		
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

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AssetAttributeValue getAssetAttributeValueWithMatchingId(Long assetAttributeId){
		AssetAttributeValue value = null;
		if(this.assetAttributeValueList!=null){
			Iterator<AssetAttributeValue> iter = this.assetAttributeValueList.iterator();
			while(iter.hasNext()){
				AssetAttributeValue tempValue = iter.next();
				if(tempValue.getAssetAttributeId().equals(assetAttributeId)){
					value = tempValue;
					break;
				}
			}
		}
		return value;
		
	}

	
}
