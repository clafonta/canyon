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
import org.tll.canyon.model.AssetAttribute;
import org.tll.canyon.service.AssetAttributeManager;


public class AssetAttributeController implements Controller {
    private final Log log = LogFactory.getLog(AssetAttributeController.class);
    private AssetAttributeManager assetAttributeManager = null;
    
    public void setAssetAttributeManager(AssetAttributeManager assetAttributeManager) {
        this.assetAttributeManager = assetAttributeManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        AssetAttribute assetAttribute = new AssetAttribute();
        
        // populate object with request parameters
        BeanUtils.populate(assetAttribute, request.getParameterMap());

        List<AssetAttribute> assetAttributes = assetAttributeManager.getAssetAttributes(assetAttribute);
        ModelAndView mv = new ModelAndView("assetAttributeList", Constants.ASSETATTRIBUTE_LIST, assetAttributes);
        
        
        return mv;
    }

   
}
