package org.data.services;
import org.data.entities.Employee;
import java.util.List;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    Employee findEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    void delete(int id);
    List<Employee> searchEmployees(String searchQuery);
}
