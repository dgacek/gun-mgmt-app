package com.example.demo.service;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;

import java.util.List;

public interface TypeService {
    DictionaryData addType(DictionaryDataInput dictionaryDataInput);
    List<DictionaryData> findAllTypes();
    DictionaryData updateType(DictionaryData dictionaryData);
    DictionaryData findTypeById(Long id);
    void deleteType(Long id);
}
