package com.example.demo.service.implementation;

import com.example.demo.dto.DictionaryData;
import com.example.demo.dto.DictionaryDataInput;
import com.example.demo.entity.CaliberDictionary;
import com.example.demo.entity.DictionaryEntity;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.mapper.DictionaryDataMapper;
import com.example.demo.repo.DictionaryRepo;
import com.example.demo.service.CaliberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CaliberServiceImpl implements CaliberService {
    private final DictionaryRepo dictionaryRepo;
    private final DictionaryDataMapper dictionaryDataMapper;

    public DictionaryData addCaliber(DictionaryDataInput dictionaryDataInput) {
        return dictionaryDataMapper.entityToDTO(dictionaryRepo.save(new CaliberDictionary(dictionaryDataInput.getName())));
    }

    public List<DictionaryData> findAllCalibers() {
        return dictionaryRepo.findAllByType(CaliberDictionary.class);
    }

    @Transactional
    public DictionaryData updateCaliber(DictionaryData dictionaryData) {
        DictionaryEntity caliberDictionary = dictionaryRepo.findById(dictionaryData.getId())
                .orElseThrow(() -> new IdNotFoundException("Caliber of id:"+dictionaryData.getId()+" could not be found in the database"));
        caliberDictionary.setName(dictionaryData.getName());
        return dictionaryDataMapper.entityToDTO(caliberDictionary);
    }

    public DictionaryData findCaliberById(Long id) {
        return dictionaryDataMapper.entityToDTO(dictionaryRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Caliber of id:"+id+" could not be found in the database")));
    }

    public void deleteCaliber(Long id) {
        dictionaryRepo.deleteById(id);
    }
}
