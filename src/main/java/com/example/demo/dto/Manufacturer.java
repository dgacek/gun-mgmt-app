package com.example.demo.dto;

import com.example.demo.entity.ManufacturerDictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Manufacturer {
    private Long id;
    private String name;

    public Manufacturer(ManufacturerDictionary manufacturerDictionary) {
        this.id = manufacturerDictionary.getId();
        this.name = manufacturerDictionary.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var manufacturer = (Manufacturer) o;

        return this.id.equals(manufacturer.id)
                && this.name.equals(manufacturer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
