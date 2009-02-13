package org.tll.canyon.dao.hibernate;

import java.util.List;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.tll.canyon.dao.UserDao;
import org.tll.canyon.model.User;


/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 *
 * <p><a href="UserDaoHibernate.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *   Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *   Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
*/
public class UserDaoHibernate extends BaseDaoHibernate implements UserDao, UserDetailsService {
    /**
     * @see org.tll.canyon.dao.UserDao#getUser(Long)
     */
    public User getUser(Long userId) {
        User user = (User) getHibernateTemplate().get(User.class, userId);

        if (user == null) {
            log.warn("uh oh, user '" + userId + "' not found...");
            throw new ObjectRetrievalFailureException(User.class, userId);
        }

        return user;
    }

    /**
     * @see org.tll.canyon.dao.UserDao#getUsers(org.tll.canyon.model.User)
     */
    public List getUsers(final User user) {        
        
        if (user == null) {
            return getHibernateTemplate().find("from User u order by upper(u.username)");
        } else {
            // filter on properties set in the employeeInfo
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    
                    Criteria criteria = session.createCriteria(User.class);
                    if (user.getEmail() != null) {
                        criteria.add(Restrictions.ilike("email", user.getEmail(), MatchMode.ANYWHERE));
                    }
                    if (user.getFirstName() != null) {
                        criteria.add(Restrictions.ilike("firstName", user.getFirstName(), MatchMode.ANYWHERE));
                    }
                    if (user.getLastName() != null) {
                        criteria.add(Restrictions.ilike("lastName", user.getLastName(), MatchMode.ANYWHERE));
                    }
                    if (user.getUsername() != null) {
                        criteria.add(Restrictions.ilike("username", user.getUsername(), MatchMode.ANYWHERE));
                    }
                    return criteria.list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
    }

    /**
     * @see org.tll.canyon.dao.UserDao#saveUser(org.tll.canyon.model.User)
     */
    public void saveUser(final User user) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + user.getId());
        }
        
        getHibernateTemplate().saveOrUpdate(user);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getHibernateTemplate().flush();
    }

    /**
     * @see org.tll.canyon.dao.UserDao#removeUser(Long)
     */
    public void removeUser(Long userId) {
        getHibernateTemplate().delete(getUser(userId));
    }

    /** 
    * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
    */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List users = getHibernateTemplate().find("from User where username=?", username);
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            return (UserDetails) users.get(0);
        }
    }
}
