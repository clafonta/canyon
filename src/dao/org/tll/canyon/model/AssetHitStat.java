package org.tll.canyon.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Contains information of an access or <i>hit</i> to an asset. 
 *
 * @author Chad Lafontaine (chad.lafontaine@gmail.com)
 * @hibernate.class table="asset_hit_stat"
 * 
 */
public class AssetHitStat extends BaseObject implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8725663768295770681L;
    
    private Long id;
    private String userIdentifierConnectingToAsset = null;
    private String userIdentifierFromOS = null;
    private Date lastLoginTimestamp;    
    private Integer numberOfLogins;
    private String connectingIP;
    private String host;
    private String applicationUsedToConnect;
    private Long assetDetailId;
    private AssetDetail assetDetail;
    
    
    /**
     * @hibernate.property column="uid_connecting_to_asset" type="string"
     * 
     * @return String
     */
    public String getUserIdentifierConnectingToAsset() {
        return userIdentifierConnectingToAsset;
    }

    public void setUserIdentifierConnectingToAsset(String userIdentifierConnectingToAsset) {
        this.userIdentifierConnectingToAsset = userIdentifierConnectingToAsset;
    }

    /**
     * @hibernate.property column="app_used_to_connect_to_asset" type="string"
     * 
     * @return String
     */
    public String getApplicationUsedToConnect() {
        return applicationUsedToConnect;
    }

    public void setApplicationUsedToConnect(String applicationUsedToConnect) {
        this.applicationUsedToConnect = applicationUsedToConnect;
    }

    /**
     * @hibernate.property column="ip_source_of_connection" type="string"
     * 
     * @return String
     */
    public String getConnectingIP() {
        return connectingIP;
    }

    public void setConnectingIP(String connectingIP) {
        this.connectingIP = connectingIP;
    }

    /**
     * @hibernate.property column="host" type="string"
     * 
     * @return String
     */
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @hibernate.property column="last_access_time"  type="date"
     * 
     * @return String
     */
    public Date getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    public void setLastLoginTimestamp(Date lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }
    

    /**
     * @hibernate.property column="num_of_logins_past_90days" type="integer"
     * 
     */
    public Integer getNumberOfLogins() {
        return numberOfLogins;
    }

    /**
     * @hibernate.property column="uid_connecting_from_os" type="string"
     * 
     * @return String
     */
    public String getUserIdentifierFromOS() {
        return userIdentifierFromOS;
    }

    public void setUserIdentifierFromOS(String userIdentifierFromOS) {
        this.userIdentifierFromOS = userIdentifierFromOS;
    }

    /**
     * @hibernate.id column="id" generator-class="native" type="long"
     * @return Long
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberOfLogins(Integer numberOfLogins) {
        this.numberOfLogins = numberOfLogins;
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
     *  column="asset_detail_id" outer-join="true"
     */
    public AssetDetail getAssetDetail() {
        return assetDetail;
    }

    public void setAssetDetail(AssetDetail assetDetail) {
        this.assetDetail = assetDetail;
    }
    
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof AssetHitStat)) {
            return false;
        }
        AssetHitStat rhs = (AssetHitStat) object;
        return new EqualsBuilder().append(this.userIdentifierConnectingToAsset,
                rhs.userIdentifierConnectingToAsset).append(this.applicationUsedToConnect, rhs.applicationUsedToConnect)
                .append(this.connectingIP, rhs.connectingIP).append(this.host, rhs.host).append(this.userIdentifierFromOS,
                        rhs.userIdentifierFromOS).append(this.numberOfLogins, rhs.numberOfLogins).append(this.lastLoginTimestamp,
                        rhs.lastLoginTimestamp).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1027386017, -1366434275).append(
                this.userIdentifierConnectingToAsset).append(this.applicationUsedToConnect).append(this.connectingIP)
                .append(this.host).append(this.userIdentifierFromOS).append(this.numberOfLogins).append(this.lastLoginTimestamp)
                .append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("lastLoginDate", this.lastLoginTimestamp).append("connectingIP",
                this.connectingIP).append("id", this.id).append("host", this.host).append("userIdentifierConnectingToAsset",
                this.userIdentifierConnectingToAsset).append("numberOfLogins", this.numberOfLogins).append(
                "userIdentifierFromOS", this.userIdentifierFromOS).append("applicationUsedToConnect",
                this.applicationUsedToConnect).toString();
    }

    

   
}
