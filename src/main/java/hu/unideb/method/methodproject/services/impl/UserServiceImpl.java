package hu.unideb.method.methodproject.services.impl;

import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.mapper.UserMapper;
import hu.unideb.method.methodproject.repositories.UserRepository;
import hu.unideb.method.methodproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    /**
     * Finds user by username
     * @param username name of the user
     * @return UserDto
     */
    @Override
    public UserDto findUserByUserName(String username) {
        UserDto user = userMapper.userToUserDto(userRepository.findUserByUsername(username));
        return user;
    }

    /**
     * Saves user data
     * @param userDto user data
     */
    @Override
    public void save(UserDto userDto) {
        userRepository.save(userMapper.userDtoToUser(userDto));
    }

    /**
     * Deletes user data
     * @param userDto user data
     */
    @Override
    public void delete(UserDto userDto) {

        userRepository.deleteById(userDto.getUsername());
    }


}
