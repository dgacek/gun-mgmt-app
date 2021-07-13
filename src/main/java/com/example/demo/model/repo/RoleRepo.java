package com.example.demo.model.repo;

import com.example.demo.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
}
