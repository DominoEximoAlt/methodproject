package hu.unideb.method.methodproject.services.impl;

import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.mapper.UserMapper;
import hu.unideb.method.methodproject.repositories.UserRepository;
import hu.unideb.method.methodproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    /**
     * Converts users to userdtos and returns them.
     * @return List<UserDto>
     */
    @Override
    public List<UserDto> getAll() {
        List<UserDto> dtos = userMapper.userlistToUserDtoList(userRepository.findAll());
        return dtos;
    }

}
