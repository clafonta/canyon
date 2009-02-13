package org.tll.canyon.webapp.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.tll.canyon.Constants;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.EmployeeInfo;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.EmployeeInfoManager;


public class AssetDetailListController implements Controller {
    
    private AssetDetailManager assetDetailManager = null;
    private EmployeeInfoManager employeeInfoManager = null;
        
    public void setEmployeeInfoManager(EmployeeInfoManager employeeInfoManager) {
		this.employeeInfoManager = employeeInfoManager;
	}

	public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
        this.assetDetailManager = assetDetailManager;
    }
	    

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	AssetDetail assetDetailSearchForm = new AssetDetail();
        BeanUtils.populate(assetDetailSearchForm, request.getParameterMap());
        
        EmployeeInfo prmAdm = employeeInfoManager.getEmployeeInfoByEmailAddress(assetDetailSearchForm.getFilterPrimaryAdminEmpEmail());
        EmployeeInfo secAdm = employeeInfoManager.getEmployeeInfoByEmailAddress(assetDetailSearchForm.getFilterSecondaryAdminEmpEmail());
        EmployeeInfo prmOwn = employeeInfoManager.getEmployeeInfoByEmailAddress(assetDetailSearchForm.getFilterPrimaryOwnerEmpEmail());
        EmployeeInfo secOwn = employeeInfoManager.getEmployeeInfoByEmailAddress(assetDetailSearchForm.getFilterSecondaryOwnerEmpEmail());
        
        AssetDetail exampleAssetDetail = new AssetDetail();
        exampleAssetDetail.setAssetAdminTeamEmail(assetDetailSearchForm.getAssetAdminTeamEmail());
        exampleAssetDetail.setAssetAdminTeamName(assetDetailSearchForm.getAssetAdminTeamName());
        exampleAssetDetail.setPrimaryAdminEmployeeId((prmAdm!=null) ? prmAdm.getEmployeeUserId() : null);
        exampleAssetDetail.setSecondaryAdminEmployeeId((secAdm!=null) ? secAdm.getEmployeeUserId() : null);
        exampleAssetDetail.setPrimaryOwnerEmployeeId((prmOwn!=null) ? prmOwn.getEmployeeUserId() : null);
        exampleAssetDetail.setSecondaryOwnerEmployeeId((secOwn!=null) ? secOwn.getEmployeeUserId() : null);
        
        List<AssetDetail> assetDetailList = assetDetailManager.getAssetDetails(exampleAssetDetail);
        
        
        ModelAndView mv = new ModelAndView("assetDetailList");
        mv.addObject(Constants.ASSETDETAIL_LIST, assetDetailList);
        mv.addObject(Constants.ASSETDETAIL_KEY, assetDetailSearchForm);
        return mv;
    }
    
}
