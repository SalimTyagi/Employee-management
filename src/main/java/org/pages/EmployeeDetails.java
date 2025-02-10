package org.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.enums.PermissionType;
import org.data.services.EmployeeService;
import org.data.services.LoginService;
import org.data.services.PermissionService;

import java.util.List;

public class EmployeeDetails {
    @Property
    private Employee employee;
    @Property
    private Employee loggedInEmployee;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private PermissionService permissionService;
    @Inject
    private LoginService loginService;

    void setupRender() {
        loggedInEmployee = loginService.getLoggedInEmployee(); // Get logged-in user
    }

    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    public boolean getCanEdit() {
        return permissionService.hasPermission(loggedInEmployee, PermissionType.EDIT);
    }

    public boolean getCanDelete() {
        return permissionService.hasPermission(loggedInEmployee, PermissionType.DELETE);
    }

    public boolean getCanCreate() {
        return permissionService.hasPermission(loggedInEmployee, PermissionType.CREATE);
    }

    public String getEmployeeRole() {
        return (employee.getRole() != null) ? employee.getRole().getRoleType().getDisplayName() : "No Role Assigned";
    }


}
