/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empmanagement.persistence;

import com.employeemanagement.models.Client;
import com.employeemanagement.models.EmpJob;
import com.employeemanagement.models.Employee;
import com.empmanagement.view.bean.JobDetail;
import java.util.Date;
import java.util.List;

/**
 *
 * @author arup
 */
public interface IPersistence {
    
    /**
     * Saving Employee detail
     * 
     * @param emp 
     */
    public Boolean saveEmployee(Employee emp);
    
    /**
     * Saving a client
     * 
     * @param c 
     */
    public Boolean saveClient(Client c);
    
    /**
     * Get Employee detail based on employee id
     * 
     * @param emp_id
     * @return 
     */
    public Employee getEmpDetail(String emp_id);
    
    /**
     * Get Client detail based on client id
     * 
     * @param emp_id
     * @return 
     */
    public Employee getClientDetail(String emp_id);
    
    /**
     * Update employee table
     * 
     * @param emp 
     */
    public boolean updateEmployee(Employee emp);
    
    /**
     * Get list of employies
     * 
     * @param jobCategory
     * @return 
     */
    public List<Employee> getEmployies(String jobCategory);
    
    /**
     * Get Employee max id
     * 
     * @return 
     */
    public Integer getEmpMaxId();
    
    /**
     * Get Client max id
     * 
     * @return 
     */
    public Integer getClientMaxId();
    
    /**
     * Get Employee ID
     * 
     * @return 
     */
    public String getEmployeeID();
    
    public String getClientID();
    
    
    public List<Employee> getEmployees();
    
    public List<Employee> getSearchedEmployeeList(List<String> listParam);
    
    public Employee getEmployee(String emp_id);
    
    public JobDetail getJobStatusDetail(String empID,String clientID, Date startDate,String status);
    public List<Client> getClients();
    public Boolean jobAssign(EmpJob empjob);
    public List<JobDetail> getJobStatusDetail(String status);
    
    public Boolean updateJobDetail(EmpJob modifiedData,EmpJob oldData);
    
    public Client getClient(String clientId);

    public Boolean updateClient(Client client);

    public List<Client> getClients(List<String> params);
    
    public List<Employee> getAssignedEmployeeByClient(String clientID,String post);
    
    public Boolean deleteEmployee(String empID);
    
    public Boolean deleteClient(String clientID);
    
    public Boolean deleteClient(List<String> clientIDs);
    
    public Boolean deleteEmployee(List<String> empIDs);
    
    public Boolean cleanDatabase();
    
    public List<Employee> getWorkingEmployee(String post);
    
    public List<Client> getWorkingClient(String empID);
    
    public String getEmployeeID(String post);
    
    public List<Employee> getEmployies(String name,String post);
    
    public List<Client> getClients(String name);
    
}
