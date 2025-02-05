package org.pages;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.services.EmployeeService;

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
}
