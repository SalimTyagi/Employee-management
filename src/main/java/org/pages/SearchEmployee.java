package org.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployee {
    @Property
    private String searchQuery;
    @Property
    private List<Employee> searchResults;
    @Inject
    private EmployeeService employeeService;

    void onValidateFromSearchForm() {
        if (searchQuery == null || searchQuery.isEmpty()) {
            searchResults = new ArrayList<>();
        }
    }
    void onSuccess() {
        searchResults = employeeService.searchEmployees(searchQuery);
    }
}

