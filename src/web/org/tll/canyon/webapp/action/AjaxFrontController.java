package org.tll.canyon.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.viewwrappers.AssetDetailInfo;
import org.tll.canyon.service.AssetDetailManager;

public class AjaxFrontController extends MultiActionController {
    private transient final Log log = LogFactory.getLog(AjaxFrontController.class);

    private AssetDetailManager assetDetailManager = null;

    public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
		this.assetDetailManager = assetDetailManager;
	}
    
	/**
	 * Only used for testing this tutorial !
	 */
    public ModelAndView ajaxFrontDemo(HttpServletRequest request,
                                            HttpServletResponse response) {

      return new ModelAndView("demoUser", "dummy", null);
    }



    public ModelAndView ajaxFrontGetAssetDetails(HttpServletRequest request,
                                            HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("Entering method...");
        }
        String arg = request.getParameter("searchTerm");
        log.debug("AJAX - Fetching %" + arg + "%");
        AssetDetail assetDetail = new AssetDetail();
		assetDetail.setAssetName(arg);
		assetDetail.setAssetAddress(arg);
		assetDetail.setAssetDescription(arg);
		assetDetail.setAssetLocation(arg);
		assetDetail.setAssetUsageType(arg);
		
		List<AssetDetail> assetDetailList = this.assetDetailManager.getAssetDetails(assetDetail);
		List<AssetDetailInfo> assetDetailInfoList = new ArrayList<AssetDetailInfo>();
		for(Iterator<AssetDetail> iter = assetDetailList.iterator(); iter.hasNext();){
			AssetDetail assetDetailTemp =  iter.next();
			AssetDetailInfo info = new AssetDetailInfo(assetDetailTemp);
			assetDetailInfoList.add(info);
		}
		
        return new ModelAndView("assetDetailInfoXML", "assetDetailInfoList", assetDetailInfoList);
    }


}
