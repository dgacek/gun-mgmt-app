package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("caliber")
@Getter
@Setter
@NoArgsConstructor
public class CaliberDictionary extends DictionaryEntity {
    /**
     * Used by Reflection in DictionaryService
     */
    public CaliberDictionary(String name) {
        this.name = name;
    }
}
