package org.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.services.EmployeeService;

public class DeleteEmployee {
    @Inject
    private EmployeeService employeeService;
    @Property
    private int employeeId;

    public Object onActivate(int id){
        this.employeeId = id;
        employeeService.delete(id);
        return EmployeeDetails.class;
    }

    public int onPassivate(){
        return employeeId;
    }
}
