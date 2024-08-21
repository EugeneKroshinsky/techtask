package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.repositories.UserRepository;
import com.example.techtask.service.UserService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUser() {
        return userRepository.findUserWithMaxSum().orElseThrow(NoResultException::new);
    }

    @Override
    public List<User> findUsers() {
        List<User> users = userRepository.findPaidOrders();
        if (users != null) {
            return users;
        } else {
            throw new NoResultException();
        }
    }
}
