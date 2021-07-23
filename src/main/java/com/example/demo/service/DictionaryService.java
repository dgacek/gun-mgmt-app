package com.example.demo.service;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;
import com.example.demo.model.entity.DictionaryEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface DictionaryService {
    DictionaryData addDictionary(Class<? extends DictionaryEntity> type, DictionaryDataInput dictionaryDataInput) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    List<DictionaryData> findAllDictionaries(Class<? extends DictionaryEntity> type);
    DictionaryData updateDictionary(String type, DictionaryData dictionaryData);
    DictionaryData findDictionaryById(String type, Long id);
    void deleteDictionary(String type, Long id);
}
