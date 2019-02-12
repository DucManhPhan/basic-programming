/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.sample_webservice.services;

import com.manhpd.sample_webservice.models.Employee;
import java.util.List;

/**
 *
 * @author manh.phanduc
 */
public interface EmployeeService {
    List<Employee> getAllEmployees();
    
    Employee findById(int id);
    
    Employee findByName(String name);
    
    Boolean addEmployee(Employee employee);
    
    Boolean updateEmployee(int id, Employee employee);
    
    Boolean deleteEmployee(int id);
}
