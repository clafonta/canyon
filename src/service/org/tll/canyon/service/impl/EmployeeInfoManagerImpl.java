
package org.tll.canyon.service.impl;

import java.util.List;

import org.tll.canyon.dao.EmployeeInfoDao;
import org.tll.canyon.model.EmployeeInfo;
import org.tll.canyon.service.EmployeeInfoManager;
import org.tll.canyon.service.impl.BaseManager;


public class EmployeeInfoManagerImpl extends BaseManager implements EmployeeInfoManager {
    private EmployeeInfoDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setEmployeeInfoDao(EmployeeInfoDao dao) {
        this.dao = dao;
    }

    /**
     * @see org.tll.canyon.service.EmployeeInfoManager#getEmployeeInfos(org.tll.canyon.model.EmployeeInfo)
     */
    public List<EmployeeInfo> getEmployeeInfoList(final EmployeeInfo employeeInfo) {
        return dao.getEmployeeInfoList(employeeInfo);
    }
    
    /**
     * @see org.tll.canyon.service.EmployeeInfoManager#saveEmployeeInfo(EmployeeInfo employeeInfo)
     */
    public EmployeeInfo getEmployeeInfo(final String employeeUserId){
        return dao.getEmployeeInfo(employeeUserId);
    }

    public EmployeeInfo getEmployeeInfoByEmailAddress(final String emailAddress){
    	return dao.getEmployeeInfoByEmailAddress(emailAddress);
    }
    /**
     * @see org.tll.canyon.service.EmployeeInfoManager#saveEmployeeInfo(EmployeeInfo employeeInfo)
     */
    public void saveEmployeeInfo(EmployeeInfo employeeInfo) {
        dao.saveEmployeeInfo(employeeInfo);
    }

    /**
     * @see org.tll.canyon.service.EmployeeInfoManager#removeEmployeeInfo(String employeeUserId)
     */
    public void removeEmployeeInfo(final String employeeUserId) {
        dao.removeEmployeeInfo(new String(employeeUserId));
    }
}
