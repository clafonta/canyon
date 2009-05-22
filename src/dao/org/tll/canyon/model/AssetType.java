package org.tll.canyon.model;

import java.util.List;

/**
 * AssetType has 0 or more AssetAttribute objects associated with it. 
 * 
 * 
 * @author chad.lafontaine@gmail.com
 * 
 * @hibernate.class table="asset_type"
 */
public class AssetType extends BaseObject {

	
	private static final long serialVersionUID = 1554910737231404628L;
	private Long id;
	private String name;
	private String description;
	private List<AssetAttribute> assetAttributeList;


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
     * @hibernate.property column="description" length="400"
     */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
     * @hibernate.property column="name" length="100" not-null="true" unique="true"
     */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * @return Returns the asset roles.
     * 
     * @hibernate.bag name="assetAttributeList" lazy="true" cascade="all"
     * @hibernate.collection-key column="asset_type_id"
     * @hibernate.collection-one-to-many class="org.tll.canyon.model.AssetAttribute"
     */
    public List<AssetAttribute> getAssetAttributeList() {
        return assetAttributeList;
    }

    public void setAssetAttributeList(List<AssetAttribute> assetAttributes) {
        this.assetAttributeList = assetAttributes;
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

}
