package com.example.demo.service;

import com.example.demo.dto.Type;
import com.example.demo.dto.TypeInput;

import java.util.List;

public interface TypeService {
    Type addType(TypeInput typeInput);
    List<Type> findAllTypes();
    Type updateType(Type type);
    Type findTypeById(Long id);
    void deleteType(Long id);
}
