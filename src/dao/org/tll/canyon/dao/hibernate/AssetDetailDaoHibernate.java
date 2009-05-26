package org.tll.canyon.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.tll.canyon.dao.AssetDetailDao;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.viewwrappers.AssetHitStatSearchForm;


public class AssetDetailDaoHibernate extends BaseDaoHibernate implements AssetDetailDao {

    public AssetDetail getAssetDetail(Long assetDetailId) {
        AssetDetail assetDetail = (AssetDetail) getHibernateTemplate().get(AssetDetail.class, assetDetailId);

        //        if (assetDetail == null) {
        //            throw new ObjectRetrievalFailureException(AssetDetail.class, assetDetailId);
        //        }

        return assetDetail;
    }
    
    @SuppressWarnings("unchecked")
    public AssetDetail getAssetDetailByName(final String assetName) {

        List<AssetDetail> list = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(AssetDetail.class);
                criteria.add(Restrictions.eq("assetName", assetName));               
                return criteria.list();
            }
        });
        if(list!=null && list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
        
    }
    
    

    public void saveAssetDetail(AssetDetail assetDetail) {
        getHibernateTemplate().saveOrUpdate(assetDetail);
    }

    public void removeAssetDetail(Long id) {
        // object must be loaded before it can be deleted
        getHibernateTemplate().delete(getAssetDetail(id));
    }
    
    /**
     * @see org.tll.canyon.dao.AssetAccessRequestDao#getAssetAccessRequestsBySearch(AssetHitStatSearchForm)
     */
    @SuppressWarnings("unchecked")
    public List<AssetDetail> getAssetDetails(final AssetDetail assetDetail) {
    	
    	if (assetDetail == null) {
            return getHibernateTemplate().find("from AssetDetail");
        } else {	
	        return getHibernateTemplate().executeFind(new HibernateCallback() {
	            public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                Criteria criteria = session.createCriteria(AssetDetail.class);
	                Disjunction disjunction = Expression.disjunction(); 
	                
	                if(assetDetail.getAssetName()!=null && assetDetail.getAssetName().trim().length()>0){
	                	disjunction.add(Expression.ilike("assetName",assetDetail.getAssetName().trim(), MatchMode.ANYWHERE));
	                }
	                criteria.add(disjunction);
	                log.debug("Retrieving Asset Details with query");
	
	                return criteria.list();
	            }
	        });
        }
    }


}
