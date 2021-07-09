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
public class TypeInput {
    private String name;

    public TypeInput(TypeEntity typeEntity) {
        this.name = typeEntity.getName();
    }
}
