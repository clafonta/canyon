package org.tll.canyon;


/**
 * Constant values used throughout the application.
 *
 * <p>
 * <a href="Constants.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class Constants {
    //~ Static fields/initializers =============================================
   
    /** The name of the ResourceBundle used in this application */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /** The encryption algorithm key to be used for passwords */
    public static final String ENC_ALGORITHM = "algorithm";

    /** A flag to indicate if passwords should be encrypted */
    public static final String ENCRYPT_PASSWORD = "encryptPassword";

    /** File separator from System properties */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /** User home from System properties */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /** The name of the configuration hashmap stored in application scope. */
    public static final String CONFIG = "appConfig";

    /** 
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */ 
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts.action.LOCALE";
    
    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "admin";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "user";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME = "csstheme";
    
    public static final String ASSETDETAIL_LIST = "assetDetailList";
    public static final String ASSETDETAIL_KEY = "assetDetail";
    public static final String ASSETDETAILSEARCHFORM_KEY = "assetDetailSearchForm";
    public static final String ASSETHITSTAT_LIST = "assetHitStatList";

    /**
     * The request scope attribute that holds the assetRole form.
     */
    public static final String ASSETROLE_KEY = "assetRoleForm";

    /**
     * The request scope attribute that holds the assetRole list
     */
    public static final String ASSETROLE_LIST = "assetRoleList";

    /**
     * The request scope attribute that holds the assetAccessRequest form.
     */
    public static final String ASSETACCESSREQUEST_KEY = "assetAccessRequest";
    public static final String ASSETACCESSSEARCHFORMREQUEST_KEY = "assetAccessRequestSearchForm";
    
    /**
     * The request scope attribute that holds the assetAccessRequest list
     */
    public static final String ASSETACCESSREQUEST_LIST = "assetAccessRequestList";


    /**
     * The request scope attribute that holds the assetAccessRequestStatus form.
     */
    public static final String ASSETACCESSREQUESTSTATUS_KEY = "assetAccessRequestStatus";

    /**
     * The request scope attribute that holds the assetAccessRequestStatus list
     */
    public static final String ASSETACCESSREQUESTSTATUS_LIST = "assetAccessRequestStatusList";

    /**
     * The request scope attribute that holds the employeeInfo form.
     */
    public static final String EMPLOYEEINFO_FORM = "employeeInfoForm";

    /**
     * The request scope attribute that holds the employeeInfo list
     */
    public static final String EMPLOYEEINFO_LIST = "employeeInfoList";

    /**
     * 
     */
    public static final String EMPLOYEEINFO_KEY = "employeeInfo";

}







