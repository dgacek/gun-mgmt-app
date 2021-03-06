package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("type")
@Getter
@Setter
@NoArgsConstructor
public class TypeDictionary extends DictionaryEntity {
    /**
     * Used by Reflection in DictionaryService
     */
    public TypeDictionary(String name) {
        this.name = name;
    }
}
