package com.example.demo.repo;

import com.example.demo.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepo extends JpaRepository<TypeEntity, Long> {
}
