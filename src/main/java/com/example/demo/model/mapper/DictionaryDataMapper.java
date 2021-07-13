package com.example.demo.model.mapper;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.entity.DictionaryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DictionaryDataMapper {
    DictionaryData toDictionaryData(DictionaryEntity dictionaryEntity);
}
