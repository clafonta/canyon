package org.tll.canyon.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.MockHttpServletRequest;
import org.tll.canyon.Constants;
import org.tll.canyon.webapp.action.AssetRoleController;
import org.tll.canyon.webapp.action.BaseControllerTestCase;

public class AssetRoleControllerTest extends BaseControllerTestCase {

    public void testHandleRequest() throws Exception {
        AssetRoleController c = 
            (AssetRoleController) ctx.getBean("assetRoleController");
        ModelAndView mav = c.handleRequest(new MockHttpServletRequest(),
                                           (HttpServletResponse) null);
        Map m = mav.getModel();
        assertNotNull(m.get(Constants.ASSETROLE_LIST));
        assertEquals(mav.getViewName(), "org.tll.canyon.model/assetRoleList");
    }
}
