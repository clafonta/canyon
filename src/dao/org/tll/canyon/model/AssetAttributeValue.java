package org.tll.canyon.model;

import java.util.Date;




/**
 * @hibernate.class table="asset_attribute_value"
 */
public class AssetAttributeValue extends BaseObject {

	
	private static final long serialVersionUID = -5388323625187361074L;
	private Long id;
	private Long assetAttributeId;
	private Long assetDetailId;
	private AssetAttribute assetAttribute;
	private AssetDetail assetDetail;
	
	private String stringValue;
	private float floatValue;
	private Date dateValue;
	private boolean booleanValue;
		
	/**
     * @hibernate.property column="attribute_type_string" length="500"
     */
	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	/**
     * @hibernate.property column="attribute_type_float" 
     */
	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

	/**
     * @hibernate.property column="attribute_type_date" type="date"
     * 
     */
	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	/**
     * @hibernate.property column="attribute_type_boolean" type="yes_no"
     */
	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
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
	 * @hibernate.property column="asset_attribute_id"
	 */
	public Long getAssetAttributeId() {
		return assetAttributeId;
	}

	public void setAssetAttributeId(Long assetAttributeId) {
		this.assetAttributeId = assetAttributeId;
	}

	/**
	 * @hibernate.property column="asset_detail_id"
	 */
	public Long getAssetDetailId() {
		return assetDetailId;
	}

	public void setAssetDetailId(Long assetDetailId) {
		this.assetDetailId = assetDetailId;
	}

	/**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="asset_attribute_id" outer-join="true"
     */
	public AssetAttribute getAssetAttribute() {
		return assetAttribute;
	}

	public void setAssetAttribute(AssetAttribute assetAttribute) {
		this.assetAttribute = assetAttribute;
	}

	/**
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="asset_detail_id" outer-join="true"
     */
	public AssetDetail getAssetDetail() {
		return assetDetail;
	}

	public void setAssetDetail(AssetDetail assetDetail) {
		this.assetDetail = assetDetail;
	}

	/**
     * @hibernate.property column="value" length="400" not-null="true" unique="true"
     */
	public String getValue() {
		return stringValue;
	}

	public void setValue(String value) {
		this.stringValue = value;
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
