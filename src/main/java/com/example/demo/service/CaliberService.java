package com.example.demo.service;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;

import java.util.List;

public interface CaliberService {
    DictionaryData addCaliber(DictionaryDataInput dictionaryDataInput);
    List<DictionaryData> findAllCalibers();
    DictionaryData updateCaliber(DictionaryData dictionaryData);
    DictionaryData findCaliberById(Long id);
    void deleteCaliber(Long id);
}
