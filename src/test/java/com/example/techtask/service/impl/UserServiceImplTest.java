package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.repositories.UserRepository;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    private User user;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testFindUser() {
        when(userRepository.findUserWithMaxSumByYear(anyInt())).thenReturn(Optional.of(user));
        User result = userService.findUser();
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void testFindUser_NoResultException() {
        when(userRepository.findUserWithMaxSumByYear(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoResultException.class, () -> userService.findUser());
    }

    @Test
    void testFindUsers() {
        List<User> users = List.of(user);
        when(userRepository.findPaidOrdersByYear(anyInt())).thenReturn(users);
        List<User> result = userService.findUsers();
        assertNotNull(result);
        assertEquals(users, result);
    }
}