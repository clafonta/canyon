package org.tll.canyon.model.viewwrappers;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.tll.canyon.model.BaseObject;


public class AssetHitStatSearchForm extends BaseObject implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1880651452117047013L;
    private String userIdentifierConnectingToAsset = null;
    private String userIdentifierFromOS = null;
    private Date startLastLoginTimestamp;  
    private Date endLastLoginTimestamp;   
    private String startLastLoginTimestampString;  
    private String endLastLoginTimestampString;   
    private Integer minNumberOfLogins;
    private Integer maxNumberOfLogins;
    private String minNumberOfLoginsString;
    private String maxNumberOfLoginsString;
    private String connectingIP;
    private String host;
    private String applicationUsedToConnect;
    private String assetDetailId;
   // private DateConverter dateConverter = new DateConverter();
    
    public String getUserIdentifierConnectingToAsset() {
        return userIdentifierConnectingToAsset;
    }
    public void setUserIdentifierConnectingToAsset(String userIdentifierConnectingToAsset) {
        this.userIdentifierConnectingToAsset = userIdentifierConnectingToAsset;
    }
    public String getUserIdentifierFromOS() {
        return userIdentifierFromOS;
    }
    public void setUserIdentifierFromOS(String userIdentifierFromOS) {
        this.userIdentifierFromOS = userIdentifierFromOS;
    }
    public Date getStartLastLoginTimestamp() {        
        return this.startLastLoginTimestamp;        
    }
    public void setStartLastLoginTimestamp(Date startLastLoginTimestamp) {
        this.startLastLoginTimestamp = startLastLoginTimestamp;
    }
    public Date getEndLastLoginTimestamp() {       
        return this.endLastLoginTimestamp;     
    }
    public void setEndLastLoginTimestamp(Date endLastLoginTimestamp) {
        this.endLastLoginTimestamp = endLastLoginTimestamp;
    }
    public String getStartLastLoginTimestampString() {
        return startLastLoginTimestampString;
    }
    public void setStartLastLoginTimestampString(String startLastLoginTimestampString) {
        this.startLastLoginTimestampString = startLastLoginTimestampString;
    }
    public String getEndLastLoginTimestampString() {
        return endLastLoginTimestampString;
    }
    public void setEndLastLoginTimestampString(String endLastLoginTimestampString) {
        this.endLastLoginTimestampString = endLastLoginTimestampString;
    }
    public Integer getMinNumberOfLogins() {
        return minNumberOfLogins;
    }
    public void setMinNumberOfLogins(Integer minNumberOfLogins) {
        this.minNumberOfLogins = minNumberOfLogins;
    }
    public Integer getMaxNumberOfLogins() {
        return maxNumberOfLogins;
    }
    public void setMaxNumberOfLogins(Integer maxNumberOfLogins) {
        this.maxNumberOfLogins = maxNumberOfLogins;
    }
    public String getConnectingIP() {
        return connectingIP;
    }
    public void setConnectingIP(String connectingIP) {
        this.connectingIP = connectingIP;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getApplicationUsedToConnect() {
        return applicationUsedToConnect;
    }
    public void setApplicationUsedToConnect(String applicationUsedToConnect) {
        this.applicationUsedToConnect = applicationUsedToConnect;
    }
    public String getAssetDetailId() {
        return assetDetailId;
    }
    public void setAssetDetailId(String assetDetailId) {
        this.assetDetailId = assetDetailId;
    }
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("connectingIP", this.connectingIP).append("endLastLoginTimestampString",
                this.endLastLoginTimestampString).append("startLastLoginTimestamp", this.startLastLoginTimestamp).append(
                "host", this.host).append("userIdentifierConnectingToAsset", this.userIdentifierConnectingToAsset).append(
                "startLastLoginTimestampString", this.startLastLoginTimestampString).append("maxNumberOfLogins",
                this.maxNumberOfLogins).append("assetDetailId", this.assetDetailId).append("userIdentifierFromOS",
                this.userIdentifierFromOS).append("minNumberOfLogins", this.minNumberOfLogins).append(
                "endLastLoginTimestamp", this.endLastLoginTimestamp).append("applicationUsedToConnect",
                this.applicationUsedToConnect).toString();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1243524641, -1662222857).append(
                this.userIdentifierConnectingToAsset).append(this.applicationUsedToConnect).append(
                this.startLastLoginTimestamp).append(this.connectingIP).append(this.host).append(this.minNumberOfLogins)
                .append(this.userIdentifierFromOS).append(this.startLastLoginTimestampString).append(
                        this.endLastLoginTimestamp).append(this.maxNumberOfLogins).append(this.assetDetailId).append(
                        this.endLastLoginTimestampString).toHashCode();
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        AssetHitStatSearchForm myClass = (AssetHitStatSearchForm) object;
        return new CompareToBuilder().append(this.userIdentifierConnectingToAsset, myClass.userIdentifierConnectingToAsset)
                .append(this.applicationUsedToConnect, myClass.applicationUsedToConnect).append(
                        this.startLastLoginTimestamp, myClass.startLastLoginTimestamp).append(this.connectingIP,
                        myClass.connectingIP).append(this.host, myClass.host).append(this.minNumberOfLogins,
                        myClass.minNumberOfLogins).append(this.userIdentifierFromOS, myClass.userIdentifierFromOS).append(
                        this.startLastLoginTimestampString, myClass.startLastLoginTimestampString).append(
                        this.endLastLoginTimestamp, myClass.endLastLoginTimestamp).append(this.maxNumberOfLogins,
                        myClass.maxNumberOfLogins).append(this.assetDetailId, myClass.assetDetailId).append(
                        this.endLastLoginTimestampString, myClass.endLastLoginTimestampString).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AssetHitStatSearchForm)) {
            return false;
        }
        AssetHitStatSearchForm rhs = (AssetHitStatSearchForm) object;
        return new EqualsBuilder().append(this.userIdentifierConnectingToAsset,
                rhs.userIdentifierConnectingToAsset).append(this.applicationUsedToConnect, rhs.applicationUsedToConnect)
                .append(this.startLastLoginTimestamp, rhs.startLastLoginTimestamp).append(this.connectingIP,
                        rhs.connectingIP).append(this.host, rhs.host).append(this.minNumberOfLogins, rhs.minNumberOfLogins)
                .append(this.userIdentifierFromOS, rhs.userIdentifierFromOS).append(this.startLastLoginTimestampString,
                        rhs.startLastLoginTimestampString).append(this.endLastLoginTimestamp, rhs.endLastLoginTimestamp)
                .append(this.maxNumberOfLogins, rhs.maxNumberOfLogins).append(this.assetDetailId, rhs.assetDetailId).append(
                        this.endLastLoginTimestampString, rhs.endLastLoginTimestampString).isEquals();
    }
    public String getMinNumberOfLoginsString() {
        return minNumberOfLoginsString;
    }
    public void setMinNumberOfLoginsString(String minNumberOfLoginsString) {
        this.minNumberOfLoginsString = minNumberOfLoginsString;
    }
    public String getMaxNumberOfLoginsString() {
        return maxNumberOfLoginsString;
    }
    public void setMaxNumberOfLoginsString(String maxNumberOfLoginsString) {
        this.maxNumberOfLoginsString = maxNumberOfLoginsString;
    }
    
    
    
    
}
