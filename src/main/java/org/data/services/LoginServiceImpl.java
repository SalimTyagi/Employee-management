package org.data.services;

import org.data.dao.EmployeeDao;
import org.data.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private EmployeeDao employeeDao;
    private Employee loggedInEmployee;

    @Override
    public boolean validateLogin(String userName, String password) {
        return employeeDao.authenticate(userName,password);
    }

    @Override
    public Employee getLoggedInEmployee() {
        return loggedInEmployee;
    }

    @Override
    public void setLoggedInEmployee(Employee employee) {
        this.loggedInEmployee = employee;
    }
}
