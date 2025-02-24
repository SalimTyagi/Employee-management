package org.data.dao;

import org.data.entities.Employee;
import java.util.List;
public interface EmployeeDao {
    void saveEmployee(Employee employee);
    Employee findEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    void deleteEmployee(int id);
    Employee findEmployeeByUsername(String userName);
    List<Employee> searchEmployees(String searchQuery);
}
