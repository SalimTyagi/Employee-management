package org.pages;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.data.entities.Employee;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Role;
import org.data.services.EmployeeService;
import org.data.services.RoleService;

import java.util.List;


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
    private String errorMessage;
    @Property
    private Role selectedRole;
    @Property
    private List<Role> availableRoles;
    @Inject
    private PageRenderLinkSource linkSource;
    @Component
    private Form addEmployeeForm;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private RoleService roleService;

    public void setupRender(){
        availableRoles = roleService.findAllRoles();
    }

    public void onValidateFromAddEmployeeForm(){
        if(!isValidInput()){
            errorMessage = "Invalid input. Please check the fields and try again.";
            addEmployeeForm.recordError(errorMessage);
        }
    }

    public Object onSuccessFromAddEmployeeForm() {
        if (isValidInput()) {
            Employee employee = new Employee(0, name, age, address,password,selectedRole);
            employeeService.saveEmployee(employee);
            return linkSource.createPageRenderLink(EmployeeDetails.class);
        }
        errorMessage = "Invalid input. Please check the fields and try again.";
        return this;
    }

    private boolean isValidInput() {
        return name != null && !name.trim().isEmpty() &&
                age != 0 && age > 0 &&
                address != null && !address.trim().isEmpty();
    }
}
