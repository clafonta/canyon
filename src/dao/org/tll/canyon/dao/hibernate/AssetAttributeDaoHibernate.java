
package org.tll.canyon.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.tll.canyon.dao.AssetAttributeDao;
import org.tll.canyon.model.AssetAttribute;


public class AssetAttributeDaoHibernate extends BaseDaoHibernate implements AssetAttributeDao {

    /**
     * @see org.tll.canyon.dao.AssetAttributeDao#getAssetAttributes(org.tll.canyon.model.AssetAttribute)
     */
    @SuppressWarnings("unchecked")
    public List getAssetAttributes(final AssetAttribute example) {
        
        if (example == null) {
            return getHibernateTemplate().find("from AssetAttribute");
        } else {
            // filter on properties set in the assetAttribute
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    // Why exclude BOOLEAN property, 'customerSensitiveData'? 
                    // Because, by default, the value is FALSE, and will be used in a filter. 
                    Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AssetAttribute.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
       
        
    }

    /**
     * @see org.tll.canyon.dao.AssetAttributeDao#getAssetAttribute(Long id)
     */
    public AssetAttribute getAssetAttribute(final Long id) {
        AssetAttribute assetAttribute = (AssetAttribute) getHibernateTemplate().get(AssetAttribute.class, id);
        if (assetAttribute == null) {
            log.warn("uh oh, assetAttribute with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(AssetAttribute.class, id);
        }

        return assetAttribute;
    }

    /**
     * @see org.tll.canyon.dao.AssetAttributeDao#saveAssetAttribute(AssetAttribute assetAttribute)
     */    
    public void saveAssetAttribute(final AssetAttribute assetAttribute) {
        getHibernateTemplate().saveOrUpdate(assetAttribute);
    }

    /**
     * @see org.tll.canyon.dao.AssetAttributeDao#removeAssetAttribute(Long id)
     */
    public void removeAssetAttribute(final Long id) {
        getHibernateTemplate().delete(getAssetAttribute(id));
    }
}
