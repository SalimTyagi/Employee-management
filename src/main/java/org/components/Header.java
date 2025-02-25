package org.components;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Header {
    @Inject
    private Messages messages;
    public String getCompanyName() {
        return messages.get("company-name");
    }

    public String getCompanyAddress() {
        return messages.get("company-address");
    }
}
