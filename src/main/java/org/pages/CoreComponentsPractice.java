package org.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.internal.services.StringValueEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoreComponentsPractice {

    @Property
    private StringValueEncoder stringValueEncoder = new StringValueEncoder();

    @Property
    private boolean checkboxValue;

    @Property
    private List<String> checklistSelectedValues;

    @Property
    private String[] STATIONERY = { "Pens", "Pencils", "Paper","Learning" };

    @Property
    private Date dateField;

    @Property
    private List<String> paletteValues = new ArrayList<>();;

    @Property
    private String[] pets = {"Cat","Dog","Rabbit"};

    @Property
    private String selectValue;

    @Property
    private String textField;

    @Property
    private String textArea;
}
