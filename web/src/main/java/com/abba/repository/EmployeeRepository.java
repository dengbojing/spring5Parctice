package com.abba.repository;


import com.abba.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author dengbojing
 */
@Service
public class EmployeeRepository {
    public List<Employee> findAll() {
        List<Employee> list  = new ArrayList<>(2);
        list.add(Employee.builder().userName("name").userPhone("phone").id("0").build());
        return list;

    }

    public void deleteById(String id) {
    }

    public Employee save(Employee employeeToUpdate) {
        return employeeToUpdate;
    }

    public Optional<Employee> findById(String id) {
        return Optional.of(Employee.builder().userName("name").userPhone("phone").build());
    }
}
