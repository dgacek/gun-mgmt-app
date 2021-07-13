package com.example.demo.mapper;

import com.example.demo.dto.DictionaryData;
import com.example.demo.entity.DictionaryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DictionaryDataMapper {
    DictionaryData toDictionaryData(DictionaryEntity dictionaryEntity);
}
