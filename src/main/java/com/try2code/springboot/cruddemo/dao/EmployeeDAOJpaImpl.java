package com.try2code.springboot.cruddemo.dao;

import com.try2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // define field for entity manager
    private EntityManager entityManager ;
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create Query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee" , Employee.class);
        //execute Query and get result
        List<Employee> employees =theQuery.getResultList();
        //return result
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        //get employee
        Employee theEmployee = entityManager.find(Employee.class , theId);

        //return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        //save employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        //return employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        //find Employee
        Employee theEmployee  = entityManager.find(Employee.class , theId);
        //delete Employee
        entityManager.remove(theEmployee);


    }
}
