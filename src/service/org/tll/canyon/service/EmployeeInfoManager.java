
package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.model.EmployeeInfo;


public interface EmployeeInfoManager extends Manager {
    /**
     * Retrieves all of the employeeInfos
     */
    public List<EmployeeInfo> getEmployeeInfoList(EmployeeInfo employeeInfo);
   
    /**
     * Gets employeeInfo's information based on employeeUserId.
     * @param employeeUserId the employeeInfo's employeeUserId
     * @return employeeInfo populated employeeInfo object
     */
    public EmployeeInfo getEmployeeInfo(final String employeeUserId);

    /**
     * Find employee information based on employee email address.
     * 
     * @param emailAddress
     * @return EmployeeInfo
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

