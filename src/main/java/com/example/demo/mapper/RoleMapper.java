package com.example.demo.mapper;

import com.example.demo.dto.Role;
import com.example.demo.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleEntity roleEntity);
    List<Role> toRoleList(List<RoleEntity> roleEntityList);
}
