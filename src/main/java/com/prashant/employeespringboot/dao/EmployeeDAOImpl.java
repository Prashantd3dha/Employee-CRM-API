package com.prashant.employeespringboot.dao;

import com.prashant.employeespringboot.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee",Employee.class);
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class,id);
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee employee = entityManager.merge(theEmployee);
        return employee;
    }

    @Override
    public void deleteById(int id) {
        Employee employee = entityManager.find(Employee.class,id);
        entityManager.remove(employee);
    }
}
