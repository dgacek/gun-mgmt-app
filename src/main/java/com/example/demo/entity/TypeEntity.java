package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("type")
@Getter
@Setter
@NoArgsConstructor
public class TypeEntity extends DictionaryEntity {
    public TypeEntity(String name) {
        this.name = name;
    }
}
