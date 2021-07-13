package com.example.demo.service.implementation;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;
import com.example.demo.model.entity.DictionaryEntity;
import com.example.demo.model.entity.ManufacturerDictionary;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.mapper.DictionaryDataMapper;
import com.example.demo.model.repo.DictionaryRepo;
import com.example.demo.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private final DictionaryRepo dictionaryRepo;
    private final DictionaryDataMapper dictionaryDataMapper;

    public DictionaryData addManufacturer(DictionaryDataInput dictionaryDataInput) {
        return dictionaryDataMapper.toDictionaryData(dictionaryRepo.save(new ManufacturerDictionary(dictionaryDataInput.getName())));
    }

    public List<DictionaryData> findAllManufacturers() {
       return dictionaryRepo.findAllByType(ManufacturerDictionary.class);
    }

    @Transactional
    public DictionaryData updateManufacturer(DictionaryData dictionaryData) {
        DictionaryEntity manufacturerDictionary = dictionaryRepo.findById(dictionaryData.getId())
                .orElseThrow(() -> new IdNotFoundException("Manufacturer of id:"+ dictionaryData.getId()+" could not be found in the database"));
        manufacturerDictionary.setName(dictionaryData.getName());
        return dictionaryDataMapper.toDictionaryData(manufacturerDictionary);
    }

    public DictionaryData findManufacturerById(Long id) {
        return dictionaryDataMapper.toDictionaryData(dictionaryRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Manufacturer of id:" + id + " could not be found in the database")));
    }

    public void deleteManufacturer(Long id) {
        dictionaryRepo.deleteById(id);
    }
}
