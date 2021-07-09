package com.example.demo.repo;

import com.example.demo.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<ModelEntity, Long> {
}
