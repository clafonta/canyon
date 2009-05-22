package org.tll.canyon.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.tll.canyon.Constants;
import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetType;
import org.tll.canyon.model.viewwrappers.AssetAccessRequestSearchForm;
import org.tll.canyon.service.AssetAccessRequestManager;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.AssetTypeManager;


public class AssetDetailController implements Controller {
    private final Log log = LogFactory.getLog(AssetDetailController.class);
    private AssetDetailManager assetDetailManager = null;
    private AssetAccessRequestManager assetAccessRequestManager = null;
    private AssetTypeManager assetTypeManager = null;
    
    public void setAssetTypeManager(AssetTypeManager assetTypeManager) {
		this.assetTypeManager = assetTypeManager;
	}

	public void setAssetAccessRequestManager(AssetAccessRequestManager assetAccessRequestManager) {
        this.assetAccessRequestManager = assetAccessRequestManager;
    }

    public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
        this.assetDetailManager = assetDetailManager;
    }

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		
        String id = request.getParameter("id");        
        ModelAndView mv = new ModelAndView("assetDetail");  
        AssetDetail assetDetail = null;
        if (!StringUtils.isEmpty(id)) {
            assetDetail = assetDetailManager.getAssetDetail(id);
            
        } else {
            assetDetail = new AssetDetail();
        }        
              
        mv.addObject(Constants.ASSETDETAIL_KEY, assetDetail);
        
        
        
        // If the asset does exist, we also want to display all
        // users who have access.
        if(!StringUtils.isEmpty(id)){
            log.debug("Retrieving all User Asset Access Request information for asset detail id: '"+id+"'");
            AssetAccessRequestSearchForm assetAccessRequestSearchForm = new AssetAccessRequestSearchForm();
            assetAccessRequestSearchForm.setAssetName(assetDetail.getAssetName());
            List<AssetAccessRequest> items = this.assetAccessRequestManager.getAssetAccessRequestsBySearch(assetAccessRequestSearchForm);
            mv.addObject(Constants.ASSETACCESSREQUEST_LIST, items);
        }
        
        List<AssetType> assetTypeList = this.assetTypeManager.getAssetTypes(new AssetType());
        mv.addObject(Constants.ASSETTYPE_LIST, assetTypeList);
        return mv;
    }

    
   
}
