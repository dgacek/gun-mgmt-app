package com.example.demo.repo;

import com.example.demo.entity.ManufacturerDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepo extends JpaRepository<ManufacturerDictionary, Long> {
}
