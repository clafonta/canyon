package org.tll.canyon.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.tll.canyon.model.AssetAttribute;
import org.tll.canyon.model.AssetType;
import org.tll.canyon.model.OptionValue;
import org.tll.canyon.service.AssetAttributeManager;
import org.tll.canyon.service.AssetTypeManager;
import org.tll.canyon.service.OptionValueManager;
import org.tll.canyon.webapp.util.MessageUtil;

public class AssetAttributeFormController extends BaseFormController {
	private AssetAttributeManager assetAttributeManager = null;
	private AssetTypeManager assetTypeManager = null;
	private OptionValueManager optionValueManager = null;

	public void setOptionValueManager(OptionValueManager optionValueManager) {
		this.optionValueManager = optionValueManager;
	}
	
	public void setAssetTypeManager(AssetTypeManager assetTypeManager) {
		this.assetTypeManager = assetTypeManager;
	}

	public void setAssetAttributeManager(
			AssetAttributeManager assetAttributeManager) {
		this.assetAttributeManager = assetAttributeManager;
	}

	public AssetAttributeFormController() {
		setCommandName("assetAttribute");
		setCommandClass(AssetAttribute.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		String assetTypeId = request.getParameter("assetTypeId");
		String type = request.getParameter("type");

		AssetAttribute assetAttribute = null;

		if (!StringUtils.isEmpty(id)) {
			assetAttribute = assetAttributeManager.getAssetAttribute(id);
		} else {

			assetAttribute = new AssetAttribute();
			AssetType assetType = this.assetTypeManager
					.getAssetType(assetTypeId);
			assetAttribute.setAssetType(assetType);
			assetAttribute.setType(type);
		}

		return assetAttribute;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		AssetAttribute assetAttribute = (AssetAttribute) command;
		boolean isNew = (assetAttribute.getId() == null);
		Locale locale = request.getLocale();
		String successView = "editAssetType.html?view=true&id="
				+ assetAttribute.getAssetTypeId();
		if (request.getParameter("delete") != null) {

			assetAttributeManager.removeAssetAttribute(assetAttribute.getId()
					.toString());
			MessageUtil.saveMessage(request, getText("assetAttribute.deleted",
					locale));

		} else {
			
			// new line delimited
			String option_value = request.getParameter("option_value");
			if(option_value!=null){
//				// REMOVE old option values
//				List<OptionValue> oldList = assetAttribute.getOptionValueList();
//				if(oldList!=null){
//					Iterator<OptionValue> iter = oldList.iterator();
//					while(iter.hasNext()){
//						OptionValue v = iter.next();
//						this.optionValueManager.removeOptionValue(v.getId());
//					}
//				}
				// SAVE new option values
				List<OptionValue> optionValueList = parseValues(option_value);
				assetAttribute.setOptionValueList(optionValueList);
			}
			assetAttributeManager.saveAssetAttribute(assetAttribute);
			String key = (isNew) ? "assetAttribute.added"
					: "assetAttribute.updated";
			MessageUtil.saveMessage(request, getText(key, locale));

		}

		return new ModelAndView(new RedirectView(successView));
	}
	
	/**
	 * Parses arg for new lines. Each argument is turned into a new OptionValue object. 
	 * @param arg
	 * @return
	 */
	private static List<OptionValue> parseValues(String arg){
		List<OptionValue> valueList = new ArrayList<OptionValue>();
		
		int i = arg.indexOf("\n");
		int beginIndex = 0;
		while(i > -1){
			String temp = arg.substring(beginIndex,i+1);
			beginIndex = i+1;
			i = arg.indexOf("\n", i+1);
			
			if(temp!=null && temp.trim().length()>0){
				OptionValue v =  new OptionValue();
				v.setValue(temp.trim());
				valueList.add(v);
			}
		}
		
		// It is possile that the last argument does *not* have a 
		// line break. Let's be sure to get that data. 
		if(i<0 && arg.substring(beginIndex).trim().length()>0){
			String temp = arg.substring(beginIndex);
			OptionValue v =  new OptionValue();
			v.setValue(temp.trim());
			valueList.add(v);
		}
		return valueList;
	}

	public static void main(String[] args){
		String arg = "1\n 2\n 3\n\n\n 4\n\n\n";
		List<OptionValue> list = parseValues(arg);
		Iterator<OptionValue> iter = list.iterator();
		while(iter.hasNext()){
			System.out.println("->"+iter.next().getValue()+"<-");
		}
	}
}
