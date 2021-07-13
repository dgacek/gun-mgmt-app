package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Model {
    private Long id;
    private DictionaryData manufacturer;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var model = (Model) o;
        return this.id.equals(model.id)
                && this.manufacturer.equals(model.manufacturer)
                && this.name.equals(model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
