package com.example.demo.service;

import com.example.demo.dto.User;
import com.example.demo.dto.UserInput;
import com.example.demo.entity.UserEntity;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity addUser(UserInput user) {
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepo.findAll();
        for (UserEntity userEntity: userEntities) {
            users.add(new User(userEntity));
        }
        return users;
    }

    public User updateUser(UserEntity userEntity) {
        return new User(userRepo.save(userEntity));
    }
}
