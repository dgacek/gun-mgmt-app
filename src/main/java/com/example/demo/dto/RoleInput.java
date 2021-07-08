package com.example.demo.dto;

import com.example.demo.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RoleInput {
    private String name;

    public RoleInput(RoleEntity roleEntity) {
        this.name = roleEntity.getName();
    }
}