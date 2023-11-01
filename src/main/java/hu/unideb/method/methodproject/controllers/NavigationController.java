package hu.unideb.method.methodproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@RequestScope
public class NavigationController {

    public String moveToProfilePage(boolean loggedIn){
        if (loggedIn){
            return "window.location.href = '/fitness/myprofile.jsf'";
        }else {
            return "window.location.href = '/fitness/login.jsf'";
        }


    }
}
