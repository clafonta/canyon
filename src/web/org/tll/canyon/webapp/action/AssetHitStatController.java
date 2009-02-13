package org.tll.canyon.webapp.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.tll.canyon.Constants;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetHitStat;
import org.tll.canyon.model.viewwrappers.AssetHitStatSearchForm;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.AssetHitStatManager;
import org.tll.canyon.util.DateConverter;
import org.tll.canyon.webapp.util.RequestUtil;


public class AssetHitStatController implements Controller {
    private final Log log = LogFactory.getLog(AssetHitStatController.class);
    private AssetHitStatManager assetHitStatManager = null;
    private AssetDetailManager assetDetailManager = null;
    private DateConverter dateConverter = new DateConverter();

    public void setAssetHitStatManager(AssetHitStatManager assetHitStatManager) {
        this.assetHitStatManager = assetHitStatManager;
    }

    public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
        this.assetDetailManager = assetDetailManager;
    }

    /**
     * Handles two types of requests.
     * <li>ALL hit statistics based on filter of http parameters</li>
     * <li>Only hit statistics value of 'view' parameter, e.g. associated to detail only?</li>
     *  
     * @param request
     * @param response
     */
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        AssetHitStatSearchForm assetHitStatSearchForm = getAssetHitStatFilterFromRequest(request);
        AssetDetail assetDetail = assetDetailManager.getAssetDetail(assetHitStatSearchForm.getAssetDetailId());
        List<AssetHitStat> assetHitStats = null;
        String viewType = request.getParameter("view");
        if (viewType != null && viewType.equals("detailonly") && assetHitStatSearchForm.getAssetDetailId() != null) {
            // View only hit statistics associated to an AssetDetail

            assetHitStats = assetHitStatManager.getAssetHitStatsByAssetDetail(assetDetail);
        } else {
            // View all hit statistics that closely match all attributes for 
            // all non-AssetDetailId http parameters. 
            // Serves two purposes; a) get all hit stats for list page or b) based
            // on filter from a SEARCh page. 
            assetHitStats = assetHitStatManager.getAssetHitStatsBySearch(assetHitStatSearchForm);
        }

        ModelAndView mv = new ModelAndView("assetHitStatList", Constants.ASSETHITSTAT_LIST, assetHitStats);
        mv.addObject("assetHitStatSearchForm", assetHitStatSearchForm);
        mv.addObject("assetDetail", assetDetail);
        return mv;

    }

    // Helper method. 
    private AssetHitStatSearchForm getAssetHitStatFilterFromRequest(HttpServletRequest request) throws Exception {

        AssetHitStatSearchForm assetHitStatSearchForm = new AssetHitStatSearchForm();
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "userIdentifierConnectingToAsset");
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "connectingIP");
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "userIdentifierFromOS");
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "startLastLoginTimestampString");
        if (assetHitStatSearchForm.getStartLastLoginTimestampString() != null) {
            assetHitStatSearchForm.setStartLastLoginTimestamp((Date) dateConverter.convert(Date.class,
                    assetHitStatSearchForm.getStartLastLoginTimestampString()));
        }
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "endLastLoginTimestampString");
        if (assetHitStatSearchForm.getEndLastLoginTimestampString() != null) {
            assetHitStatSearchForm.setEndLastLoginTimestamp((Date) dateConverter.convert(Date.class, assetHitStatSearchForm
                    .getEndLastLoginTimestampString()));
        }
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "minNumberOfLoginsString");
        if (assetHitStatSearchForm.getMinNumberOfLoginsString() != null) {
            try {
                assetHitStatSearchForm
                        .setMinNumberOfLogins(new Integer(assetHitStatSearchForm.getMinNumberOfLoginsString()));
            } catch (Exception e) {
                // Do nothing.
            }
        }
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "maxNumberOfLoginsString");
        if (assetHitStatSearchForm.getMaxNumberOfLoginsString() != null) {
            try {
                assetHitStatSearchForm
                        .setMaxNumberOfLogins(new Integer(assetHitStatSearchForm.getMaxNumberOfLoginsString()));
            } catch (Exception e) {
                // Do nothing.
            }
        }
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "host");
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "applicationUsedToConnect");
        RequestUtil.setNonEmptyTrimmedProperty(assetHitStatSearchForm, request, "assetDetailId");
        return assetHitStatSearchForm;
    }

}
