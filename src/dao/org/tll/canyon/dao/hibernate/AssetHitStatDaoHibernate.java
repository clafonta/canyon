package org.tll.canyon.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.tll.canyon.dao.AssetHitStatDao;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetHitStat;
import org.tll.canyon.model.viewwrappers.AssetHitStatSearchForm;


public class AssetHitStatDaoHibernate extends BaseDaoHibernate implements AssetHitStatDao {

    public AssetHitStat getAssetHitStat(Long assetHitStatId) {
        AssetHitStat assetHitStat = (AssetHitStat) getHibernateTemplate().get(AssetHitStat.class, assetHitStatId);
        if (assetHitStat == null) {
            throw new ObjectRetrievalFailureException(AssetHitStat.class, assetHitStatId);
        }
        return assetHitStat;
    }

    public void saveAssetHitStat(AssetHitStat assetHitStat) {
        getHibernateTemplate().saveOrUpdate(assetHitStat);
    }

    public void removeAssetHitStat(Long assetHitStatId) {
        // object must be loaded before it can be deleted
        getHibernateTemplate().delete(getAssetHitStat(assetHitStatId));
    }

    @SuppressWarnings("unchecked")
    public List<AssetHitStat> getAssetHitStats(final AssetHitStat example) {
        //return getHibernateTemplate().find("from AssetHitStat");
        if (example == null) {
            return getHibernateTemplate().find("from AssetHitStat");
        } else {
            // filter on properties set in the assetRole
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {

                    Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AssetHitStat.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AssetHitStat> getAssetHitStatsForAssetDetail(AssetDetail assetDetail) {
        List items = getHibernateTemplate().find("from AssetHitStat where assetDetailId=?", assetDetail.getId());
        if (items == null || items.isEmpty()) {
            return new ArrayList();
        } else {
            return items;
        }
    }

    @SuppressWarnings("unchecked")
    public List<AssetHitStat> getAssetHitStatsBySearch(final AssetHitStatSearchForm assetHitStatSearchForm) {

        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(AssetHitStat.class);
                criteria.add(Restrictions.eq("assetDetailId", new Long(assetHitStatSearchForm.getAssetDetailId())));
                // Last Login DATE RANGE 
                if (assetHitStatSearchForm.getStartLastLoginTimestamp() != null
                        && assetHitStatSearchForm.getEndLastLoginTimestamp() != null) {
                    criteria.add(Restrictions.between("lastLoginTimestamp", assetHitStatSearchForm
                            .getStartLastLoginTimestamp(), assetHitStatSearchForm.getEndLastLoginTimestamp()));
                }
                if (assetHitStatSearchForm.getUserIdentifierConnectingToAsset() != null) {
                    criteria.add(Restrictions.ilike("userIdentifierConnectingToAsset", assetHitStatSearchForm
                            .getUserIdentifierConnectingToAsset(), MatchMode.ANYWHERE));
                }
                if (assetHitStatSearchForm.getApplicationUsedToConnect() != null) {
                    criteria.add(Restrictions.ilike("applicationUsedToConnect", assetHitStatSearchForm
                            .getApplicationUsedToConnect(), MatchMode.ANYWHERE));
                }
                if (assetHitStatSearchForm.getUserIdentifierFromOS() != null) {
                    criteria.add(Restrictions.ilike("userIdentifierFromOS", assetHitStatSearchForm
                            .getUserIdentifierFromOS(), MatchMode.ANYWHERE));
                }
                if (assetHitStatSearchForm.getHost() != null) {                   
                    
                    criteria.add(Restrictions.ilike("host", StringEscapeUtils.escapeJava(assetHitStatSearchForm.getHost()), MatchMode.ANYWHERE));
                }
                if (assetHitStatSearchForm.getMinNumberOfLogins()!=null && assetHitStatSearchForm.getMaxNumberOfLogins()!=null){
                    criteria.add(Restrictions.between("numberOfLogins", assetHitStatSearchForm.getMinNumberOfLogins(), assetHitStatSearchForm.getMaxNumberOfLogins()));
                }
                if (assetHitStatSearchForm.getConnectingIP() != null) {
                    criteria.add(Restrictions.ilike("connectingIP", assetHitStatSearchForm
                            .getConnectingIP(), MatchMode.ANYWHERE));
                } 
                return criteria.list();
            }
        });

    }

}
