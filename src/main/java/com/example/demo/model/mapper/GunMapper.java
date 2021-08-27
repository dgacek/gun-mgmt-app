package com.example.demo.model.mapper;

import com.example.demo.model.dto.Gun;
import com.example.demo.model.entity.GunEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GunMapper {
    GunMapper INSTANCE = Mappers.getMapper(GunMapper.class);

    Gun toGun(GunEntity gunEntity);
    List<Gun> toGunList(List<GunEntity> gunEntityList);
}
