package org.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Index {

    @Property
    @Persist
    private String username;

    @Property
    @Persist
    private String password;

    @Property
    private String loginMessage;

    @Inject
    private PageRenderLinkSource linkSource;

    @Component
    private Form loginForm;

    private boolean isValidLogin() {
        return "admin".equals(username) && "password123".equals(password);
    }

    public void onPrepare(){

    }

    public void onValidateFromLoginForm() {
        if (!"admin".equals(username) || !"password123".equals(password)) {
            loginMessage = "Invalid username or password.";
            loginForm.recordError(loginMessage);
        }
    }

    public Object onSuccessFromLoginForm() {
        if (isValidLogin()) {
            return linkSource.createPageRenderLink(EmployeeDetails.class);
        }
        else {
            loginMessage = "Invalid username or password.";
            return this;
        }
    }


}
