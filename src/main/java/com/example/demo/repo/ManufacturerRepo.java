package com.example.demo.repo;

import com.example.demo.entity.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepo extends JpaRepository<ManufacturerEntity, Long> {
}
