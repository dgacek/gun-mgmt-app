package com.example.demo.model.repo;

import com.example.demo.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<ModelEntity, Long> {
}
