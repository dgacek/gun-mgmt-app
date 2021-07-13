package com.example.demo.service.implementation;

import com.example.demo.dto.User;
import com.example.demo.dto.UserInput;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;

    public User addUser(UserInput userInput) {
        var roleEntity = roleRepo.findById(userInput.getRoleId())
                .orElseThrow(() -> new IdNotFoundException("Role of id:"+userInput.getRoleId()+" could not be found in the database"));
        return userMapper.toUser(userRepo.save(new UserEntity(
                null,
                roleEntity,
                userInput.getEmail(),
                userInput.getPhone(),
                userInput.getUsername(),
                userInput.getPassword()
        )));
    }

    public List<User> findAllUsers() {
        return userMapper.toUserList(userRepo.findAll());
    }

    @Transactional
    public User updateUser(UserInput userInput) {
        UserEntity userEntity = userRepo.findById(userInput.getId())
                .orElseThrow(() -> new IdNotFoundException("User of id:"+userInput.getId()+" could not be found in the database"));
        RoleEntity roleEntity = roleRepo.findById(userInput.getRoleId())
                .orElseThrow(() -> new IdNotFoundException("Role of id:"+userInput.getRoleId()+" could not be found in the database"));
        userEntity.setRoleEntity(roleEntity);
        userEntity.setUsername(userInput.getUsername());
        userEntity.setEmail(userInput.getEmail());
        userEntity.setPassword(userInput.getPassword());
        userEntity.setPhone(userInput.getPhone());
        return userMapper.toUser(userEntity);
    }

    public User findUserById(Long id) {
        return userMapper.toUser(userRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("User of id:"+id+" could not be found in the database")));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
