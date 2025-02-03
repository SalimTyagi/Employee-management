package org.data.services;


import org.data.entities.Employee;

public interface EmployeeService {

    void saveEmployee(Employee employee);

    Employee findEmployeeById(int employeeId);
}
