package hu.unideb.method.methodproject.services;

import hu.unideb.method.methodproject.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto findUserByUserName(String username);
    void save(UserDto userDto);

    void delete(UserDto userDto);
}
