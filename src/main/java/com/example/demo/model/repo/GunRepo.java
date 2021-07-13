package com.example.demo.model.repo;

import com.example.demo.model.entity.GunEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GunRepo extends JpaRepository<GunEntity, Long> {
}
