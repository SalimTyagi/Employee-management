package org.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;

public class WishBanner {

    @Parameter(required = true)
    @Property
    private Employee employee;
    @Inject
    private ComponentResources componentResources;

    @SetupRender
    void beginRender(MarkupWriter writer) {
        if (employee == null) return;
        String color = employee.getGender().equalsIgnoreCase("Female") ? "pink" : "blue";

        writer.element("div",
                "style", "background-color:" + color + "; padding: 10px; color: white; text-align: center; border-radius: 5px;");
        writer.write("🎉 Happy Birthday " + employee.getName() + "! 🎂");
        writer.end();
    }

    @AfterRender
    void afterRender() {
        componentResources.triggerEvent("birthdayWished", null, null);
    }
}
