package org.tll.canyon.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * AssetType has 0 or more AssetAttribute objects associated with it. 
 * 
 * 
 * @author chad.lafontaine@gmail.com
 * 
 * @hibernate.class table="option_value"
 */
public class OptionValue extends BaseObject {


	private static final long serialVersionUID = 8540254814414720889L;
	private Long id;
	private Long assetAttributeId;
	private AssetAttribute assetAttribute;
	private String value;
	
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
	 * @hibernate.property column="asset_attribute_id"
	 */
	public Long getAssetAttributeId() {
		return assetAttributeId;
	}
	public void setAssetAttributeId(Long assetAttributeId) {
		this.assetAttributeId = assetAttributeId;
	}
	
	/**
	 * @hibernate.many-to-one insert="false" update="false" cascade="none"
	 *                        column="asset_attribute_id" outer-join="true"
	 */
	public AssetAttribute getAssetAttribute() {
		return assetAttribute;
	}
	public void setAssetAttribute(AssetAttribute assetAttribute) {
		this.assetAttribute = assetAttribute;
	}
	
	/**
     * @hibernate.property column="value" length="250" not-null="true"
     */
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof OptionValue)) {
			return false;
		}
		OptionValue rhs = (OptionValue) object;
		return new EqualsBuilder().append(
				this.value, rhs.value).append(this.assetAttributeId,
				rhs.assetAttributeId).append(this.assetAttribute,
				rhs.assetAttribute).append(this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1908710943, -1851783629).append(this.value).append(
				this.assetAttributeId).append(this.assetAttribute).append(
				this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("assetAttribute",
				this.assetAttribute).append("id", this.id).append("value",
				this.value).append("assetAttributeId", this.assetAttributeId)
				.toString();
	}
	
	
	
	
}
