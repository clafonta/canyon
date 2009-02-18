package org.tll.canyon.webapp.action;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.EmployeeInfoManager;

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

		String csvInput = request.getParameter("csvinput");
		Reader inputReader = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(csvInput.getBytes())));
		List<AssetDetail> assetDetailList = parseDelimitedInput(inputReader);
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
	 * <li>assetGroupName (can be empty)
	 * <li>assetDescription (non-empty)
	 * <li>assetUsageType (non-empty)
	 * <li>assetLocation (non-empty)
	 * <li>assetAddress (non-empty)
	 * <li>enabled (non-empty, true OR false)
	 * <li>managerApproval (non-empty, true OR false)
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
		String[] columns = new String[] { "assetName", "assetGroupName",
				"assetDescription", "assetUsageType", "assetLocation",
				"assetAddress", "assetAdminTeamName", "assetAdminTeamEmail",
				"primaryAdminEmployeeId", "secondaryAdminEmployeeId",
				"primaryOwnerEmployeeId", "secondaryOwnerEmployeeId" };

		strat.setColumnMapping(columns);

		CsvToBean csv = new CsvToBean();
		List list = csv.parse(strat, inputReader);
		return list;

	}

	public static void main(String[] args) throws Exception {
		AssetDetailCsvFormController c = new AssetDetailCsvFormController();

		// LOCATION
		String parseFile = "C:\\sample.csv";

		File file = new File(parseFile);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;

		StringBuffer cleanLine = new StringBuffer();

		while ((line = br.readLine()) != null) {
			cleanLine.append((line));
		}
		br.close();

		List<AssetDetail> details = c.parseDelimitedInput(new FileReader(
				parseFile));
		for (Iterator<AssetDetail> iterator = details.iterator(); iterator
				.hasNext();) {
			AssetDetail assetDetail = (AssetDetail) iterator.next();
			System.out.println(assetDetail.toString());
		}

	}

}
