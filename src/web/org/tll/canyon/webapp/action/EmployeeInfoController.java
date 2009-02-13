package org.tll.canyon.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.tll.canyon.Constants;
import org.tll.canyon.model.EmployeeInfo;
import org.tll.canyon.service.EmployeeInfoManager;


public class EmployeeInfoController implements Controller {
    private final Log log = LogFactory.getLog(EmployeeInfoController.class);
    private EmployeeInfoManager employeeInfoManager = null;

    public void setEmployeeInfoManager(EmployeeInfoManager employeeInfoManager) {
        this.employeeInfoManager = employeeInfoManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        EmployeeInfo employeeInfoForm = new EmployeeInfo();
        EmployeeInfo employeeInfoDetail = null;
        BeanUtils.populate(employeeInfoForm, request.getParameterMap());
        List<EmployeeInfo> employeeList = employeeInfoManager.getEmployeeInfoList(employeeInfoForm);
        ModelAndView mv = new ModelAndView("employeeInfoList");
        mv.addObject(Constants.EMPLOYEEINFO_LIST, employeeList);
        mv.addObject(Constants.EMPLOYEEINFO_FORM, employeeInfoForm);
        String detailView = request.getParameter("view");
        
        if(detailView!=null && detailView.trim().equals("detail") && employeeInfoForm.getEmployeeUserId()!=null){
            employeeInfoDetail = this.employeeInfoManager.getEmployeeInfo(employeeInfoForm.getEmployeeUserId());   
          mv.addObject(Constants.EMPLOYEEINFO_KEY, employeeInfoDetail);
        }
        return mv;
    }
}
