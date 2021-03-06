package com.example.demo.service;

import com.example.demo.model.dto.User;
import com.example.demo.model.dto.UserInput;

import java.util.List;

public interface UserService {
    User addUser(UserInput userInput);
    List<User> findAllUsers();
    User updateUser(UserInput userInput);
    User findUserById(Long id);
    void deleteUser(Long id);
}
