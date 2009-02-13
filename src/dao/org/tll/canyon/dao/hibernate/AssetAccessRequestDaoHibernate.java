package org.tll.canyon.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.tll.canyon.dao.AssetAccessRequestDao;
import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.model.viewwrappers.AssetAccessRequestSearchForm;
import org.tll.canyon.model.viewwrappers.AssetHitStatSearchForm;


public class AssetAccessRequestDaoHibernate extends BaseDaoHibernate implements AssetAccessRequestDao {

    /**
     * @see org.tll.canyon.dao.AssetAccessRequestDao#getAssetAccessRequests(org.tll.canyon.model.AssetAccessRequest)
     */
    @SuppressWarnings("unchecked")
    public List<AssetAccessRequest> getAssetAccessRequests(final AssetAccessRequest example) {
        // return getHibernateTemplate().find("from AssetAccessRequest");
        if (example == null) {
            return getHibernateTemplate().find("from AssetAccessRequest");
        } else {
            // filter on properties set in the assetRole
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {

                    Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AssetAccessRequest.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
    }

    /**
     * @see org.tll.canyon.dao.AssetAccessRequestDao#getAssetAccessRequest(Long
     *      id)
     */
    public AssetAccessRequest getAssetAccessRequest(final Long id) {
        AssetAccessRequest assetAccessRequest = (AssetAccessRequest) getHibernateTemplate()
                .get(AssetAccessRequest.class, id);
        if (assetAccessRequest == null) {
            log.warn("uh oh, assetAccessRequest with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(AssetAccessRequest.class, id);
        }

        return assetAccessRequest;
    }

    /**
     * @see org.tll.canyon.dao.AssetAccessRequestDao#saveAssetAccessRequest(AssetAccessRequest
     *      assetAccessRequest)
     */
    public void saveAssetAccessRequest(final AssetAccessRequest assetAccessRequest) {
        getHibernateTemplate().saveOrUpdate(assetAccessRequest);
    }

    /**
     * @see org.tll.canyon.dao.AssetAccessRequestDao#removeAssetAccessRequest(Long
     *      id)
     */
    public void removeAssetAccessRequest(final Long id) {
        getHibernateTemplate().delete(getAssetAccessRequest(id));
    }

    /**
     * @see org.tll.canyon.dao.AssetAccessRequestDao#getAssetAccessRequestsBySearch(AssetHitStatSearchForm)
     */
    @SuppressWarnings("unchecked")
    public List<AssetAccessRequest> getAssetAccessRequestsBySearch(
            final AssetAccessRequestSearchForm assetAccessRequestSearchForm) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(AssetAccessRequest.class);
                
                if (assetAccessRequestSearchForm.getStartCompleteDate() != null
                        && assetAccessRequestSearchForm.getEndCompleteDate() != null) {
                    criteria.add(Restrictions.between("completeDate", assetAccessRequestSearchForm.getStartCompleteDate(),
                            assetAccessRequestSearchForm.getEndCompleteDate()));
                }
                
                if (assetAccessRequestSearchForm.getAssetAdministrationEmail() != null) {
                    criteria.add(Restrictions.ilike("assetAdministrationEmail", assetAccessRequestSearchForm.getAssetAdministrationEmail(),
                            MatchMode.ANYWHERE));
                }
                if (assetAccessRequestSearchForm.getEmployeeUserId() != null) {
                    criteria.add(Restrictions.ilike("employeeUserId", assetAccessRequestSearchForm.getEmployeeUserId(),
                            MatchMode.ANYWHERE));
                }
                if (assetAccessRequestSearchForm.isComplete() != null) {
                    criteria.add(Restrictions.eq("complete", assetAccessRequestSearchForm.isComplete()));
                }
                // 
                if (assetAccessRequestSearchForm.getAssetRole() != null
                        && assetAccessRequestSearchForm.getAssetName() != null) {

                    criteria.createCriteria("assetRole").add(
                            Restrictions.eq("name", assetAccessRequestSearchForm.getAssetRole())).createCriteria(
                            "assetDetail").add(Restrictions.eq("assetName", assetAccessRequestSearchForm.getAssetName()));
                } else if (assetAccessRequestSearchForm.getAssetName() != null) {
                    criteria.createCriteria("assetRole").createCriteria("assetDetail").add(
                            Restrictions.eq("assetName", assetAccessRequestSearchForm.getAssetName()));
                } else if (assetAccessRequestSearchForm.getAssetRole() != null) {
                    criteria.createCriteria("assetRole").add(
                            Restrictions.eq("name", assetAccessRequestSearchForm.getAssetRole()));
                }
                return criteria.list();
            }
        });
    }
}
