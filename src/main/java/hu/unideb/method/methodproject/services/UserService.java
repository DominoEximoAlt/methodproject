package hu.unideb.method.methodproject.services;

import hu.unideb.method.methodproject.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getAll();
}
