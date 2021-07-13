package com.example.demo.mapper;

import com.example.demo.dto.User;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "roleEntity", target = "role")
    User toUser(UserEntity userEntity);
    @Mapping(source = "roleEntity", target = "role")
    List<User> toUserList(List<UserEntity> userEntityList);
}
