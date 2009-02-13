
package org.tll.canyon.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.tll.canyon.dao.AssetAccessRequestStatusDao;
import org.tll.canyon.model.AssetAccessRequestStatus;


public class AssetAccessRequestStatusDaoHibernate extends BaseDaoHibernate implements AssetAccessRequestStatusDao {

    /**
     * @see org.tll.canyon.dao.AssetAccessRequestStatusDao#getAssetAccessRequestStatuss(org.tll.canyon.model.AssetAccessRequestStatus)
     */
    @SuppressWarnings("unchecked")
    public List getAssetAccessRequestStatusList(final AssetAccessRequestStatus assetAccessRequestStatus) {
                
        if (assetAccessRequestStatus == null) {
            return getHibernateTemplate().find("from AssetAccessRequestStatus");
        } else {
            // filter on properties set in the assetAccessRequestStatus
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(assetAccessRequestStatus).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AssetAccessRequestStatus.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
        
    }

    /**
     * @see org.tll.canyon.dao.AssetAccessRequestStatusDao#getAssetAccessRequestStatus(Long id)
     */
    public AssetAccessRequestStatus getAssetAccessRequestStatus(final Long id) {
        AssetAccessRequestStatus assetAccessRequestStatus = (AssetAccessRequestStatus) getHibernateTemplate().get(AssetAccessRequestStatus.class, id);
        if (assetAccessRequestStatus == null) {
            log.warn("uh oh, assetAccessRequestStatus with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(AssetAccessRequestStatus.class, id);
        }

        return assetAccessRequestStatus;
    }

    /**
     * @see org.tll.canyon.dao.AssetAccessRequestStatusDao#saveAssetAccessRequestStatus(AssetAccessRequestStatus assetAccessRequestStatus)
     */    
    public void saveAssetAccessRequestStatus(final AssetAccessRequestStatus assetAccessRequestStatus) {
        getHibernateTemplate().saveOrUpdate(assetAccessRequestStatus);
    }

    /**
     * @see org.tll.canyon.dao.AssetAccessRequestStatusDao#removeAssetAccessRequestStatus(Long id)
     */
    public void removeAssetAccessRequestStatus(final Long id) {
        getHibernateTemplate().delete(getAssetAccessRequestStatus(id));
    }
}
