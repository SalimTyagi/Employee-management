package org.data.services;

import org.data.dao.EmployeeDao;
import org.data.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }
    @Override
    public Employee findEmployeeById(int employeeId) {
        return employeeDao.findEmployeeById(employeeId);
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
    @Override
    public void delete(int id) {
        employeeDao.deleteEmployee(id);
    }
    @Override
    public List<Employee> searchEmployees(String searchQuery) {
        return employeeDao.searchEmployees(searchQuery);
    }
}
