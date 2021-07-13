package com.example.demo.service.implementation;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;
import com.example.demo.model.entity.DictionaryEntity;
import com.example.demo.model.entity.TypeDictionary;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.mapper.DictionaryDataMapper;
import com.example.demo.model.repo.DictionaryRepo;
import com.example.demo.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final DictionaryRepo dictionaryRepo;
    private final DictionaryDataMapper dictionaryDataMapper;

    public DictionaryData addType(DictionaryDataInput dictionaryDataInput) {
        return dictionaryDataMapper.toDictionaryData(dictionaryRepo.save(new TypeDictionary(dictionaryDataInput.getName())));
    }

    public List<DictionaryData> findAllTypes() {
        return dictionaryRepo.findAllByType(TypeDictionary.class);
    }

    @Transactional
    public DictionaryData updateType(DictionaryData dictionaryData) {
        DictionaryEntity typeDictionary = dictionaryRepo.findById(dictionaryData.getId())
                .orElseThrow(() -> new IdNotFoundException("Type of id:"+ dictionaryData.getId()+" could not be found in the database"));
        typeDictionary.setName(dictionaryData.getName());
        return dictionaryDataMapper.toDictionaryData(typeDictionary);
    }

    public DictionaryData findTypeById(Long id) {
        return dictionaryDataMapper.toDictionaryData(dictionaryRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Type of id:" + id + " could not be found in the database")));
    }

    public void deleteType(Long id) {
        dictionaryRepo.deleteById(id);
    }
}
