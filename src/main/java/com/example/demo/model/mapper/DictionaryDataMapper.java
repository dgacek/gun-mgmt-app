package com.example.demo.model.mapper;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.entity.DictionaryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DictionaryDataMapper {
    DictionaryDataMapper INSTANCE = Mappers.getMapper(DictionaryDataMapper.class);

    DictionaryData toDictionaryData(DictionaryEntity dictionaryEntity);
}
