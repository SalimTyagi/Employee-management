package org.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.services.EmployeeService;
import java.util.stream.Collectors;

public class EmployeeDetailsView {

    @ActivationRequestParameter("employeeId")
    @Property
    private int employeeId;

    @Property
    private Employee employee;

    @Inject
    private EmployeeService employeeService;

    public void onActivate(int employeeId) {
        this.employeeId = employeeId;
        this.employee = employeeService.findEmployeeById(employeeId);
        if (this.employee == null) {
            throw new RuntimeException("Employee not found!");
        }
    }

    public int onPassivate() {
        return employeeId;
    }

    //  Get Role Name
    public String getRoleName() {
        return (employee.getRole() != null) ? employee.getRole().getRoleType().getDisplayName() : "No Role Assigned";
    }

    //  Get Permissions as Comma-Separated String
    public String getPermissionsList() {
        return (employee.getPermissions() != null && !employee.getPermissions().isEmpty())
                ? employee.getPermissions().stream()
                .map(p -> p.getPermissionType().getDisplayName())
                .collect(Collectors.joining(", "))
                : "No Permissions Assigned";
    }
}
