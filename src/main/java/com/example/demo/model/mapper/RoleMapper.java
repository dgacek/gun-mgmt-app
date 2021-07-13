package com.example.demo.model.mapper;

import com.example.demo.model.dto.Role;
import com.example.demo.model.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleEntity roleEntity);
    List<Role> toRoleList(List<RoleEntity> roleEntityList);
}
