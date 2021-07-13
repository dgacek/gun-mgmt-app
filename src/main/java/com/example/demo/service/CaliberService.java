package com.example.demo.service;

import com.example.demo.dto.DictionaryData;
import com.example.demo.dto.DictionaryDataInput;

import java.util.List;

public interface CaliberService {
    DictionaryData addCaliber(DictionaryDataInput dictionaryDataInput);
    List<DictionaryData> findAllCalibers();
    DictionaryData updateCaliber(DictionaryData dictionaryData);
    DictionaryData findCaliberById(Long id);
    void deleteCaliber(Long id);
}
