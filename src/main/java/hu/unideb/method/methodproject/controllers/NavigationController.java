package hu.unideb.method.methodproject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.faces.bean.ViewScoped;


@Controller
@ApplicationScope
public class NavigationController {

    /**
     * Returns a string of the page to be redirected depending on being logged in
     *
     * @return string link and window location
     */
    public String moveToProfilePage(boolean loginStatus) {
        if(Boolean.TRUE.equals(loginStatus)){
            return "window.location.href = '/fitness/myprofile.jsf'";
        }
        return null;

    }
    public String moveToLoginPage() {
        return "window.location.href = '/fitness/login.jsf'";

    }

}