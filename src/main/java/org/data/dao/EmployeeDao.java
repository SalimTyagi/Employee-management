package org.data.dao;

import org.data.entities.Employee;
import org.springframework.stereotype.Repository;


public interface EmployeeDao {

    void saveEmployee(Employee employee);

    Employee findEmployeeById(int employeeId);
}
