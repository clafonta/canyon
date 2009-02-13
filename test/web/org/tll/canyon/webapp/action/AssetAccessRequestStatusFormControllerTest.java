package org.tll.canyon.webapp.action;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.tll.canyon.model.AssetAccessRequestStatus;
import org.tll.canyon.webapp.action.AssetAccessRequestStatusFormController;
import org.tll.canyon.webapp.action.BaseControllerTestCase;

public class AssetAccessRequestStatusFormControllerTest extends BaseControllerTestCase {
    private AssetAccessRequestStatusFormController c;
    private MockHttpServletRequest request;
    private ModelAndView mv;

    protected void setUp() throws Exception {
        // needed to initialize a user
        super.setUp();
        c = (AssetAccessRequestStatusFormController) ctx.getBean("assetAccessRequestStatusFormController");
    }

    protected void tearDown() {
        c = null;
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/editAssetAccessRequestStatus.html");
        request.addParameter("id", "1");

        mv = c.handleRequest(request, new MockHttpServletResponse());

        assertEquals("assetAccessRequestStatusForm", mv.getViewName());
    }

    public void testSave() throws Exception {
        request = newGet("/editAssetAccessRequestStatus.html");
        request.addParameter("id", "1");

        mv = c.handleRequest(request, new MockHttpServletResponse());

        AssetAccessRequestStatus assetAccessRequestStatus = (AssetAccessRequestStatus) mv.getModel().get(c.getCommandName());
        assertNotNull(assetAccessRequestStatus);
        request = newPost("/editAssetAccessRequestStatus.html");
        super.objectToRequestParameters(assetAccessRequestStatus, request);

        // update the form's fields and add it back to the request
        mv = c.handleRequest(request, new MockHttpServletResponse());
        Errors errors = (Errors) mv.getModel().get(BindException.MODEL_KEY_PREFIX + "assetAccessRequestStatus");

        if (errors != null) {
            log.debug(errors);
        }
        assertNull(errors);
        assertNotNull(request.getSession().getAttribute("successMessages"));        
    }

    public void testRemove() throws Exception {
        request = newPost("/editAssetAccessRequestStatus.html");
        request.addParameter("delete", "");
        request.addParameter("id", "2");
        mv = c.handleRequest(request, new MockHttpServletResponse());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
