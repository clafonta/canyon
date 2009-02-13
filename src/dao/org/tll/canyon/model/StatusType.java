package org.tll.canyon.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class StatusType implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -8696752581099852557L;
    private String type = null;
    private StatusType(){};
    private StatusType(String type){
        this.type = type;
    }
    public static final StatusType APPROVED = new StatusType("APPROVED");
    public static final StatusType DECLINED = new StatusType("DECLINED");
    public static final StatusType PENDING = new StatusType("PENDING");
    public static final StatusType NOT_APPROVED = new StatusType("NOT_APPROVED");
    public static final StatusType OTHER = new StatusType("OTHER");
    public static final StatusType COMPLETED = new StatusType("COMPLETED");
    public static final StatusType NOT_COMPLETED = new StatusType("NOT_COMPLETED");
    public static final StatusType ALL = new StatusType("ALL");
    
    
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof StatusType)) {
            return false;
        }
        StatusType rhs = (StatusType) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.type, rhs.type).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1415675483, -1345539675).appendSuper(super.hashCode()).append(this.type).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.type;
    }
    
}
