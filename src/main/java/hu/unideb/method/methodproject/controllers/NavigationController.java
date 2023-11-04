package hu.unideb.method.methodproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@SessionScope
public class NavigationController {

    /**
     * Returns a string of the page to be redirected depending on being logged in
     *
     * @param loggedIn boolean login status
     * @return string link and window location
     */
    public String moveToProfilePage() {
        return "window.location.href = '/fitness/myprofile.jsf'";

    }
    public String moveToLoginPage() {
        return "window.location.href = '/fitness/login.jsf'";

    }

}