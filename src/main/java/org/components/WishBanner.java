package org.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.data.entities.Employee;

public class WishBanner {
    @Parameter(required = true)
    private Employee employee;

    void beginRender(MarkupWriter writer) {
        String color = employee.getGender().equalsIgnoreCase("Female") ? "pink" : "blue";
        writer.element("div", "style", "background-color:" + color + "; padding: 10px; color: white; text-align: center; border-radius: 5px;");
        writer.write("🎉 Happy Birthday " + employee.getName() + "! 🎂");
        writer.end();
    }
}
