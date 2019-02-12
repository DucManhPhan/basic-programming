/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.sample_webservice.services;

import com.manhpd.sample_webservice.Utils.HibernateUtil;
import com.manhpd.sample_webservice.models.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author manh.phanduc
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
    
    private Session session = HibernateUtil.getSessionFactory().openSession();
    
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("FROM Employee");
            employees = query.list();
            session.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = new Employee();
        try {
            session.getTransaction().begin();
            employee = (Employee) session.get(Employee.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        
        return employee;
    }

    @Override
    public Employee findByName(String name) {
        List<Employee> employees = new ArrayList<Employee>();
        Employee employee = new Employee();
        try {
            session.getTransaction().begin();
            Query query = session.createQuery("SELECT e FROM Employee AS e WHERE e.name = :name");
            query.setString("name", name);
            employees = query.list();
            if(employees.size() > 0) {
                employee = employees.get(0);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        
        return employee;
    }

    @Override
    public Boolean addEmployee(Employee employee) {
        boolean b = true;
        try {
            session.getTransaction().begin();
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            b = false;
        }
        
        return b;
    }

    @Override
    public Boolean updateEmployee(int id, Employee employee) {
         boolean b = true;
        try {
            session.getTransaction().begin();
            Employee updateEmployee = (Employee) session.get(Employee.class, id);
            updateEmployee.setName(employee.getName());
            updateEmployee.setAddress(employee.getAddress());
            updateEmployee.setPhone(employee.getPhone());
            updateEmployee.setSalary(employee.getSalary());
            session.update(updateEmployee);
            session.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            b = false;
        }
        
        return b;
    }

    @Override
    public Boolean deleteEmployee(int id) {
        boolean b = true;
        try {
            session.getTransaction().begin();
            session.delete(session.get(Employee.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            b = false;
        }
        
        return b;
    }
    
}
