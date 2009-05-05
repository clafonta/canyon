package org.tll.canyon.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author chad.lafontaine@gmail.com
 * 
 * @hibernate.class table="asset_attribute"
 */
public class AssetAttribute extends BaseObject {

	private static final long serialVersionUID = -6747169316191700318L;
	private Long id;
	private Long assetTypeId;
	private String name;
	private String description;
	private String type; // String, Long, Date, Boolean
	private boolean required;
	private AssetType assetType; // 

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
	 * @hibernate.property column="asset_type_id"
	 */
	public Long getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(Long assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	/**
	 * @hibernate.property column="name" length="50"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="description" length="200"
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String attributeDescription) {
		this.description = attributeDescription;
	}

	/**
	 * @hibernate.property column="type" length="25"
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @hibernate.property column="required" not-null="true" type="yes_no"
	 */
	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @hibernate.many-to-one insert="false" update="false" cascade="none"
	 *                        column="asset_type_id" outer-join="true"
	 */
	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name).append(
				"description", this.description).append("assetTypeId",
				this.assetTypeId).append("id", this.id).append("type",
				this.type).append("assetType", this.assetType).append(
				"required", this.required).toString();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AssetAttribute)) {
			return false;
		}
		AssetAttribute rhs = (AssetAttribute) object;
		return new EqualsBuilder().append(
				this.required, rhs.required).append(this.type, rhs.type)
				.append(this.assetTypeId, rhs.assetTypeId).append(
						this.description, rhs.description).append(
						this.assetType, rhs.assetType).append(this.name,
						rhs.name).append(this.id, rhs.id).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1898148937, 638779537).append(this.required).append(this.type)
				.append(this.assetTypeId).append(this.description).append(
						this.assetType).append(this.name).append(this.id)
				.toHashCode();
	}
	

	

}
