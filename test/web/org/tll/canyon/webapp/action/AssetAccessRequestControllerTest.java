package org.tll.canyon.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.MockHttpServletRequest;
import org.tll.canyon.Constants;
import org.tll.canyon.webapp.action.AssetAccessRequestController;
import org.tll.canyon.webapp.action.BaseControllerTestCase;

public class AssetAccessRequestControllerTest extends BaseControllerTestCase {

    public void testHandleRequest() throws Exception {
        AssetAccessRequestController c = 
            (AssetAccessRequestController) ctx.getBean("assetAccessRequestController");
        ModelAndView mav = c.handleRequest(new MockHttpServletRequest(),
                                           (HttpServletResponse) null);
        Map m = mav.getModel();
        assertNotNull(m.get(Constants.ASSETACCESSREQUEST_LIST));
        assertEquals(mav.getViewName(), "assetAccessRequestList");
    }
}
