package org.data.dao;

import org.data.entities.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EmployeeDao {

    void saveEmployee(Employee employee);

    Employee findEmployeeById(int employeeId);

    List<Employee> getAllEmployees();

    boolean authenticate(String userName, String password);

    void deleteEmployee(int id);
}
