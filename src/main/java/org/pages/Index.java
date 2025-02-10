package org.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.services.LoginService;

public class Index {
    @Property
    private String username;
    @Property
    private String password;
    @Property
    private String loginMessage;
    @Inject
    private Messages messages;
    @Inject
    private PageRenderLinkSource linkSource;
    @Inject
    private LoginService loginService;
    @Component
    private Form loginForm;

    public void onValidateFromLoginForm(){
        if(!loginService.validateLogin(username,password)){
            loginMessage = messages.get("invalid-login");
            loginForm.recordError(loginMessage);
        }
    }

    public Object onSuccessFromLoginForm(){
        return linkSource.createPageRenderLink(EmployeeDetails.class);
    }

    public String getGreetingMessage() {
        return messages.get("greeting");
    }

}
