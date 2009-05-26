package org.tll.canyon.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.tll.canyon.model.AssetAttribute;
import org.tll.canyon.model.OptionValue;
import org.tll.canyon.service.AssetAttributeManager;
import org.tll.canyon.service.OptionValueManager;
import org.tll.canyon.webapp.util.MessageUtil;

public class OptionValueFormController extends BaseFormController {
	private OptionValueManager optionValueManager = null;
	
	private AssetAttributeManager assetAttributeManager = null;

	public void setOptionValueManager(OptionValueManager optionValueManager) {
		this.optionValueManager = optionValueManager;
	}

	public void setAssetAttributeManager(
			AssetAttributeManager assetAttributeManager) {
		this.assetAttributeManager = assetAttributeManager;
	}

	public OptionValueFormController() {
		setCommandName("optionValue");
		setCommandClass(OptionValue.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		String assetAttributeId = request.getParameter("assetAttributeId");
		

		OptionValue optionValue = null;

		if (!StringUtils.isEmpty(id)) {
			optionValue = this.optionValueManager.getOptionValue(id);
			
		} else {

			optionValue = new OptionValue();
			AssetAttribute assetAttribute = this.assetAttributeManager.getAssetAttribute(assetAttributeId);
			optionValue.setAssetAttribute(assetAttribute);
			
		}

		return optionValue;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		OptionValue optionValue = (OptionValue) command;
		boolean isNew = (optionValue.getId() == null);
		Locale locale = request.getLocale();
		String successView = "editAssetAttribute.html?view=true&id="
				+ optionValue.getAssetAttributeId();
		
		if (request.getParameter("delete") != null) {
			this.optionValueManager.removeOptionValue(optionValue.getId());
			
			MessageUtil.saveMessage(request, getText("optionValue.deleted",
					locale));

		} else {
			
			optionValueManager.saveOptionValue(optionValue);
			String key = (isNew) ? "optionValue.added" 	: "optionValue.updated";
			MessageUtil.saveMessage(request, getText(key, locale));

		}

		return new ModelAndView(new RedirectView(successView));
	}

}
