package com.example.demo.repo;

import com.example.demo.entity.CaliberDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaliberRepo extends JpaRepository<CaliberDictionary, Long> {
}
