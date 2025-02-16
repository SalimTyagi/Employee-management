package org.pages;

import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.services.EmployeeService;

public class DeleteEmployee {

    @PageActivationContext
    @Property
    private int employeeId;

    @Inject
    private EmployeeService employeeService;

    @Property
    private boolean deleted; // Used to display success message

    void setupRender() {
        if (employeeId > 0) {
            employeeService.delete(employeeId);
            deleted = true; // Set flag to show message in TML
        }
    }
}
