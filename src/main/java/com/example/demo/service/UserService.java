package com.example.demo.service;

import com.example.demo.dto.User;
import com.example.demo.dto.UserInput;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public User addUser(UserInput userInput) {
        var roleEntity = roleRepo.findById(userInput.getRoleId())
                .orElseThrow(() -> new IdNotFoundException("Role of id:"+userInput.getRoleId()+" could not be found in the database"));
        var userEntity = new UserEntity(
                    roleEntity,
                    userInput.getEmail(),
                    userInput.getPhone(),
                    userInput.getUsername(),
                    userInput.getPassword()
                );
        userRepo.save(userEntity);
        return new User(userEntity);
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepo.findAll();
        for (UserEntity userEntity: userEntities) {
            users.add(new User(userEntity));
        }
        return users;
    }

    public User updateUser(UserInput userInput) {
        var userEntity = userRepo.findById(userInput.getId())
                .orElseThrow(() -> new IdNotFoundException("User of id:"+userInput.getId()+" could not be found in the database"));
        var roleEntity = roleRepo.findById(userInput.getRoleId())
                .orElseThrow(() -> new IdNotFoundException("Role of id:"+userInput.getRoleId()+" could not be found in the database"));
        userEntity.setRoleEntity(roleEntity);
        userEntity.setUsername(userInput.getUsername());
        userEntity.setEmail(userInput.getEmail());
        userEntity.setPassword(userInput.getPassword());
        userEntity.setPhone(userInput.getPhone());
        return new User(userEntity);
    }

    public User findUserById(Long id) {
        var userEntity = userRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("User of id:"+id+" could not be found in the database"));
        return new User(userEntity);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
