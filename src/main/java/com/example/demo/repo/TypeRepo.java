package com.example.demo.repo;

import com.example.demo.entity.TypeDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepo extends JpaRepository<TypeDictionary, Long> {
}
