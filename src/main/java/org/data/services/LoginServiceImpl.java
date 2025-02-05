package org.data.services;

import org.data.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public boolean validateLogin(String userName, String password) {
        return employeeDao.authenticate(userName,password);
    }
}
