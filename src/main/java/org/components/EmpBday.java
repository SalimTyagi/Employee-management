package org.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EmpBday {
    @Property
    @Parameter(required = true,  allowNull = false)
    private Employee employee;
    @Inject
    private ComponentResources componentResources;
    @Property
    private boolean isBirthdayToday;

    void setupRender() {
        if (employee == null || employee.getDateOfBirth() == null) {
            isBirthdayToday = false;
            return;
        }
        isBirthdayToday = isBirthdayToday();
    }
    public boolean isBirthdayToday() {

        if (employee == null || employee.getDateOfBirth() == null) {
            return false; // No birthday available
        }
        // Convert current date to LocalDate
        LocalDate today = LocalDate.now();

        // Convert Date to LocalDate
        Date birthDate = employee.getDateOfBirth();
        LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Compare only month and day
        return today.getMonth() == birthLocalDate.getMonth() &&
                today.getDayOfMonth() == birthLocalDate.getDayOfMonth();
    }

    void onBirthdayWished() {
        componentResources.triggerEvent("birthdayHandled", null, null);
    }

}
