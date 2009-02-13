package org.tll.canyon.webapp.action;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.tll.canyon.model.AssetHitStat;
import org.tll.canyon.webapp.action.AssetHitStatFormController;
import org.tll.canyon.webapp.action.BaseControllerTestCase;

public class AssetHitStatFormControllerTest extends BaseControllerTestCase {
    private AssetHitStatFormController c;
    private MockHttpServletRequest request;
    private ModelAndView mv;

    protected void setUp() throws Exception {
        // needed to initialize a user
        super.setUp();
        c = (AssetHitStatFormController) ctx.getBean("assetHitStatFormController");
    }

    protected void tearDown() {
        c = null;
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/editAssetHitStat.html");
        request.addParameter("id", "1");

        mv = c.handleRequest(request, new MockHttpServletResponse());

        assertEquals("assetHitStatForm", mv.getViewName());
    }

    public void testSave() throws Exception {
        request = newGet("/editAssetHitStat.html");
        request.addParameter("id", "1");

        mv = c.handleRequest(request, new MockHttpServletResponse());

        AssetHitStat assetHitStat = (AssetHitStat) mv.getModel().get(c.getCommandName());
        assertNotNull(assetHitStat);
        request = newPost("/editAssetHitStat.html");
        super.objectToRequestParameters(assetHitStat, request);

        // update the form's fields and add it back to the request
        mv = c.handleRequest(request, new MockHttpServletResponse());
        Errors errors = (Errors) mv.getModel().get(BindException.MODEL_KEY_PREFIX + "assetHitStat");

        if (errors != null) {
            log.debug(errors);
        }
        assertNull(errors);
        assertNotNull(request.getSession().getAttribute("successMessages"));        
    }

    public void testRemove() throws Exception {
        request = newPost("/editAssetHitStat.html");
        request.addParameter("delete", "");
        request.addParameter("id", "2");
        mv = c.handleRequest(request, new MockHttpServletResponse());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
