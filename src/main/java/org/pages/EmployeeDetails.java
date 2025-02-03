package org.pages;

import org.apache.tapestry5.annotations.Property;
import org.data.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetails {

    @Property
    private Employee employee;

    private static final List<Employee> EMPLOYEESLIST = new ArrayList<>();

    static {
        EMPLOYEESLIST.add(new Employee(5, "Noor", 2, "Todi"));
        EMPLOYEESLIST.add(new Employee(6, "Ayan", 3, "Nisar House"));
    }

    public List<Employee> getEmployees() {
        return EMPLOYEESLIST;
    }

    public static List<Employee> getEmployeesList() {
        return EMPLOYEESLIST;
    }

}
