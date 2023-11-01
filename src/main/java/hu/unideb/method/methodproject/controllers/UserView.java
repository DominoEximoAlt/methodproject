package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionScope
public class UserView {

    @Autowired
    UserService userService;

    private List<UserDto> users;

    private UserDto currentUser;

    private boolean loggedIn = false;

    @PostConstruct
    public void getAllUsers(){
        if(this.getUsers().isEmpty()){
            this.getUsers().clear();
        }
        this.getUsers().addAll(userService.getAll());
    }

    /**
     * Checks wether the given user is stored in the database or not
     * Also sets up the currently logged in profile
     * @param userDto the user to check and log in
     */
    public void loginUser(UserDto userDto){
        UserDto toBeLoggedIn = userService.findUserByUserName(userDto.getUsername());
        if(toBeLoggedIn != null){
            if (toBeLoggedIn.getUsername().equals(currentUser.getUsername()) && toBeLoggedIn.getPassword().equals(currentUser.getPassword())){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCES", "Logged in successfully!");
                PrimeFaces.current().dialog().showMessageDynamic(message);
                loggedIn = true;
            }else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "LOGIN ERROR", "Could not find user with given username and password!");
                PrimeFaces.current().dialog().showMessageDynamic(message);
                loggedIn = false;
            }
        }else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "LOGIN ERROR", "Could not find user with given username and password!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            loggedIn = false;
        }
    }

    /**
     * Registers the user into the database if not already stored
     * @param userDto the user to be registered
     */
    public void registerUser(UserDto userDto){
        if(userService.findUserByUserName(userDto.getUsername()) != null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "FAILURE", "User with name already exists!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }else{
            userService.save(userDto);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCES", "Succesfully registered!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public List<UserDto> getUsers() {
        if(null == users){
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public UserDto getCurrentUser() {
        if(null == currentUser){
            currentUser = new UserDto();
        }
        return currentUser;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setCurrentUser(UserDto currentUser) {
        this.currentUser = currentUser;
    }
}
