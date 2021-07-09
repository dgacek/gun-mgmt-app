package com.example.demo.repo;

import com.example.demo.entity.CaliberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaliberRepo extends JpaRepository<CaliberEntity, Long> {
}
