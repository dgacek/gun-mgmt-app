package com.example.demo.dto;

import com.example.demo.entity.CaliberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Caliber {
    private Long id;
    private String name;

    public Caliber(CaliberEntity caliberEntity) {
        this.id = caliberEntity.getId();
        this.name = caliberEntity.getName();
    }
}
