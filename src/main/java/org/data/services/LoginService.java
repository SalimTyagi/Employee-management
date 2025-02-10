package org.data.services;

import org.data.entities.Employee;

public interface LoginService {
    boolean validateLogin(String userName, String password);
    Employee getLoggedInEmployee();
    void setLoggedInEmployee(Employee employee);
}
