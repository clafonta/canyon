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
import org.tll.canyon.model.AssetType;
import org.tll.canyon.model.EmployeeInfo;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.AssetTypeManager;
import org.tll.canyon.service.EmployeeInfoManager;

/**
 * Handles search and filter function for all Asset Details.
 * @author Chad.Lafontaine
 *
 */
public class AssetDetailListController implements Controller {
	private final Log log = LogFactory
	.getLog(AssetDetailListController.class);
    private AssetDetailManager assetDetailManager = null;
    private EmployeeInfoManager employeeInfoManager = null;
    private AssetTypeManager assetTypeManager;
    public void setAssetTypeManager(AssetTypeManager assetTypeManager) {
		this.assetTypeManager = assetTypeManager;
	}

	public void setEmployeeInfoManager(EmployeeInfoManager employeeInfoManager) {
		this.employeeInfoManager = employeeInfoManager;
	}

	public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
        this.assetDetailManager = assetDetailManager;
    }
	    

	/**
	 * Builds an <code>AssetDetail</code> object from request parameters, then retrieve a
	 * list of similar <code>AssetDetail</code> objects.
	 *
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		AssetDetail exampleAssetDetail = new AssetDetail();
        BeanUtils.populate(exampleAssetDetail, request.getParameterMap());
        // HACK
        
        log.debug("in handleRequest, using as example: " + exampleAssetDetail.toString());
        
        EmployeeInfo prmAdm = employeeInfoManager.getEmployeeInfoByEmailAddress(exampleAssetDetail.getFilterPrimaryAdminEmpEmail());
        EmployeeInfo secAdm = employeeInfoManager.getEmployeeInfoByEmailAddress(exampleAssetDetail.getFilterSecondaryAdminEmpEmail());
        EmployeeInfo prmOwn = employeeInfoManager.getEmployeeInfoByEmailAddress(exampleAssetDetail.getFilterPrimaryOwnerEmpEmail());
        EmployeeInfo secOwn = employeeInfoManager.getEmployeeInfoByEmailAddress(exampleAssetDetail.getFilterSecondaryOwnerEmpEmail());
        
        exampleAssetDetail.setPrimaryAdminEmployeeId((prmAdm!=null) ? prmAdm.getEmployeeUserId() : null);
        exampleAssetDetail.setSecondaryAdminEmployeeId((secAdm!=null) ? secAdm.getEmployeeUserId() : null);
        exampleAssetDetail.setPrimaryOwnerEmployeeId((prmOwn!=null) ? prmOwn.getEmployeeUserId() : null);
        exampleAssetDetail.setSecondaryOwnerEmployeeId((secOwn!=null) ? secOwn.getEmployeeUserId() : null);
        
        List<AssetDetail> assetDetailList = assetDetailManager.getAssetDetails(exampleAssetDetail);
        List<AssetType> assetTypeList = this.assetTypeManager.getAssetTypes(null);
        
        ModelAndView mv = new ModelAndView("assetDetailList");
        mv.addObject(Constants.ASSETDETAIL_LIST, assetDetailList);
        mv.addObject(Constants.ASSETDETAIL_KEY, exampleAssetDetail);
        mv.addObject(Constants.ASSETTYPE_LIST, assetTypeList);
        return mv;
    }
    
}
