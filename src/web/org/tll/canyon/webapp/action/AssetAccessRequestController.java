package org.tll.canyon.webapp.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.tll.canyon.Constants;
import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.StatusType;
import org.tll.canyon.model.viewwrappers.AssetAccessRequestSearchForm;
import org.tll.canyon.service.AssetAccessRequestManager;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.util.DateConverter;
import org.tll.canyon.webapp.util.RequestUtil;


/**
 * 
 * @author U159282
 *
 */
public class AssetAccessRequestController implements Controller {
    //private final Log log = LogFactory.getLog(AssetAccessRequestController.class);
    private AssetAccessRequestManager assetAccessRequestManager = null;
    private AssetDetailManager assetDetailManager = null;
    private DateConverter dateConverter = new DateConverter();

    public void setAssetAccessRequestManager(AssetAccessRequestManager assetAccessRequestManager) {
        this.assetAccessRequestManager = assetAccessRequestManager;
    }

    public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
        this.assetDetailManager = assetDetailManager;
    }

    /**
     * Supports filtered list view of all <code>AssetAccessRequest</code> objects. By default, 
     * filters on complete = FALSE view.
     * 
     * @see AssetAccessRequest#isComplete()
     * @see AssetAccessRequestSearchForm#setComplete(boolean)
     */
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        AssetAccessRequestSearchForm assetAccessRequestSearchForm = null;
        
        if (request.getParameter("search") != null) {
            // 1) REQUEST USING SEARCH FILTER
            assetAccessRequestSearchForm = getAssetAccessRequestSearchFormFromRequestParams(request);

        } else {
            // 2) DEFAULT SEARCH; FILTER ON complete = FALSE
            assetAccessRequestSearchForm = new AssetAccessRequestSearchForm();
            assetAccessRequestSearchForm.setComplete(new Boolean(false));
        }
        List<AssetAccessRequest> assetAccessRequests = assetAccessRequestManager
                .getAssetAccessRequestsBySearch(assetAccessRequestSearchForm);
                
        ModelAndView mv = new ModelAndView("assetAccessRequestList");
        mv.addObject(Constants.ASSETACCESSREQUEST_LIST, assetAccessRequests);
        mv.addObject(Constants.ASSETDETAIL_LIST, assetDetailManager.getAssetDetails(new AssetDetail()));
        mv.addObject(Constants.ASSETACCESSSEARCHFORMREQUEST_KEY, assetAccessRequestSearchForm);
        return mv;
    }

    /**
     * Extracts input values from search form and gives state to view wrapper. 
     *  
     * @param request
     * @return
     * @throws Exception
     */
    private AssetAccessRequestSearchForm getAssetAccessRequestSearchFormFromRequestParams(HttpServletRequest request)
            throws Exception {
        AssetAccessRequestSearchForm assetAccessRequestSearchForm = new AssetAccessRequestSearchForm();
        RequestUtil.setNonEmptyTrimmedProperty(assetAccessRequestSearchForm, request, "userIdentifier");
        RequestUtil.setNonEmptyTrimmedProperty(assetAccessRequestSearchForm, request, "userEmailAddress");
        RequestUtil.setNonEmptyTrimmedProperty(assetAccessRequestSearchForm, request, "queueEmail");
        RequestUtil.setNonEmptyTrimmedProperty(assetAccessRequestSearchForm, request, "startCompleteDateString");
        RequestUtil.setNonEmptyTrimmedProperty(assetAccessRequestSearchForm, request, "endCompleteDateString");
        RequestUtil.setNonEmptyTrimmedProperty(assetAccessRequestSearchForm, request, "assetRole");
        RequestUtil.setNonEmptyTrimmedProperty(assetAccessRequestSearchForm, request, "assetName");

        String completeFilter = request.getParameter("complete");
        if (completeFilter != null && !"ALL".equalsIgnoreCase(completeFilter.trim())) {
            if (StatusType.COMPLETED.toString().equalsIgnoreCase(completeFilter.trim())) {
                assetAccessRequestSearchForm.setComplete(new Boolean(true));
            } else {
                assetAccessRequestSearchForm.setComplete(new Boolean(false));
            }
        }
        if (assetAccessRequestSearchForm.getStartCompleteDateString() != null) {
            assetAccessRequestSearchForm.setStartCompleteDate((Date) dateConverter.convert(Date.class,
                    assetAccessRequestSearchForm.getStartCompleteDateString()));
        }
        if (assetAccessRequestSearchForm.getEndCompleteDateString() != null) {
            assetAccessRequestSearchForm.setEndCompleteDate((Date) dateConverter.convert(Date.class,
                    assetAccessRequestSearchForm.getEndCompleteDateString()));
        }
        return assetAccessRequestSearchForm;
    }
}
