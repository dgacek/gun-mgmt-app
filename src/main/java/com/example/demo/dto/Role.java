package com.example.demo.dto;

import com.example.demo.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Role {
    private Long id;
    private String name;

    public Role(RoleEntity roleEntity) {
        this.id = roleEntity.getId();
        this.name = roleEntity.getName();
    }
}
