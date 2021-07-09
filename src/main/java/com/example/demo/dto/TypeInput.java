package com.example.demo.dto;

import com.example.demo.entity.TypeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypeInput {
    private String name;

    public TypeInput(TypeEntity typeEntity) {
        this.name = typeEntity.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var typeInput = (TypeInput) o;
        return this.name.equals(typeInput.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
