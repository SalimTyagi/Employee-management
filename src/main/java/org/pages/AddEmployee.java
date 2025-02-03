package org.pages;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.data.entities.Employee;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.services.EmployeeService;

import java.util.List;


public class AddEmployee {

    @Property
    private String name;

    @Property
    private int age;

    @Property
    private String address;

    @Property
    private String errorMessage;

    @Inject
    private PageRenderLinkSource linkSource;

    @Component
    private Form addEmployeeForm;

    @Inject
    private EmployeeService employeeService;

    public void onValidateFromAddEmployeeForm(){
        if(!isValidInput()){
            errorMessage = "Invalid input. Please check the fields and try again.";
            addEmployeeForm.recordError(errorMessage);
        }
    }

    public Object onSuccessFromAddEmployeeForm() {
        if (isValidInput()) {
            List<Employee> employees = EmployeeDetails.getEmployeesList();
            Employee employee = new Employee(employees.size() + 1, name, age, address);
            employees.add(employee);
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
