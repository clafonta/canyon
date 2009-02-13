
package org.tll.canyon.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.tll.canyon.dao.AssetRoleDao;
import org.tll.canyon.model.AssetRole;


public class AssetRoleDaoHibernate extends BaseDaoHibernate implements AssetRoleDao {

    /**
     * @see org.tll.canyon.dao.AssetRoleDao#getAssetRoles(org.tll.canyon.model.AssetRole)
     */
    @SuppressWarnings("unchecked")
    public List getAssetRoles(final AssetRole example) {
        
        if (example == null) {
            return getHibernateTemplate().find("from AssetRole");
        } else {
            // filter on properties set in the assetRole
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    // Why exclude BOOLEAN property, 'customerSensitiveData'? 
                    // Because, by default, the value is FALSE, and will be used in a filter. 
                    Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE).excludeProperty("customerSensitiveData");
                    return session.createCriteria(AssetRole.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
       
        
    }

    /**
     * @see org.tll.canyon.dao.AssetRoleDao#getAssetRole(Long id)
     */
    public AssetRole getAssetRole(final Long id) {
        AssetRole assetRole = (AssetRole) getHibernateTemplate().get(AssetRole.class, id);
        if (assetRole == null) {
            log.warn("uh oh, assetRole with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(AssetRole.class, id);
        }

        return assetRole;
    }

    /**
     * @see org.tll.canyon.dao.AssetRoleDao#saveAssetRole(AssetRole assetRole)
     */    
    public void saveAssetRole(final AssetRole assetRole) {
        getHibernateTemplate().saveOrUpdate(assetRole);
    }

    /**
     * @see org.tll.canyon.dao.AssetRoleDao#removeAssetRole(Long id)
     */
    public void removeAssetRole(final Long id) {
        getHibernateTemplate().delete(getAssetRole(id));
    }
}
