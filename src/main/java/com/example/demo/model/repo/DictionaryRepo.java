package com.example.demo.model.repo;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.entity.DictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepo extends JpaRepository<DictionaryEntity, Long> {
    @Query("SELECT new com.example.demo.model.dto.DictionaryData(d.id, d.name) " +
            "FROM DictionaryEntity d " +
            "WHERE TYPE(d) = :type")
    List<DictionaryData> findAllByType(@Param("type") Class<? extends DictionaryEntity> type);

    @Query(value = "SELECT * " +
            "FROM dictionary " +
            "WHERE dtype = :type " +
            "AND id = :id", nativeQuery = true)
    Optional<DictionaryEntity> findByTypeAndId(@Param("type") String type, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE " +
            "FROM dictionary "  +
            "WHERE dtype = :type " +
            "AND id = :id", nativeQuery = true)
    void deleteByTypeAndId(@Param("type") String type, @Param("id") Long id);

}
