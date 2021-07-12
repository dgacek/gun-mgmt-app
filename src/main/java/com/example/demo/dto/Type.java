package com.example.demo.dto;

import com.example.demo.entity.TypeDictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Type {
    private Long id;
    private String name;

    public Type(TypeDictionary typeDictionary) {
        this.id = typeDictionary.getId();
        this.name = typeDictionary.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var type = (Type) o;
        return this.id.equals(type.id)
                && this.name.equals(type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
