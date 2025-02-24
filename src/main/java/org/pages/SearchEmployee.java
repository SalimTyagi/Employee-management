package org.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.services.EmployeeService;

import java.util.List;

public class SearchEmployee {
    @Property
    private String searchQuery;
    @Property
    @Persist
    private List<Employee> searchResults;
    @Inject
    private EmployeeService employeeService;
    @Property
    private Employee employee;
    @Component
    private Form searchForm;
    void onActivateFromSearchForm() {
        searchResults = null;
    }
    void onSuccessFromSearchForm() {
        searchResults = employeeService.searchEmployees(searchQuery);
    }
}

