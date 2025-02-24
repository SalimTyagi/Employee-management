package org.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.corelib.components.Zone;
import org.data.entities.Employee;
import org.data.entities.Permission;
import org.data.enums.PermissionType;
import org.data.enums.RoleType;
import org.data.services.EmployeeService;
import org.data.services.PermissionService;
import org.data.services.RoleService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeDetailsView {
    @ActivationRequestParameter("employeeId")
    @Property
    private int employeeId;
    @Property
    private Employee employee;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private RoleService roleService;
    @Inject
    private PermissionService permissionService;
    @InjectComponent
    private Zone roleZone;
    @InjectComponent
    private Zone birthdayZone;
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

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
    public String getRoleName() {
        return (employee.getRole() != null) ? employee.getRole().getRoleType().getDisplayName() : "No Role Assigned";
    }

    public String getPermissionsList() {
        return (employee.getPermissions() != null && !employee.getPermissions().isEmpty())
                ? employee.getPermissions().stream()
                .map(p -> p.getPermissionType().getDisplayName())
                .collect(Collectors.joining(", "))
                : "No Permissions Assigned";
    }

    public void onShowImage() {
        // Logic to display image in a popup
    }
    public void onPromoteToManager() {
        if (employee.getRole().getRoleType() != RoleType.MANAGER) {
            employee.setRole(roleService.getRoleByType(RoleType.MANAGER));

            // Update Permissions: VIEW & EDIT for Manager
            Set<Permission> newPermissions = new HashSet<>();
            newPermissions.add(permissionService.getPermissionByType(PermissionType.VIEW));
            newPermissions.add(permissionService.getPermissionByType(PermissionType.EDIT));
            employee.setPermissions(newPermissions);

            employeeService.saveEmployee(employee);

            ajaxResponseRenderer.addRender(roleZone);
        }
    }
    void onBirthdayHandled() {
        ajaxResponseRenderer.addRender(birthdayZone);
    }
}
