package org.tll.canyon.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.MockHttpServletRequest;
import org.tll.canyon.Constants;
import org.tll.canyon.webapp.action.AssetHitStatController;
import org.tll.canyon.webapp.action.BaseControllerTestCase;

public class AssetHitStatControllerTest extends BaseControllerTestCase {

    public void testHandleRequest() throws Exception {
        AssetHitStatController c = 
            (AssetHitStatController) ctx.getBean("assetHitStatController");
        ModelAndView mav = c.handleRequest(new MockHttpServletRequest(),
                                           (HttpServletResponse) null);
        Map m = mav.getModel();
        assertNotNull(m.get(Constants.ASSETHITSTAT_LIST));
        assertEquals(mav.getViewName(), "assetHitStatList");
    }
}
