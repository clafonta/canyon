package org.tll.canyon.webapp.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.tll.canyon.Constants;
import org.tll.canyon.model.AssetAttribute;
import org.tll.canyon.model.AssetAttributeValue;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetType;
import org.tll.canyon.model.EmployeeInfo;
import org.tll.canyon.service.AssetAttributeManager;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.AssetTypeManager;
import org.tll.canyon.service.EmployeeInfoManager;
import org.tll.canyon.webapp.util.MessageUtil;

public class AssetDetailFormController extends BaseFormController {
	private static final String ASSET_ATTRIBUTE_PREFIX = "assetAttribute_";
	
	private AssetDetailManager assetDetailManager = null;
	private EmployeeInfoManager employeeInfoManager = null;
	private AssetTypeManager assetTypeManager = null;
	private AssetAttributeManager assetAttributeManager = null;

	public void setAssetAttributeManager(AssetAttributeManager assetAttributeManager) {
		this.assetAttributeManager = assetAttributeManager;
	}

	public void setAssetTypeManager(AssetTypeManager assetTypeManager) {
		this.assetTypeManager = assetTypeManager;
	}

	public void setEmployeeInfoManager(EmployeeInfoManager employeeInfoManager) {
		this.employeeInfoManager = employeeInfoManager;
	}

	public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
		this.assetDetailManager = assetDetailManager;
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		
		AssetDetail assetDetail = null;
		if (!StringUtils.isEmpty(id)) {
			assetDetail = assetDetailManager.getAssetDetail(id);
		} else {
			String assetTypeId = request.getParameter("assetTypeId");
			AssetType assetType = this.assetTypeManager.getAssetType(assetTypeId);
			assetDetail = new AssetDetail();
			assetDetail.setAssetType(assetType);
			
			List<AssetAttributeValue> assetAttributeValueList = new ArrayList<AssetAttributeValue>();
			List<AssetAttribute> assetAttributeList = assetType.getAssetAttributeList();
			Iterator<AssetAttribute> iter = assetAttributeList.iterator();
			while (iter.hasNext()) {
				AssetAttribute assetAttribute = iter.next();
				AssetAttributeValue value = new AssetAttributeValue();
				value.setAssetAttributeId(assetAttribute.getId());
				value.setAssetAttribute(assetAttribute);
				assetAttributeValueList.add(value);
			}
			assetDetail.setAssetAttributeValueList(assetAttributeValueList);
		}

		return assetDetail;
	}

	public ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (request.getParameter("cancel") != null) {
			return new ModelAndView(new RedirectView(getSuccessView()));
		}

		return super.processFormSubmission(request, response, command, errors);
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		String assetTypeId = request.getParameter("assetTypeId");
		AssetType assetType = this.assetTypeManager.getAssetType(assetTypeId);
		AssetDetail assetDetail = (AssetDetail) command;
		assetDetail.setAssetType(assetType);
		boolean isNew = (assetDetail.getId() == null);
		
		//**********************************************
		// UPDATE THE DYNAMIC ATTRIBUTE VALUES
		//**********************************************
		List<String> assetAttributeIdList = extractIds(request,ASSET_ATTRIBUTE_PREFIX);
		List<AssetAttributeValue> _newAssetAttributeValueList = new ArrayList<AssetAttributeValue>();
		// VALIDATION - PASS!
		if (!isNew) {
			// IF NOT NEW, THEN UPDATE ALL ATTRIBUTE VALUES
			Iterator<String> iter = assetAttributeIdList.iterator();
			while(iter.hasNext()){
				String assetAttributeId = iter.next();
				String value = request.getParameter(ASSET_ATTRIBUTE_PREFIX+assetAttributeId);
				AssetAttributeValue assetAttributeValue = assetDetail.getAssetAttributeValueWithMatchingId(new Long(assetAttributeId));
				assetAttributeValue.setValue(value);
				_newAssetAttributeValueList.add(assetAttributeValue);	
			}
		}else {
			// ELSE, CREATE NEW ATTRIBUTE VALUES
			List<AssetAttribute> assetAttributeList = assetDetail.getAssetType().getAssetAttributeList();
			Iterator<AssetAttribute> iter = assetAttributeList.iterator();
			while (iter.hasNext()) {
				AssetAttribute assetAttribute = (AssetAttribute) iter.next();
				AssetAttributeValue assetAttributeValue = new AssetAttributeValue();
				assetAttributeValue.setAssetAttribute(assetAttribute);
				assetAttributeValue.setAssetAttributeId(assetAttribute.getId());
				assetAttributeValue.setValue(request.getParameter(ASSET_ATTRIBUTE_PREFIX+assetAttribute.getId()));
				_newAssetAttributeValueList.add(assetAttributeValue);
			}
		}
		assetDetail.setAssetAttributeValueList(_newAssetAttributeValueList);
		
		
		
		
		String success = getSuccessView();
		Locale locale = request.getLocale();

		if (request.getParameter("delete") != null) {
			assetDetailManager
					.removeAssetDetail(assetDetail.getId().toString());
			MessageUtil.saveMessage(request, getText("assetDetail.deleted", locale));
		} else {

			// 1. No AssetDetail can have duplicate asset name.
			// 2. Before creating a NEW asset, let's verify no duplicate NAME
			// 3. For an non-new asset being edited, let's ensure no duplicate
			// NAME
			AssetDetail duplicateAssetDetail = assetDetailManager
					.getAssetDetailByName(assetDetail.getAssetName().trim());

			if (duplicateAssetDetail != null
					&& (assetDetail.getId() != null
							&& !assetDetail.getId().equals(
									duplicateAssetDetail.getId()) || (assetDetail
							.getId() == null))) {

				ModelAndView mv = new ModelAndView("editAssetDetail.html");
				mv.addObject(Constants.ASSETDETAIL_KEY, assetDetail);
				MessageUtil.saveError(request, getText("assetDetail.exists.assetName",
						locale));
				return mv;
			}

			// 4. We map each email address to a valid Employee ID. If invalid,
			// then error
			EmployeeInfo prmAdm = employeeInfoManager
					.getEmployeeInfoByEmailAddress(assetDetail
							.getFilterPrimaryAdminEmpEmail());
			EmployeeInfo secAdm = employeeInfoManager
					.getEmployeeInfoByEmailAddress(assetDetail
							.getFilterSecondaryAdminEmpEmail());
			EmployeeInfo prmOwn = employeeInfoManager
					.getEmployeeInfoByEmailAddress(assetDetail
							.getFilterPrimaryOwnerEmpEmail());
			EmployeeInfo secOwn = employeeInfoManager
					.getEmployeeInfoByEmailAddress(assetDetail
							.getFilterSecondaryOwnerEmpEmail());

			boolean inactiveEmployees = false;
			if (prmAdm == null
					|| !EmployeeInfo.EMP_STATUS_ACTIVE.equalsIgnoreCase(prmAdm
							.getEmployeeStatus())) {
				errors.rejectValue("filterPrimaryAdminEmpEmail",
						"filterPrimaryAdminEmpEmail");
				inactiveEmployees = true;
				prmAdm = new EmployeeInfo();
			}
			if (secAdm == null
					|| !EmployeeInfo.EMP_STATUS_ACTIVE.equalsIgnoreCase(secAdm
							.getEmployeeStatus())) {
				errors.rejectValue("filterSecondaryAdminEmpEmail",
						"filterSecondaryAdminEmpEmail");
				inactiveEmployees = true;
				secAdm = new EmployeeInfo();
				
			}
			if (prmOwn == null
					|| !EmployeeInfo.EMP_STATUS_ACTIVE.equalsIgnoreCase(prmOwn
							.getEmployeeStatus())) {
				errors.rejectValue("filterPrimaryOwnerEmpEmail",
						"filterPrimaryOwnerEmpEmail");
				inactiveEmployees = true;
				prmOwn = new EmployeeInfo();
			}
			if (secOwn == null
					|| !EmployeeInfo.EMP_STATUS_ACTIVE.equalsIgnoreCase(secOwn
							.getEmployeeStatus())) {
				errors.rejectValue("filterSecondaryOwnerEmpEmail",
						"filterSecondaryOwnerEmpEmail");
				inactiveEmployees = true;
				secOwn = new EmployeeInfo();
			}
			assetDetail.setPrimaryAdminEmployeeId(prmAdm.getEmployeeUserId());
			assetDetail.setSecondaryAdminEmployeeId(prmAdm.getEmployeeUserId());
			assetDetail.setPrimaryOwnerEmployeeId(prmOwn.getEmployeeUserId());
			assetDetail.setSecondaryOwnerEmployeeId(secOwn.getEmployeeUserId());

			if (inactiveEmployees) {
				// One or more INVALID email addresses.
				ModelAndView mv = new ModelAndView(this.getFormView());
				assetDetail.setPrimaryAdminEmployeeInfo(prmAdm);
				assetDetail.setSecondaryAdminEmployeeInfo(secAdm);
				assetDetail.setPrimaryOwnerEmployeeInfo(prmOwn);
				assetDetail.setSecondaryOwnerEmployeeInfo(secOwn);
				mv.addObject(Constants.ASSETDETAIL_KEY, assetDetail);
				String msg = getText(
						"assetDetail.invalidOrInactiveEmployeeInfo", locale);
				MessageUtil.saveError(request, msg);
				return mv;
			} else {		
				
				
				
				assetDetailManager.saveAssetDetail(assetDetail);
				String key = (isNew) ? "assetDetail.added"
						: "assetDetail.updated";
				MessageUtil.saveMessage(request, getText(key, locale));

				if (!isNew) {
					success = "editAssetDetail.html?view=true&id="
							+ assetDetail.getId();
				}
			}
		}

		return new ModelAndView(new RedirectView(success));
	}
	
	@SuppressWarnings("unchecked")
	private List<String> extractIds (HttpServletRequest request, String prefix){
		List<String> assetAttributeIds = new ArrayList<String>(); 
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String paramName = parameterNames.nextElement();
			if(paramName.startsWith(prefix)){
				assetAttributeIds.add(paramName.substring(prefix.length()));
			}
		}
		return assetAttributeIds;
	}
	
	
	
}
