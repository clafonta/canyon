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
 * @hibernate.class table="asset_history_notes"
 * 
 */
public class AssetHistoryNote extends BaseObject implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3249043556928621893L;
	private Long id;
    private String comment = null;
    private Date createTimestamp;
    private Long assetDetailId;
    private AssetDetail assetDetail;
    private User user;
    private Long userId;
    
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
    
    
    
    /**
     * @hibernate.property column="comment" type="string" length="500"
     * 
     * @return String
     */
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @hibernate.property column="create_timestamp"  type="date"
     * 
     * @return String
     */
    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
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
     * @hibernate.many-to-one insert="false" update="false" cascade="none"
     *  column="user_id" outer-join="true"
     */
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
     * @hibernate.property column="user_id"
     */
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AssetHistoryNote)) {
			return false;
		}
		AssetHistoryNote rhs = (AssetHistoryNote) object;
		return new EqualsBuilder().append(
				this.assetDetail, rhs.assetDetail).append(this.user, rhs.user)
				.append(this.userId, rhs.userId).append(this.createTimestamp,
						rhs.createTimestamp).append(this.comment, rhs.comment)
				.append(this.assetDetailId, rhs.assetDetailId).append(this.id,
						rhs.id).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1865045197, 232055497).append(this.assetDetail).append(this.user)
				.append(this.userId).append(this.createTimestamp).append(
						this.comment).append(this.assetDetailId)
				.append(this.id).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("user",
				this.user).append("assetDetailId", this.assetDetailId).append(
				"assetDetail", this.assetDetail)
				.append("comment", this.comment).append("createTimestamp",
						this.createTimestamp).append("userId", this.userId)
				.toString();
	}
        
    

   
}
