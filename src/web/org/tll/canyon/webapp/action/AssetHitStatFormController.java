package org.tll.canyon.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.tll.canyon.model.AssetHitStat;
import org.tll.canyon.service.AssetHitStatManager;
import org.tll.canyon.webapp.util.MessageUtil;


public class AssetHitStatFormController extends BaseFormController {
    private AssetHitStatManager assetHitStatManager = null;

    public void setAssetHitStatManager(AssetHitStatManager assetHitStatManager) {
        this.assetHitStatManager = assetHitStatManager;
    }

    public AssetHitStatFormController() {
        setCommandName("assetHitStat");
        setCommandClass(AssetHitStat.class);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        AssetHitStat assetHitStat = null;

        if (!StringUtils.isEmpty(id)) {
            assetHitStat = assetHitStatManager.getAssetHitStat(id);
        } else {
            assetHitStat = new AssetHitStat();
        }

        return assetHitStat;
    }

    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AssetHitStat assetHitStat = (AssetHitStat) command;
        boolean isNew = (assetHitStat.getId() == null);
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            assetHitStatManager.removeAssetHitStat(assetHitStat.getId().toString());

            MessageUtil.saveMessage(request, getText("assetHitStat.deleted", locale));
        } else {
            assetHitStatManager.saveAssetHitStat(assetHitStat);

            String key = (isNew) ? "assetHitStat.added" : "assetHitStat.updated";
            MessageUtil.saveMessage(request, getText(key, locale));

            if (!isNew) {
                return new ModelAndView("redirect:editAssetHitStat.html", "id", assetHitStat.getId());
            }
        }

        return new ModelAndView(getSuccessView());
    }
}
