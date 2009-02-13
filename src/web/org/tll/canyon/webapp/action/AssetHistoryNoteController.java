package org.tll.canyon.webapp.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import org.tll.canyon.model.AssetHistoryNote;
import org.tll.canyon.model.User;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.AssetHistoryNoteManager;
import org.tll.canyon.service.UserManager;

public class AssetHistoryNoteController implements Controller {

	private final Log log = LogFactory.getLog(AssetHistoryNoteController.class);
	private AssetHistoryNoteManager assetHistoryNoteManager = null;
	private AssetDetailManager assetDetailManager = null;
	private UserManager userManager = null;
	
	public void setAssetHistoryNoteManager(
			AssetHistoryNoteManager assetHistoryNoteManager) {
		this.assetHistoryNoteManager = assetHistoryNoteManager;
	}


	public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
		this.assetDetailManager = assetDetailManager;
	}

	

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String assetDetailId = request.getParameter("assetDetailId");
		String comment = request.getParameter("comment");
		User user = userManager.getUserByUsername(
				request.getRemoteUser());
		AssetHistoryNote assetHistoryNote = new AssetHistoryNote();
		assetHistoryNote.setAssetDetailId(new Long(assetDetailId));
		assetHistoryNote.setComment(comment);
		assetHistoryNote.setCreateTimestamp(new Date());
		assetHistoryNote.setUserId(user.getId());
		this.assetHistoryNoteManager.saveAssetHistoryNote(assetHistoryNote);
		//viewAssetDetail.html?id=1
		
		return new ModelAndView(new RedirectView(request.getContextPath()+"/viewAssetDetail.html?id="+assetDetailId));
	}

}
