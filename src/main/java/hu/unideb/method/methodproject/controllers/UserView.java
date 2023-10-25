package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserView {

    @Autowired
    UserService userService;

    private List<UserDto> users;

    private UserDto currentUser;

    @PostConstruct
    public void getAllUsers(){
        if(this.getUsers().isEmpty()){
            this.getUsers().clear();
        }
        this.getUsers().addAll(userService.getAll());
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

    public void setCurrentUser(UserDto currentUser) {
        this.currentUser = currentUser;
    }
}
