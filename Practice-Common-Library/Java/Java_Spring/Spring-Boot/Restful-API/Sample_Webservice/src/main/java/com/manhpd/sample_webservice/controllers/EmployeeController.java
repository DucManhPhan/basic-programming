/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.sample_webservice.controllers;

import com.manhpd.sample_webservice.models.Employee;
import com.manhpd.sample_webservice.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 *
 * @author manh.phanduc
 */
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;
    
    @GetMapping(value = "/employees") 
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = service.getAllEmployees();
        if(employees.isEmpty()) {
            System.out.println("Employees Not Found!");
            return new ResponseEntity<List<Employee>>(NO_CONTENT);
        } else {
            System.out.println("Employees Found!");
            return new ResponseEntity<List<Employee>>(employees, OK);
        }
    }
    
    @RequestMapping(value = "/employee", params = "id", method = GET)
    public ResponseEntity<Employee> getById(@RequestParam int id) {
        Employee employee = service.findById(id);
        if(employee == null) {
            System.out.println("Employee ID " + id + " Not Found!");
            return new ResponseEntity<Employee>(NOT_FOUND);
        } else {
            System.out.println("Employee ID " + id + " Found!");
            return new ResponseEntity<Employee>(employee, OK);
        }
    }

    @RequestMapping(value = "/employee", params = "name", method = GET)
    public ResponseEntity<Employee> getByName(@RequestParam String name) {
        Employee employee = service.findByName(name);
        if(employee.getId() == 0) {
            System.out.println("Employee Name " + name + " Not Found!");
            return new ResponseEntity<Employee>(NOT_FOUND);
        } else {
            System.out.println("Employee Name " + name + " Found!");
            return new ResponseEntity<Employee>(employee, OK);
        }
    }

    @RequestMapping(value = "/addEmployee", method = POST)
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        if(service.addEmployee(employee)) {
            System.out.println("Created New Employee!");
            return new ResponseEntity<Void>(OK);
        } else {
            System.out.println("Can not create new employee!");
            return new ResponseEntity<Void>(BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateEmployee", method = PUT)
    public ResponseEntity<Void> updateEmployee(@RequestParam("id") int id, @RequestBody Employee employee) {
        if(service.updateEmployee(id, employee)) {
            System.out.println("Updated Employee with ID " + id);
            return new ResponseEntity<Void>(OK);
        } else {
            System.out.println("Employee ID " + id + " Not Found!");
            return new ResponseEntity<Void>(NOT_FOUND);
        }
    }

    @RequestMapping(value = "/deleteEmployee", method = DELETE)
    public ResponseEntity<Void> deleteEmployee(@RequestParam("id") int id) {
        if(service.deleteEmployee(id)) {
            System.out.println("Deleted Employee with ID " + id);
            return new ResponseEntity<Void>(OK);
        } else {
            System.out.println("Employee ID " + id + " Not Found!");
            return new ResponseEntity<Void>(NOT_FOUND);
        }
    }
}
