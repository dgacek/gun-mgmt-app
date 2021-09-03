package com.example.demo.service.implementation;

import com.example.demo.model.dto.User;
import com.example.demo.model.dto.UserInput;
import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.repo.RoleRepo;
import com.example.demo.model.repo.UserRepo;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public User addUser(UserInput userInput) {
        RoleEntity roleEntity = roleRepo.findById(userInput.getRoleId())
                .orElseThrow(() -> new IdNotFoundException("Role of id:"+userInput.getRoleId()+" could not be found in the database"));
        return UserMapper.INSTANCE
                .toUser(userRepo
                        .save(UserEntity
                                .builder()
                                .roleEntity(roleEntity)
                                .email(userInput.getEmail())
                                .phone(userInput.getPhone())
                                .username(userInput.getUsername())
                                .password(passwordEncoder.encode(userInput.getPassword()))
                                .build()
                        )
                );
    }

    public List<User> findAllUsers() {
        return UserMapper.INSTANCE.toUserList(userRepo.findAll());
    }

    @Transactional
    public User updateUser(UserInput userInput) {
        UserEntity userEntity = userRepo.findById(userInput.getId())
                .orElseThrow(() -> new IdNotFoundException("User of id:"+userInput.getId()+" could not be found in the database"));
        // checking if the user-provided role id is valid
        RoleEntity roleEntity = roleRepo.findById(userInput.getRoleId())
                .orElseThrow(() -> new IdNotFoundException("Role of id:"+userInput.getRoleId()+" could not be found in the database"));
        //have to use setters because builder doesnt work
        userEntity.setRoleEntity(roleEntity);
        userEntity.setUsername(userInput.getUsername());
        userEntity.setEmail(userInput.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userInput.getPassword()));
        userEntity.setPhone(userInput.getPhone());
        return UserMapper.INSTANCE.toUser(userEntity);
    }

    public User findUserById(Long id) {
        return UserMapper.INSTANCE.toUser(userRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("User of id:"+id+" could not be found in the database")));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
