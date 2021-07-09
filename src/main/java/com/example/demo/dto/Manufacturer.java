package com.example.demo.dto;

import com.example.demo.entity.ManufacturerEntity;
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

    public Manufacturer(ManufacturerEntity manufacturerEntity) {
        this.id = manufacturerEntity.getId();
        this.name = manufacturerEntity.getName();
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
