package hu.unideb.method.methodproject.mapper;

import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username",target = "username")
    @Mapping(source = "password",target = "password")
    UserDto userToUserDto(User user);

    @Mapping(source = "username",target = "username")
    @Mapping(source = "password",target = "password")
    User userDtoToUser(UserDto userDto);

    @Mapping(source = "username",target = "username")
    @Mapping(source = "password",target = "password")
    List<UserDto> userlistToUserDtoList(List<User> users);

    @Mapping(source = "username",target = "username")
    @Mapping(source = "password",target = "password")
    List<User> userDtolistToUserList(List<UserDto> userDtos);
}
