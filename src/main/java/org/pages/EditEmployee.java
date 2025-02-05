package org.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.services.EmployeeService;

import java.util.List;

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

    void setUpRender(){
        if(employee!=null){
            employeeService.findEmployeeById(employeeId);
        }
    }

    public void onActivate(int employeeId) {
        this.employeeId = employeeId;
        this.employee = employeeService.findEmployeeById(employeeId);
        if (employee != null) {
            this.name = employee.getName();
            this.age = employee.getAge();
            this.address = employee.getAddress();
            this.password=employee.getPassword();
        }
    }

    public int onPassivate() {
        return employeeId;
    }

    public void onValidateFromEditEmployeeForm() {
        if (!isValidInput()) {
            errorMessage = messages.get("invalid input");
            editEmployeeForm.recordError(errorMessage);
        }
    }

    public Object onSuccessFromEditEmployeeForm() {
        if (employee != null && isValidInput()) {
            employee.setName(name);
            employee.setAge(age);
            employee.setAddress(address);
            employee.setPassword(password);
            employeeService.saveEmployee(employee);
            return EmployeeDetails.class;
        }
        errorMessage = "Employee not found or invalid input.";
        return this;
    }

    private boolean isValidInput() {
        return name != null && !name.trim().isEmpty() &&
                age > 0 &&
                address != null && !address.trim().isEmpty();
    }
}
