package org.tll.canyon.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.tll.canyon.Constants;
import org.tll.canyon.model.User;
import org.tll.canyon.service.UserManager;


/**
 * Simple class to retrieve a list of users from the database.
 *
 * <p>
 * <a href="UserController.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class UserController implements Controller {
    private transient final Log log = LogFactory.getLog(UserController.class);
    private UserManager mgr = null;

    public void setUserManager(UserManager userManager) {
        this.mgr = userManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        User userSearchForm = new User();
        BeanUtils.populate(userSearchForm, request.getParameterMap());
        
        ModelAndView mv = new ModelAndView("userList", Constants.USER_LIST, mgr.getUsers(userSearchForm)); 
        mv.addObject("userSearchForm", userSearchForm);
        return mv;
        
    }
}
