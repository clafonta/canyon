package org.tll.canyon.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.MockHttpServletRequest;
import org.tll.canyon.Constants;
import org.tll.canyon.webapp.action.BaseControllerTestCase;
import org.tll.canyon.webapp.action.EmployeeInfoController;

public class EmployeeInfoControllerTest extends BaseControllerTestCase {

    public void testHandleRequest() throws Exception {
        EmployeeInfoController c = 
            (EmployeeInfoController) ctx.getBean("employeeInfoController");
        ModelAndView mav = c.handleRequest(new MockHttpServletRequest(),
                                           (HttpServletResponse) null);
        Map m = mav.getModel();
        assertNotNull(m.get(Constants.EMPLOYEEINFO_LIST));
        assertEquals(mav.getViewName(), "employeeInfoList");
    }
}
