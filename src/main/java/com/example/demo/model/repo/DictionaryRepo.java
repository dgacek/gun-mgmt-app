package com.example.demo.model.repo;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.entity.DictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DictionaryRepo extends JpaRepository<DictionaryEntity, Long> {
    @Query("SELECT new com.example.demo.model.dto.DictionaryData(d.id, d.name) FROM DictionaryEntity d WHERE TYPE(d) = :type")
    List<DictionaryData> findAllByType(@Param("type") Class<? extends DictionaryEntity> type);
}
