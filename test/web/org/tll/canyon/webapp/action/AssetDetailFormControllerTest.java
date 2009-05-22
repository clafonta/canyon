package org.tll.canyon.webapp.action;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.webapp.action.AssetDetailFormController;


public class AssetDetailFormControllerTest extends BaseControllerTestCase {
    private AssetDetailFormController c;
    private MockHttpServletRequest request;
    private ModelAndView mv;

    protected void setUp() throws Exception {
        // needed to initialize a user
        super.setUp();
        c = (AssetDetailFormController) ctx.getBean("assetDetailFormController");
    }

    protected void tearDown() {
        c = null;
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/editAssetDetail.html");
        request.addParameter("assetName", "tomcat");
        mv = c.handleRequest(request, new MockHttpServletResponse());

        assertEquals("assetDetailForm", mv.getViewName());
    }

    public void testSave() throws Exception {

        request = newGet("/editAssetDetail.html");
        request.addParameter("id", "1");
        request.addParameter("assetName", "tomcat");
        
        mv = c.handleRequest(request, new MockHttpServletResponse());

        AssetDetail assetDetail = (AssetDetail) mv.getModel().get(c.getCommandName());
        assertNotNull(assetDetail);
        request = newPost("/editAssetDetail.html");
        super.objectToRequestParameters(assetDetail, request);

        // update the form's fields and add it back to the request
        mv = c.handleRequest(request, new MockHttpServletResponse());
        Errors errors = (Errors) mv.getModel().get(BindException.MODEL_KEY_PREFIX + "assetDetail");

        if (errors != null) {
            log.debug(errors);
        }
        assertNull(errors);
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    public void testRemove() throws Exception {
        request = newPost("/editAssetDetail.html");
        request.addParameter("delete", "");
        request.addParameter("id", "2");
        request.addParameter("assetName", "tomcat");
        mv = c.handleRequest(request, new MockHttpServletResponse());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
