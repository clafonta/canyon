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
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetRole;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.AssetRoleManager;


public class AssetRoleController implements Controller {
    private final Log log = LogFactory.getLog(AssetRoleController.class);
    private AssetRoleManager assetRoleManager = null;
    private AssetDetailManager assetDetailManager = null;
    
    public void setAssetRoleManager(AssetRoleManager assetRoleManager) {
        this.assetRoleManager = assetRoleManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        AssetRole assetRole = new AssetRole();
        String assetDetailId = request.getParameter("assetDetailId");
        // populate object with request parameters
        BeanUtils.populate(assetRole, request.getParameterMap());

        List<AssetRole> assetRoles = assetRoleManager.getAssetRoles(assetRole);
        ModelAndView mv = new ModelAndView("assetRoleList", Constants.ASSETROLE_LIST, assetRoles);
        
        // If there isn't an AssetDetail ID, then just display all AssetRoles
        if(assetDetailId!=null){
            AssetDetail assetDetail = assetDetailManager.getAssetDetail(assetDetailId);
            mv.addObject(Constants.ASSETDETAIL_KEY, assetDetail);
        }
        return mv;
    }

    public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
        this.assetDetailManager = assetDetailManager;
    }
}
