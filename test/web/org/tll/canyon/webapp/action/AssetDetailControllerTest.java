package org.tll.canyon.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.tll.canyon.Constants;
import org.tll.canyon.webapp.action.AssetDetailController;


public class AssetDetailControllerTest extends BaseControllerTestCase {
    
    public void testHandleRequest() throws Exception {
        AssetDetailController c = (AssetDetailController) ctx.getBean("assetDetailController");
        ModelAndView mav = c.handleRequest((HttpServletRequest) null,
                                           (HttpServletResponse) null);
        Map m = mav.getModel();
        assertNotNull(m.get(Constants.ASSETDETAIL_LIST));
        assertEquals(mav.getViewName(), "assetDetailList");
    }
}
