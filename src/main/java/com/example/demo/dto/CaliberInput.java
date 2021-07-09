package com.example.demo.dto;

import com.example.demo.entity.CaliberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CaliberInput {
    private String name;

    public CaliberInput(CaliberEntity caliberEntity) {
        this.name = caliberEntity.getName();
    }
}
