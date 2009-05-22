package org.tll.canyon.webapp.view;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.springframework.web.servlet.view.AbstractView;
import org.tll.canyon.model.viewwrappers.AssetDetailInfo;

public class AssetDetailInfoView extends AbstractView {

	@SuppressWarnings("unchecked")
	protected void renderMergedOutputModel(Map model,
			HttpServletRequest httpServletRequest, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		List<AssetDetailInfo> assetDetailInfoList = (List<AssetDetailInfo>) model
				.get("assetDetailInfoList");

//		String xml = new AjaxXmlBuilder().addItems(assetDetailInfoList,
//				"assetName", "assetName").toString();
		AjaxXmlBuilder builder = new AjaxXmlBuilder();
		builder.addItems(assetDetailInfoList,"assetName", "assetName").toString();
		
		ServletOutputStream out = response.getOutputStream();
		out.print(builder.toString());
		out.close();
	}
}
