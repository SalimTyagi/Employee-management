package org.data.services;

import org.data.entities.Employee;

public interface LoginService {

    boolean validateLogin(String userName, String password);
    Employee getLoggedInEmployee();  // ✅ Retrieve the logged-in user
    void setLoggedInEmployee(Employee employee); // ✅ Store the logged-in user
}
