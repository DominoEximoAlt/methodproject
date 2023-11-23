package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserViewTest {

    @Mock
    UserService userService;

    @Mock
    NavigationController navigationController;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserView underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        underTest = null;
    }

    @Test
    void testGetUsersShouldReturnEmptyList() {
        List<UserDto> expected = new ArrayList<>();

        List<UserDto> actual = underTest.getUsers();

        assertEquals(actual,expected);

    }

    @Test
    void testLoginUserShouldLogUserIn() {

        UserDto user = new UserDto();
        user.setUsername("David");
        user.setPassword(bCryptPasswordEncoder.encode("123"));

        given(userService.findUserByUserName(user.getUsername())).willReturn(user);
        given(bCryptPasswordEncoder.matches(user.getPassword(),underTest.getCurrentUser().getPassword())).willReturn(true);

        underTest.loginUser(user);

        assertTrue(underTest.isLoggedIn());
    }

    @Test
    void registerUser() {
    }
}