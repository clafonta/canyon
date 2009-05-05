package org.tll.canyon.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class is used to represent available roles for an Asset
 *
 * @author chad.lafontaine@gmail.com
 * 
 * @hibernate.class table="asset_role"
 */
public class AssetRole extends BaseObject implements Serializable {
    
    private static final long serialVersionUID = -4619955419769275861L;
    private Long id;
    private String name;
    private String description;
    private Long assetDetailId;
    private boolean customerSensitiveData;
    private AssetDetail assetDetail;
    
    public AssetRole() {}
    
    public AssetRole(String name) {
        this.name = name;
    }
    
    /**
     * @hibernate.id column="id" generator-class="native" unsaved-value="null"
     */
    public Long getId() {
        return id;
    }  
    
    /**
     * @hibernate.property column="name" length="25"
     */
    public String getName() {
        return this.name;
    }

    /**
     * @hibernate.property column="description" length="400"
     */
    public String getDescription() {
        return this.description;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @spring.validator type="required"
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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
     * @hibernate.property column="customer_sensitive_data" type="yes_no"
     */
    public boolean isCustomerSensitiveData() {
        return customerSensitiveData;
    }

    public void setCustomerSensitiveData(boolean customerSensitiveData) {
        this.customerSensitiveData = customerSensitiveData;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("name", this.name).append("description", this.description).append("id",
                this.id).append("customerSensitiveData", this.customerSensitiveData).append("assetDetailId",
                this.assetDetailId).append("assetDetail", this.assetDetail).toString();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1263649773, 2088661357).append(this.assetDetail).append(
                this.customerSensitiveData).append(this.description).append(this.name).append(this.assetDetailId).append(
                this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof AssetRole)) {
            return false;
        }
        AssetRole rhs = (AssetRole) object;
        return new EqualsBuilder().append(this.assetDetail, rhs.assetDetail).append(
                this.customerSensitiveData, rhs.customerSensitiveData).append(this.description, rhs.description).append(
                this.name, rhs.name).append(this.assetDetailId, rhs.assetDetailId).append(this.id, rhs.id).isEquals();
    }    
    
}
