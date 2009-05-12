
package org.tll.canyon.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.tll.canyon.dao.AssetTypeDao;
import org.tll.canyon.model.AssetType;


public class AssetTypeDaoHibernate extends BaseDaoHibernate implements AssetTypeDao {

    /**
     * @see org.tll.canyon.dao.AssetTypeDao#getAssetTypes(org.tll.canyon.model.AssetType)
     */
    @SuppressWarnings("unchecked")
    public List getAssetTypes(final AssetType example) {
        
        if (example == null) {
            return getHibernateTemplate().find("from AssetType");
        } else {
            // filter on properties set in the assetType
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    // Why exclude BOOLEAN property, 'customerSensitiveData'? 
                    // Because, by default, the value is FALSE, and will be used in a filter. 
                    Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AssetType.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
       
        
    }

    /**
     * @see org.tll.canyon.dao.AssetTypeDao#getAssetType(Long id)
     */
    public AssetType getAssetType(final Long id) {
        AssetType assetType = (AssetType) getHibernateTemplate().get(AssetType.class, id);
        if (assetType == null) {
            log.warn("uh oh, assetType with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(AssetType.class, id);
        }

        return assetType;
    }

    /**
     * @see org.tll.canyon.dao.AssetTypeDao#saveAssetType(AssetType assetType)
     */    
    public void saveAssetType(final AssetType assetType) {
        getHibernateTemplate().saveOrUpdate(assetType);
    }

    /**
     * @see org.tll.canyon.dao.AssetTypeDao#removeAssetType(Long id)
     */
    public void removeAssetType(final Long id) {
        getHibernateTemplate().delete(getAssetType(id));
    }
}
