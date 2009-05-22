package org.tll.canyon.webapp.action;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.EmployeeInfo;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.EmployeeInfoManager;
import org.tll.canyon.webapp.util.MessageUtil;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

/**
 * For more than 1 asset detail loader, from a CSV web form input.
 * 
 * @author Chad.Lafontaine
 * 
 */
public class AssetDetailCsvFormController extends MultiActionController {
	private final Log log = LogFactory
			.getLog(AssetDetailCsvFormController.class);

	private AssetDetailManager assetDetailManager = null;
	private EmployeeInfoManager employeeInfoManager = null;

	public void setEmployeeInfoManager(EmployeeInfoManager employeeInfoManager) {
		this.employeeInfoManager = employeeInfoManager;
	}

	public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
		this.assetDetailManager = assetDetailManager;
	}

	public ModelAndView form(HttpServletRequest request,
			HttpServletResponse response) {

		log.debug("entering 'form' method...");

		return new ModelAndView("assetDetailImportForm");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws Exception
	 */
	public ModelAndView confirm(HttpServletRequest request,
			HttpServletResponse response) {
		
		if (log.isDebugEnabled()) {
			log.debug("entering 'confirm' method...");
		}

		// ******************************************************
		// BUILD ASSET DETAIL LIST FROM CSV INPUT
		// ******************************************************		
		String csvInput = request.getParameter("csvinput");
		Reader inputReader = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(csvInput.getBytes())));
		List<AssetDetail> assetDetailList = parseDelimitedInput(inputReader);
		
		
		// TODO: 
		// ******************************************************
		// THERE HAS TO BE A BETTER WAY TO MANAGE THESE Employees It's not KISS. It's copy/paste/repeat. UGH!
		// ******************************************************
		// EMPLOYEE VERIFICATION
		// ******************************************************
		// Now, retrieve employee information per asset detail.
		// Add any issues per AssetDetail encountered. 
		// But let's be smart: let's not display invalid Employee X '20' or '30' times. Just once. 
		Map<String, Boolean> invalidEmpIds = new HashMap<String, Boolean>();
		for (Iterator<AssetDetail> iterator = assetDetailList.iterator(); iterator.hasNext();) {
			AssetDetail assetDetail = (AssetDetail) iterator.next();
			
			// Primary
			EmployeeInfo pAemp = this.employeeInfoManager.getEmployeeInfo(assetDetail.getPrimaryAdminEmployeeId() );
			assetDetail.setPrimaryAdminEmployeeInfo(pAemp);
			if(pAemp==null || !pAemp.isActive()){
				invalidEmpIds.put(assetDetail.getPrimaryAdminEmployeeId(), new Boolean(false));
			}
			
			// Secondary
			EmployeeInfo sAemp = this.employeeInfoManager.getEmployeeInfo(assetDetail.getSecondaryAdminEmployeeId() );
			assetDetail.setSecondaryAdminEmployeeInfo(sAemp);
			if(sAemp==null || !sAemp.isActive()){
				invalidEmpIds.put(assetDetail.getSecondaryAdminEmployeeId(), new Boolean(false));				
			}
			
			// Primary
			EmployeeInfo pOemp = this.employeeInfoManager.getEmployeeInfo(assetDetail.getPrimaryOwnerEmployeeId() );
			assetDetail.setPrimaryOwnerEmployeeInfo(pOemp);
			if(pOemp==null || !pOemp.isActive()){
				invalidEmpIds.put(assetDetail.getPrimaryOwnerEmployeeId(), new Boolean(false));				
			}
			
			// Secondary
			EmployeeInfo sOemp = this.employeeInfoManager.getEmployeeInfo(assetDetail.getSecondaryOwnerEmployeeId() );
			assetDetail.setSecondaryOwnerEmployeeInfo(sOemp);
			if(sOemp==null || !sOemp.isActive()){
				invalidEmpIds.put(assetDetail.getSecondaryOwnerEmployeeId(), new Boolean(false));				
			}
		}
		;
		if(!invalidEmpIds.keySet().isEmpty()){
			String invalidEmployeeText = getText("assetDetailImport.invalid_employee", request.getLocale());
			for (Iterator iterator = invalidEmpIds.keySet().iterator(); iterator.hasNext();) {
				invalidEmployeeText = invalidEmployeeText + " " + (String) iterator.next();
				if(iterator.hasNext()){
					invalidEmployeeText = invalidEmployeeText + ",";
				}
				
			}			
			MessageUtil.saveError(request, invalidEmployeeText);
		}
		
		// ******************************************************
		// ASSET DETAIL VERIFICATION - does it exist already??
		// ******************************************************
		Map<String, Boolean> dupAssetNames = new HashMap<String, Boolean>();
		for (Iterator<AssetDetail> iterator = assetDetailList.iterator(); iterator.hasNext();) {
			AssetDetail assetDetail = (AssetDetail) iterator.next();
			if(this.assetDetailManager.getAssetDetailByName(assetDetail.getAssetName())!=null){
				dupAssetNames.put(assetDetail.getAssetName(), new Boolean(false));
			}
		}
		if(!dupAssetNames.keySet().isEmpty()){
			String duplicateAssetText = getText("assetDetailImport.exists", request.getLocale());
			for (Iterator iterator = dupAssetNames.keySet().iterator(); iterator.hasNext();) {
				duplicateAssetText = duplicateAssetText + " " + (String) iterator.next();
				if(iterator.hasNext()){
					duplicateAssetText = duplicateAssetText + ",";
				}
				
			}			
			MessageUtil.saveError(request, duplicateAssetText);
		}
		
		// ******************************************************
		// IMPORTING DUPLICATE ENTRIES?
		// ******************************************************
		Map<String, Boolean> dupEntries = new HashMap<String, Boolean>();
		Map<String, Boolean> visited = new HashMap<String, Boolean>();
		for (Iterator<AssetDetail> iterator = assetDetailList.iterator(); iterator.hasNext();) {
			AssetDetail assetDetail = (AssetDetail) iterator.next();
			if(visited.get(assetDetail.getAssetName())!=null){
				dupEntries.put(assetDetail.getAssetName(), new Boolean(true));
			}else {
				visited.put(assetDetail.getAssetName(), new Boolean(true));
			}
		}
		if(!dupEntries.keySet().isEmpty()){
			String dupImportEntry = getText("assetDetailImport.duplicate", request.getLocale());
			for (Iterator iterator = dupEntries.keySet().iterator(); iterator.hasNext();) {
				dupImportEntry = dupImportEntry + " " + (String) iterator.next();
				if(iterator.hasNext()){
					dupImportEntry = dupImportEntry + ",";
				}
				
			}			
			MessageUtil.saveError(request, dupImportEntry);
		}
		
		ModelAndView listPage = new ModelAndView("assetDetailImportList");
		listPage.addObject("import", new Boolean(true));
		listPage.addObject("assetDetailList", assetDetailList);
		request.getSession().setAttribute("assetDetailList", assetDetailList);
		return listPage;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) {

		log.debug("entering 'save' method...");
		// Now we iterate, and save each item.
		List<AssetDetail> assetDetailList = (List<AssetDetail>) request
				.getSession().getAttribute("assetDetailList");
		List<String> unsuccessfulAdditions = new ArrayList();
		for (Iterator iterator = assetDetailList.iterator(); iterator.hasNext();) {
			AssetDetail assetDetail = (AssetDetail) iterator.next();
			// TODO:
			// Add error + information to end user
			// * Data too long for DB
			// * Invalid data type
			// * Etc....
			try {
				AssetDetail alreadyExists = this.assetDetailManager.getAssetDetailByName(assetDetail.getAssetName());
				if(alreadyExists!=null){
					unsuccessfulAdditions.add(assetDetail.getAssetName());
				}else {
					this.assetDetailManager.saveAssetDetail(assetDetail);	
				}
				
			} catch (Exception e) {
				log.warn("Can't insert asset with name:"
						+ assetDetail.getAssetName(), e);
				unsuccessfulAdditions.add(assetDetail.getAssetName());
			}

		}
		ModelAndView mv = new ModelAndView("assetDetailImportSummary");
		mv.addObject("errorList", unsuccessfulAdditions);
		return mv;
	}

	/**
	 * Takes in input string (from a web form), parses input, and builds a list
	 * of <code>AssetDetail</code> objects. Order of required input is as
	 * follows:
	 * <ul>
	 * <li></li>
	 * <li>assetName (non-empty)
	 * <li>assetAdminTeamName (non-empty)
	 * <li>assetAdminTeamEmail (non-empty)
	 * <li>primaryAdminEmployeeId (non-empty)
	 * <li>secondaryAdminEmployeeId (can be empty)
	 * <li>primaryOwnerEmployeeId (non-empty)
	 * <li>secondaryOwnerEmployeeId (can be empty)
	 * </ul>
	 * Example:
	 * 
	 * <pre>
	 * appw1x.somedomain.net 10.111.111.101	SLiM	Blah blah blah	PROD	Enterprise Data Center (Seattle, WA) 10.111.111.101	TRUE	TRUE	DL@somedomain.net	TheAdmins	jim.smith@somedomain.net	&quot;&quot;	bob.doe@somedomain.com	&quot;&quot;
	 * </pre>
	 * 
	 * @param input
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<AssetDetail> parseDelimitedInput(Reader inputReader) {
		// Minimum, 13 tabs.
		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		strat.setType(AssetDetail.class);
		String[] columns = new String[] { "assetName", "assetAdminTeamName", "assetAdminTeamEmail",
				"primaryAdminEmployeeId", "secondaryAdminEmployeeId",
				"primaryOwnerEmployeeId", "secondaryOwnerEmployeeId" };

		strat.setColumnMapping(columns);

		CsvToBean csv = new CsvToBean();
		List list = csv.parse(strat, inputReader);
		return list;

	}
	
	/**
     * Convenience method for getting a i18n key's value.  Calling
     * getMessageSourceAccessor() is used because the RequestContext variable
     * is not set in unit tests b/c there's no DispatchServlet Request.
     *
     * @param msgKey
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, Locale locale) {
        return getMessageSourceAccessor().getMessage(msgKey, locale);
    }

//	public static void main(String[] args) throws Exception {
//		AssetDetailCsvFormController c = new AssetDetailCsvFormController();
//
//		// LOCATION
//		String parseFile = "C:\\sample.csv";
//
//		File file = new File(parseFile);
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		String line = null;
//
//		StringBuffer cleanLine = new StringBuffer();
//
//		while ((line = br.readLine()) != null) {
//			cleanLine.append((line));
//		}
//		br.close();
//
//		List<AssetDetail> details = c.parseDelimitedInput(new FileReader(
//				parseFile));
//		for (Iterator<AssetDetail> iterator = details.iterator(); iterator
//				.hasNext();) {
//			AssetDetail assetDetail = (AssetDetail) iterator.next();
//			System.out.println(assetDetail.toString());
//		}
//
//	}

}
