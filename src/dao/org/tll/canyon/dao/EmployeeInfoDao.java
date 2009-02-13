
package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.dao.Dao;
import org.tll.canyon.model.EmployeeInfo;


public interface EmployeeInfoDao extends Dao {

    /**
     * Retrieves all of the employeeInfos
     */
    public List<EmployeeInfo> getEmployeeInfoList(EmployeeInfo employeeInfo);
    
    
    /**
     * Retrieves a employeeInfo's information
     * @param employee id
     */    
    public EmployeeInfo getEmployeeInfo(final String employeeUserId);
    
    /**
     * Retrieves an employee info by email
     */
    public EmployeeInfo getEmployeeInfoByEmailAddress(final String emailAddress);
    
    /**
     * Saves a employeeInfo's information
     * @param employeeInfo the object to be saved
     */    
    public void saveEmployeeInfo(EmployeeInfo employeeInfo);

    /**
     * Removes a employeeInfo from the database by employeeUserId
     * @param employeeUserId the employeeInfo's employeeUserId
     */
    public void removeEmployeeInfo(final String employeeUserId);
}

