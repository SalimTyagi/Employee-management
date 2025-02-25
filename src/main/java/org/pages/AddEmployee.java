package org.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.data.entities.Employee;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Role;
import org.data.entities.Permission;
import org.data.enums.PermissionType;
import org.data.enums.RoleType;
import org.data.services.EmployeeService;
import org.data.services.RoleService;
import org.data.services.PermissionService;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class AddEmployee {
    @Property
    private String name;
    @Property
    private int age;
    @Property
    private String address;
    @Property
    private String password;
    @Property
    private Role selectedRole;
    @Property
    private List<Role> availableRoles;
    @Property
    private String errorMessage;
    @Property
    private Date dateOfBirth;
    @Property
    private String selectedGender;
    @Inject
    private PageRenderLinkSource linkSource;
    @Component
    private Form addEmployeeForm;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private RoleService roleService;
    @Inject
    private PermissionService permissionService;
    @Inject
    private Messages messages;

    public void setupRender() {
        //  is a lifecycle method in Tapestry that runs before the page renders.
        // Ensuring available roles retrieved properly before rendering
        availableRoles = roleService.findAllRoles();
    }

    public void onValidateFromAddEmployeeForm() {
        if (!isValidInput()) {
            errorMessage= messages.get("invalid-input");
            addEmployeeForm.recordError(errorMessage);
        }
    }

    public Object onSuccessFromAddEmployeeForm() {
        if (isValidInput()) {
            Employee employee = new Employee();
            employee.setName(name);
            employee.setAge(age);
            employee.setAddress(address);
            employee.setPassword(password);
            employee.setRole(selectedRole);
            employee.setGender(selectedGender);
            employee.setDateOfBirth(dateOfBirth);
            employee.setPermissions(assignPermissionsBasedOnRole(selectedRole));

            employeeService.saveEmployee(employee);
            return linkSource.createPageRenderLink(EmployeeDetails.class);
        }
        return this;
    }

    private boolean isValidInput() {
        return name != null && !name.trim().isEmpty() &&
                age > 0 &&
                address != null && !address.trim().isEmpty() &&
                password != null && !password.trim().isEmpty() &&
                selectedRole != null &&
                selectedGender != null && !selectedGender.trim().isEmpty() &&
                dateOfBirth != null;
    }

    private Set<Permission> assignPermissionsBasedOnRole(Role role) {
        Set<Permission> permissions = new HashSet<>();
        List<Permission> allPermissions = permissionService.findAllPermissions();

        if (role.getRoleType() == RoleType.ADMIN) {
            permissions.addAll(allPermissions);
        } else if (role.getRoleType() == RoleType.MANAGER) {
            permissions.addAll(allPermissions.stream()
                    .filter(p -> p.getPermissionType() == PermissionType.VIEW || p.getPermissionType() == PermissionType.EDIT)
                    .collect(Collectors.toSet()));
        } else if (role.getRoleType() == RoleType.EMPLOYEE) {
            permissions.addAll(allPermissions.stream()
                    .filter(p -> p.getPermissionType() == PermissionType.VIEW)
                    .collect(Collectors.toSet()));
        }
        return permissions;
    }
}
