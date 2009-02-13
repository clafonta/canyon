package org.tll.canyon.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.MockHttpServletRequest;
import org.tll.canyon.Constants;
import org.tll.canyon.webapp.action.AssetAccessRequestStatusController;
import org.tll.canyon.webapp.action.BaseControllerTestCase;

public class AssetAccessRequestStatusControllerTest extends BaseControllerTestCase {

    public void testHandleRequest() throws Exception {
        AssetAccessRequestStatusController c = 
            (AssetAccessRequestStatusController) ctx.getBean("assetAccessRequestStatusController");
        ModelAndView mav = c.handleRequest(new MockHttpServletRequest(),
                                           (HttpServletResponse) null);
        Map m = mav.getModel();
        assertNotNull(m.get(Constants.ASSETACCESSREQUESTSTATUS_LIST));
        assertEquals(mav.getViewName(), "assetAccessRequestStatusList");
    }
}
