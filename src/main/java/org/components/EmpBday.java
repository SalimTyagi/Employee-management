package org.components;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.data.entities.Employee;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EmpBday {
    @Parameter(required = true)
    private Employee employee;
    @InjectComponent
    private WishBanner wishBanner;

    public boolean isBirthdayToday() {
        // Convert current date to LocalDate
        LocalDate today = LocalDate.now();

        // Convert Date to LocalDate
        Date birthDate = employee.getDateOfBirth();
        LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Compare only month and day
        return today.getMonth() == birthLocalDate.getMonth() &&
                today.getDayOfMonth() == birthLocalDate.getDayOfMonth();
    }
}
