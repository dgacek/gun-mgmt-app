package com.example.demo.dto;

import com.example.demo.entity.TypeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Type {
    private Long id;
    private String name;

    public Type(TypeEntity typeEntity) {
        this.id = typeEntity.getId();
        this.name = typeEntity.getName();
    }
}
