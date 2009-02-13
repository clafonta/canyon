package org.tll.canyon.service.impl;

import java.util.List;

import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.tll.canyon.dao.UserDao;
import org.tll.canyon.model.User;
import org.tll.canyon.service.UserExistsException;
import org.tll.canyon.service.UserManager;


/**
 * Implementation of UserManager interface.</p>
 * 
 * <p>
 * <a href="UserManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class UserManagerImpl extends BaseManager implements UserManager {
    private UserDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setUserDao(UserDao dao) {
        this.dao = dao;
    }

    /**
     * @see org.tll.canyon.service.UserManager#getUser(java.lang.String)
     */
    public User getUser(String userId) {
        return dao.getUser(new Long(userId));
    }

    /**
     * @see org.tll.canyon.service.UserManager#getUsers(org.tll.canyon.model.User)
     */
    public List<User> getUsers(User user) {
        return dao.getUsers(user);
    }

    /**
     * @see org.tll.canyon.service.UserManager#saveUser(org.tll.canyon.model.User)
     */
    public void saveUser(User user) throws UserExistsException {
    	// if new user, lowercase userId
    	if (user.getVersion() == null) {
            user.setUsername(user.getUsername().toLowerCase());
    	}
        try {
            dao.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * @see org.tll.canyon.service.UserManager#removeUser(java.lang.String)
     */
    public void removeUser(String userId) {
        if (log.isDebugEnabled()) {
            log.debug("removing user: " + userId);
        }

        dao.removeUser(new Long(userId));
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return (User) dao.loadUserByUsername(username);
    }
}
