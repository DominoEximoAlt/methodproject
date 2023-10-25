package hu.unideb.method.methodproject.services.impl;

import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.repositories.UserRepository;
import hu.unideb.method.methodproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
