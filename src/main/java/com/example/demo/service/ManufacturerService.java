package com.example.demo.service;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;

import java.util.List;

public interface ManufacturerService {
    DictionaryData addManufacturer(DictionaryDataInput dictionaryDataInput);
    List<DictionaryData> findAllManufacturers();
    DictionaryData updateManufacturer(DictionaryData dictionaryData);
    DictionaryData findManufacturerById(Long id);
    void deleteManufacturer(Long id);
}
