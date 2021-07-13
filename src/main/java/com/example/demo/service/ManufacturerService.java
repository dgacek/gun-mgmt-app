package com.example.demo.service;

import com.example.demo.dto.DictionaryData;
import com.example.demo.dto.DictionaryDataInput;

import java.util.List;

public interface ManufacturerService {
    DictionaryData addManufacturer(DictionaryDataInput dictionaryDataInput);
    List<DictionaryData> findAllManufacturers();
    DictionaryData updateManufacturer(DictionaryData dictionaryData);
    DictionaryData findManufacturerById(Long id);
    void deleteManufacturer(Long id);
}
