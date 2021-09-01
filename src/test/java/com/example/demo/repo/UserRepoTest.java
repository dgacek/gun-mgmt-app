package com.example.demo.repo;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
class UserRepoTest {
    @Autowired
    UserRepo userRepo;

    @Test
    void findByUsername() {
        UserEntity user = userRepo.findByUsername("admin").orElseThrow(() -> new UsernameNotFoundException("yeah rip"));
        Assertions.assertEquals("admin", user.getUsername());
    }
}
