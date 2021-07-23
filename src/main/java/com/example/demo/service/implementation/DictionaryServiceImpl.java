package com.example.demo.service.implementation;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;
import com.example.demo.model.entity.DictionaryEntity;
import com.example.demo.model.mapper.DictionaryDataMapper;
import com.example.demo.model.repo.DictionaryRepo;
import com.example.demo.service.DictionaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryRepo dictionaryRepo;
    private final DictionaryDataMapper dictionaryDataMapper;

    public DictionaryData addDictionary(Class<? extends DictionaryEntity> type, DictionaryDataInput dictionaryDataInput) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return dictionaryDataMapper
                .toDictionaryData(dictionaryRepo
                        .save(type
                                .getDeclaredConstructor(String.class)
                                .newInstance(dictionaryDataInput.getName())));
    }

    public List<DictionaryData> findAllDictionaries(Class<? extends DictionaryEntity> type) {
        return dictionaryRepo.findAllByType(type);
    }

    @Transactional
    public DictionaryData updateDictionary(String type, DictionaryData dictionaryData) {
        DictionaryEntity dictionaryEntity = dictionaryRepo.findByTypeAndId(type, dictionaryData.getId())
                .orElseThrow(() -> new NoSuchElementException(type + "of id:"+ dictionaryData.getId() + "could not be found in the database"));
        dictionaryEntity.setName(dictionaryData.getName());
        return dictionaryDataMapper.toDictionaryData(dictionaryEntity);
    }

    public DictionaryData findDictionaryById(String type, Long id) {
        return dictionaryDataMapper.toDictionaryData(dictionaryRepo.findByTypeAndId(type, id)
                .orElseThrow(() -> new NoSuchElementException(type + "of id:"+ id + "could not be found in the database")));
    }

    public void deleteDictionary(String type, Long id) {
        dictionaryRepo.deleteByTypeAndId(type, id);
    }
}
