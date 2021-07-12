package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("manufacturer")
@Getter
@Setter
@NoArgsConstructor
public class ManufacturerEntity extends DictionaryEntity {
    public ManufacturerEntity(String name) {
        this.name = name;
    }
}
