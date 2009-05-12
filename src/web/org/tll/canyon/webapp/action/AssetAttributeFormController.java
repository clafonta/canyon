package org.tll.canyon.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.tll.canyon.model.AssetAttribute;
import org.tll.canyon.model.AssetType;
import org.tll.canyon.service.AssetAttributeManager;
import org.tll.canyon.service.AssetTypeManager;
import org.tll.canyon.webapp.util.MessageUtil;

public class AssetAttributeFormController extends BaseFormController {
	private AssetAttributeManager assetAttributeManager = null;
	private AssetTypeManager assetTypeManager = null;

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

		AssetAttribute assetAttribute = null;

		if (!StringUtils.isEmpty(id)) {
			assetAttribute = assetAttributeManager.getAssetAttribute(id);
		} else {

			assetAttribute = new AssetAttribute();
			AssetType assetType = this.assetTypeManager
					.getAssetType(assetTypeId);
			assetAttribute.setAssetType(assetType);
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
			assetAttributeManager.saveAssetAttribute(assetAttribute);
			String key = (isNew) ? "assetAttribute.added"
					: "assetAttribute.updated";
			MessageUtil.saveMessage(request, getText(key, locale));

		}

		return new ModelAndView(new RedirectView(successView));
	}

}
