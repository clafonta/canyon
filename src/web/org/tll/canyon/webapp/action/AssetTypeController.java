package org.tll.canyon.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.tll.canyon.Constants;
import org.tll.canyon.model.AssetType;
import org.tll.canyon.service.AssetTypeManager;


public class AssetTypeController implements Controller {
    private final Log log = LogFactory.getLog(AssetTypeController.class);
    private AssetTypeManager assetTypeManager = null;
    
    public void setAssetTypeManager(AssetTypeManager assetTypeManager) {
        this.assetTypeManager = assetTypeManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        AssetType assetType = new AssetType();
        
        // populate object with request parameters
        BeanUtils.populate(assetType, request.getParameterMap());

        List<AssetType> assetTypes = assetTypeManager.getAssetTypes(assetType);
        ModelAndView mv = new ModelAndView("assetTypeList", Constants.ASSETTYPE_LIST, assetTypes);
        
        
        return mv;
    }

   
}
