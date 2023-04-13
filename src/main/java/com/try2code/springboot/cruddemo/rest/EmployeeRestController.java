package com.try2code.springboot.cruddemo.rest;

import com.try2code.springboot.cruddemo.dao.EmployeeDAO;
import com.try2code.springboot.cruddemo.entity.Employee;
import com.try2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService ;

    //inject employee dao

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @RequestMapping("/employees")
    public List<Employee> findAll(){

        return employeeService.findAll();
    }

    @RequestMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId ){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw  new RuntimeException("employee not found .. ");
        }

        return theEmployee ;
    }

    // add mapping for Post Employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        //also just in case they pass an id in json ... set id to 0
        // this is to force a save new item ... instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;

    }
    // add mapping for update Employee
    @PutMapping ("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){


        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;

    }

    // add mapping for delete Employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee dbEmployee = employeeService.findById(employeeId);
        if(dbEmployee==null){
            throw new RuntimeException("employee not found...");
        }
        employeeService.deleteById(employeeId);

        return "deleted "+ employeeId ; 
    }


}
