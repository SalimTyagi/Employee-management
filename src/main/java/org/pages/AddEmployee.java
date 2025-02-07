package org.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
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
    private List<Permission> availablePermissions;

    @Property
    private Set<Permission> selectedPermissions = new HashSet<>();

    @Property
    private String errorMessage;

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

    public void setupRender() {
        availableRoles = roleService.findAllRoles();
        availablePermissions = permissionService.findAllPermissions();
    }

    public void onValidateFromAddEmployeeForm() {
        if (!isValidInput()) {
            errorMessage = "Invalid input. Please check the fields and try again.";
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

            // Assign permissions based on role
            if (selectedRole.getRoleType() == RoleType.ADMIN) {
                employee.setPermissions(new HashSet<>(availablePermissions)); // Admin gets all permissions
            } else {
                employee.setPermissions(selectedPermissions); // Assign selected permissions
            }

            employeeService.saveEmployee(employee);
            return linkSource.createPageRenderLink(EmployeeDetails.class);
        }
        errorMessage = "Invalid input. Please check the fields and try again.";
        return this;
    }

    private boolean isValidInput() {
        return name != null && !name.trim().isEmpty() &&
                age > 0 &&
                address != null && !address.trim().isEmpty() &&
                password != null && !password.trim().isEmpty() &&
                selectedRole != null;
    }
}
