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
public class Type {
    private Long id;
    private String name;

    public Type(TypeEntity typeEntity) {
        this.id = typeEntity.getId();
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
        var type = (Type) o;
        return this.id.equals(type.id)
                && this.name.equals(type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
