
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
import org.tll.canyon.dao.EmployeeInfoDao;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.EmployeeInfo;


public class EmployeeInfoDaoHibernate extends BaseDaoHibernate implements EmployeeInfoDao {

    /**
     * @see org.tll.canyon.dao.EmployeeInfoDao#getEmployeeInfos(org.tll.canyon.model.EmployeeInfo)
     */
    public List<EmployeeInfo> getEmployeeInfoList(final EmployeeInfo employeeInfo) {
        if (employeeInfo == null) {
            return getHibernateTemplate().find("from EmployeeInfo");
        } else {
            // filter on properties set in the employeeInfo
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(employeeInfo).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(EmployeeInfo.class).add(ex).add(Restrictions.ilike("employeeUserId", employeeInfo.getEmployeeUserId(),
                            MatchMode.ANYWHERE)).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
    }

    /**
     * @see org.tll.canyon.dao.EmployeeInfoDao#getEmployeeInfo(String id)
     */
    public EmployeeInfo getEmployeeInfo(final String id) {
        EmployeeInfo employeeInfo = (EmployeeInfo) getHibernateTemplate().get(EmployeeInfo.class, id);
        if (employeeInfo == null) {
            log.warn("uh oh, employeeInfo with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(EmployeeInfo.class, id);
        }

        return employeeInfo;
     
    }
    
    @SuppressWarnings("unchecked")
    public EmployeeInfo getEmployeeInfoByEmailAddress(final String emailAddress){
    	
    	List<EmployeeInfo> list = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(EmployeeInfo.class);
                criteria.add(Restrictions.eq("employeeEmail", emailAddress));               
                return criteria.list();
            }
        });
        if(list!=null && list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }
    
    /**
     * @see org.tll.canyon.dao.EmployeeInfoDao#saveEmployeeInfo(EmployeeInfo employeeInfo)
     */    
    public void saveEmployeeInfo(final EmployeeInfo employeeInfo) {
        getHibernateTemplate().saveOrUpdate(employeeInfo);
    }

    /**
     * @see org.tll.canyon.dao.EmployeeInfoDao#removeEmployeeInfo(String employeeUserId)
     */
    public void removeEmployeeInfo(final String employeeUserId) {
        getHibernateTemplate().delete(getEmployeeInfo(employeeUserId));
    }
}
