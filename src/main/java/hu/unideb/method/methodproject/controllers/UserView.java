package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

@Controller
@ApplicationScope
public class UserView {

    @Autowired
    UserService userService;

    @Autowired
    NavigationController navigationController;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    private List<UserDto> users;

    private UserDto currentUser;

    private boolean loggedIn;

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
            if (toBeLoggedIn.getUsername().equals(userDto.getUsername()) && bCryptPasswordEncoder.matches(userDto.getPassword(),toBeLoggedIn.getPassword())){
                setLoggedIn(true);
            }else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "FAILURE", "Username or password incorrect!");
                PrimeFaces.current().dialog().showMessageDynamic(message);
                setLoggedIn(false);
            }
        }
    }

    /**
     * Logs the user out by setting the current user to null
     */
    public void logout(){
        setLoggedIn(false);
        setCurrentUser(null);
    }

    /**
     * Registers the user into the database if not already stored
     * @param userDto the user to be registered
     */
    public void registerUser(UserDto userDto) {
        if(userService.findUserByUserName(userDto.getUsername()) != null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "FAILURE", "User with name already exists!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }else{
            setLoggedIn(true);
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            userService.save(userDto);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCES", "Succesfully registered!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            userDto.setUsername("");
            userDto.setPassword("");


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
