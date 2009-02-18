package org.tll.canyon.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationTrustResolver;
import org.acegisecurity.AuthenticationTrustResolverImpl;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.tll.canyon.Constants;
import org.tll.canyon.model.Role;
import org.tll.canyon.model.User;
import org.tll.canyon.service.RoleManager;
import org.tll.canyon.service.UserExistsException;
import org.tll.canyon.service.UserManager;
import org.tll.canyon.util.StringUtil;
import org.tll.canyon.webapp.util.MessageUtil;
import org.tll.canyon.webapp.util.RequestUtil;

/**
 * Implementation of <strong>SimpleFormController</strong> that interacts with
 * the {@link UserManager} to retrieve/persist values to the database.
 * 
 * <p>
 * <a href="UserFormController.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class UserFormController extends BaseFormController {
	private RoleManager roleManager;

	/**
	 * @param roleManager
	 *            The roleManager to set.
	 */
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public UserFormController() {
		setCommandName("user");
		setCommandClass(User.class);
	}

	public ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (request.getParameter("cancel") != null) {
			if (!StringUtils.equals(request.getParameter("from"), "list")) {
				return new ModelAndView(getCancelView());
			} else {
				return new ModelAndView(getSuccessView());
			}
		}

		return super.processFormSubmission(request, response, command, errors);
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		User user = (User) command;
		Locale locale = request.getLocale();

		if (request.getParameter("delete") != null) {
			getUserManager().removeUser(user.getId().toString());
			MessageUtil.saveMessage(request, getText("user.deleted", user.getFullName(),
					locale));

			return new ModelAndView(getSuccessView());
		} else {
			Boolean encrypt = (Boolean) getConfiguration().get(
					Constants.ENCRYPT_PASSWORD);

			if (StringUtils.equals(request.getParameter("encryptPass"), "true")
					&& (encrypt != null && encrypt.booleanValue())) {

				String algorithm = (String) getConfiguration().get(
						Constants.ENC_ALGORITHM);

				if (algorithm == null) { // should only happen for test case

					if (log.isDebugEnabled()) {
						log
								.debug("assuming testcase, setting algorithm to 'SHA'");
					}

					algorithm = "SHA";
				}

				user.setPassword(StringUtil.encodePassword(user.getPassword(),
						algorithm));
			}

			String[] userRoles = request.getParameterValues("userRoles");

			if (userRoles != null) {
				// for some reason, Spring seems to hang on to the roles in
				// the User object, even though isSessionForm() == false
				user.getRoles().clear();
				for (int i = 0; i < userRoles.length; i++) {
					String roleName = userRoles[i];
					user.addRole(roleManager.getRole(roleName));
				}
			}

			Integer originalVersion = user.getVersion();

			try {
				getUserManager().saveUser(user);
			} catch (UserExistsException e) {
				log
						.warn(e.getMessage()
								+ "WARNING: 'Duplicate entry error' may be mistaken for "
								+ "required non-null fields! User info: \n"
								+ user.toString());

				errors.rejectValue("username", "errors.existing.user",
						new Object[] { user.getUsername(), user.getEmail() },
						"duplicate user");

				// redisplay the unencrypted passwords
				user.setPassword("");
				user.setConfirmPassword("");
				// reset the version # to what was passed in
				user.setVersion(originalVersion);

				return showForm(request, response, errors);
			}

			if (!StringUtils.equals(request.getParameter("from"), "list")) {
				MessageUtil.saveMessage(request, getText("user.saved", user.getFullName(),
						locale));

				// return to main Menu
				return new ModelAndView(new RedirectView("mainMenu.html"));
			} else {
				if (StringUtils.isBlank(request.getParameter("version"))) {
					MessageUtil.saveMessage(request, getText("user.added", user
							.getFullName(), locale));

					// Send an account information e-mail
					message.setSubject(getText("signup.email.subject", locale));
					sendUserMessage(user, getText("newuser.email.message", user
							.getFullName(), locale), RequestUtil
							.getAppURL(request));

					return showNewForm(request, response);
				} else {
					MessageUtil.saveMessage(request, getText("user.updated.byAdmin", user
							.getFullName(), locale));
				}
			}
		}

		return showForm(request, response, errors);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {
		if (request.getRequestURI().indexOf("editProfile") > -1) {
			// if URL is "editProfile" - make sure it's the current user
			// reject if username passed in or "list" parameter passed in
			// someone that is trying this probably knows the AppFuse code
			// but it's a legitimate bug, so I'll fix it. ;-)
			if ((request.getParameter("username") != null)
					|| (request.getParameter("from") != null)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				log.warn("User '" + request.getRemoteUser()
						+ "' is trying to edit user '"
						+ request.getParameter("username") + "'");

				return null;
			}
		}

		// prevent ordinary users from calling a GET on editUser.html
		// unless a bind error exists.
		if ((request.getRequestURI().indexOf("editUser") > -1)
				&& (!request.isUserInRole(Constants.ADMIN_ROLE)
						&& (errors.getErrorCount() == 0) && // be nice to
															// server-side
															// validation for
															// editProfile
				(request.getRemoteUser() != null))) { // be nice to unit tests
			response.sendError(HttpServletResponse.SC_FORBIDDEN);

			return null;
		}

		return super.showForm(request, response, errors);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		if (!isFormSubmission(request)) {
			String username = request.getParameter("username");

			// if user logged in with remember me, display a warning that they
			// can't change passwords
			log.debug("checking for remember me login...");

			AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
			SecurityContext ctx = SecurityContextHolder.getContext();

			if (ctx.getAuthentication() != null) {
				Authentication auth = ctx.getAuthentication();

				if (resolver.isRememberMe(auth)) {
					request.getSession().setAttribute("cookieLogin", "true");

					// add warning message
					MessageUtil.saveMessage(request, getText("userProfile.cookieLogin",
							request.getLocale()));
				}
			}

			User user = null;

			if (request.getRequestURI().indexOf("editProfile") > -1) {
				user = getUserManager().getUserByUsername(
						request.getRemoteUser());
			} else if (!StringUtils.isBlank(username)
					&& !"".equals(request.getParameter("version"))) {
				try {
					user = getUserManager().getUserByUsername(username);
				} catch (UsernameNotFoundException unfe) {
					user = new User();
					user.addRole(new Role(Constants.USER_ROLE));
					user.setUsername(username);
					user.setEmail(request.getParameter("employeeEmail"));
					user.setFirstName(request.getParameter("employeeFirstName"));
					user.setLastName(request.getParameter("employeeLastName"));
					user.setEnabled(true);
					String randomPassword = UserFormController.getPassword(8);
					user.setPassword(randomPassword);
					user.setPasswordHint(randomPassword);
					user.setConfirmPassword(randomPassword);
				}
			} else {
				user = new User();
				user.addRole(new Role(Constants.USER_ROLE));
			}

			// Blow out passwords.
			user.setConfirmPassword("");
			user.setPassword("");
			return user;
		}
		return super.formBackingObject(request);
	}

	protected void onBind(HttpServletRequest request, Object command)
			throws Exception {
		// if the user is being deleted, turn off validation
		if (request.getParameter("delete") != null) {
			super.setValidateOnBinding(false);
		} else {
			super.setValidateOnBinding(true);
		}
	}

	private static String getPassword(int n) {
		char[] pw = new char[n];
		int c = 'A';
		int r1 = 0;
		for (int i = 0; i < n; i++) {
			r1 = (int) (Math.random() * 3);
			switch (r1) {
			case 0:
				c = '0' + (int) (Math.random() * 10);
				break;
			case 1:
				c = 'a' + (int) (Math.random() * 26);
				break;
			case 2:
				c = 'A' + (int) (Math.random() * 26);
				break;
			}
			pw[i] = (char) c;
		}
		return new String(pw);
	}
	
}
