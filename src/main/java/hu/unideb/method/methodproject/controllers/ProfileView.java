package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.ProfileDto;
import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.mapper.UserMapper;
import hu.unideb.method.methodproject.services.ProfileService;
import hu.unideb.method.methodproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.bean.ViewScoped;

@Controller
@ViewScoped
public class ProfileView {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    private ProfileDto currentProfile;

    public void loadProfile(UserDto currentUser){
        if (currentUser != null){
            setCurrentProfile(profileService.findByUserId(currentUser.getUsername()));
        }
    }

    public void saveProfile(UserDto userDto){
        User user = userMapper.userDtoToUser(userService.findUserByUserName(userDto.getUsername()));
        currentProfile.setUser(user);
        profileService.save(currentProfile);
    }

    public ProfileDto getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(ProfileDto currentProfile) {
        this.currentProfile = currentProfile;
    }



}
