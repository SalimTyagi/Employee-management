package org.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.entities.Role;
import org.data.entities.Permission;
import org.data.enums.RoleType;
import org.data.enums.PermissionType;
import org.data.services.EmployeeService;
import org.data.services.RoleService;
import org.data.services.PermissionService;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EditEmployee {

    @ActivationRequestParameter("employeeId")
    @Property
    private int employeeId;
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
    @Inject
    private Messages messages;
    @Property
    private String errorMessage;
    @Component
    private Form editEmployeeForm;
    @Property
    private Employee employee;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private RoleService roleService;
    @Inject
    private PermissionService permissionService;
    @Property
    private String selectedGender;
    @Property
    private Date dateOfBirth;
    @Component
    private Select genderSelect;

    // Ensuring employee is retrieved properly before rendering
    void setupRender() {
        availableRoles = roleService.findAllRoles(); // Load all available roles

        if (employeeId > 0) {
            employee = employeeService.findEmployeeById(employeeId);
            if (employee != null) {
                name = employee.getName();
                age = employee.getAge();
                address = employee.getAddress();
                password = employee.getPassword();
                selectedRole = employee.getRole();
                selectedGender=employee.getGender();
                dateOfBirth=employee.getDateOfBirth();
            } else {
                errorMessage = messages.get("not-found");
            }
        }
    }
    public void onActivate(Integer employeeId) {
        if (employeeId != null) {
            this.employeeId = employeeId;
            employee = employeeService.findEmployeeById(employeeId);
        }
    }
    public int onPassivate() {
        return employeeId;
    }

    public void onValidateFromEditEmployeeForm() {
        if (!isValidInput()) {
            errorMessage = messages.get("Invalid input");
            editEmployeeForm.recordError(errorMessage);
        }
    }

    public Object onSuccessFromEditEmployeeForm() {
        if (employee != null && isValidInput()) {
            employee.setName(name);
            employee.setAge(age);
            employee.setAddress(address);
            employee.setPassword(password);
            employee.setRole(selectedRole);
            employee.setGender(selectedGender);
            employee.setDateOfBirth(dateOfBirth);
            employee.setPermissions(assignPermissions(selectedRole)); // Update permissions based on role
            employeeService.saveEmployee(employee);
            return EmployeeDetails.class;
        }
        return this;
    }

    private boolean isValidInput() {
        return name != null && !name.trim().isEmpty() &&
                age > 0 &&
                address != null && !address.trim().isEmpty() &&
                selectedRole != null &&
                selectedGender != null && !selectedGender.trim().isEmpty() &&
                dateOfBirth != null;
    }

    private Set<Permission> assignPermissions(Role role) {
        Set<Permission> permissions = new HashSet<>();
        List<Permission> allPermissions = permissionService.findAllPermissions();

        if (role.getRoleType() == RoleType.ADMIN) {
            permissions.addAll(allPermissions); // Admin gets all permissions
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
