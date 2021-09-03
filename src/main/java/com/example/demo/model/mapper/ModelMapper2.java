package com.example.demo.model.mapper;

import com.example.demo.model.dto.Model;
import com.example.demo.model.entity.ModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
// ModelMapper2 because ModelMapper caused conflicts with Swagger
public interface ModelMapper2 {
    ModelMapper2 INSTANCE = Mappers.getMapper(ModelMapper2.class);

    Model toModel(ModelEntity modelEntity);
    List<Model> toModelList(List<ModelEntity> modelEntityList);
}
