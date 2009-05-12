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

/**
 * Handles search and filter function for all Asset Details.
 * @author Chad.Lafontaine
 *
 */
public class AssetAttributeListController implements Controller {
	private final Log log = LogFactory
	.getLog(AssetAttributeListController.class);
    private AssetTypeManager assetTypeManager = null;
    
        
    

	public void setAssetTypeManager(AssetTypeManager assetTypeManager) {
        this.assetTypeManager = assetTypeManager;
    }

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		AssetType example = new AssetType();
        BeanUtils.populate(example, request.getParameterMap());
        // HACK
        
        
        List<AssetType> list = assetTypeManager.getAssetTypes(example);
        
        
        ModelAndView mv = new ModelAndView("assetTypeList");
        mv.addObject(Constants.ASSETTYPE_LIST, list);
        mv.addObject(Constants.ASSETTYPE_KEY, example);
        return mv;
    }
    
}
