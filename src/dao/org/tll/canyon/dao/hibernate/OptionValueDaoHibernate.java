
package org.tll.canyon.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.tll.canyon.dao.OptionValueDao;
import org.tll.canyon.model.OptionValue;


public class OptionValueDaoHibernate extends BaseDaoHibernate implements OptionValueDao {

    /**
     * @see org.tll.canyon.dao.OptionValueDao#getOptionValues(org.tll.canyon.model.OptionValue)
     */
    @SuppressWarnings("unchecked")
    public List getOptionValues(final OptionValue example) {
        
        if (example == null) {
            return getHibernateTemplate().find("from OptionValue");
        } else {
            // filter on properties set in the optionValue
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                   
                    Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(OptionValue.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
       
        
    }

    /**
     * @see org.tll.canyon.dao.OptionValueDao#getOptionValue(Long id)
     */
    public OptionValue getOptionValue(final Long id) {
        OptionValue optionValue = (OptionValue) getHibernateTemplate().get(OptionValue.class, id);
        if (optionValue == null) {
            log.warn("uh oh, optionValue with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(OptionValue.class, id);
        }

        return optionValue;
    }

    /**
     * @see org.tll.canyon.dao.OptionValueDao#saveOptionValue(OptionValue optionValue)
     */    
    public void saveOptionValue(final OptionValue optionValue) {
        getHibernateTemplate().saveOrUpdate(optionValue);
    }

    /**
     * @see org.tll.canyon.dao.OptionValueDao#removeOptionValue(Long id)
     */
    public void removeOptionValue(final Long id) {
        getHibernateTemplate().delete(getOptionValue(id));
    }
}
