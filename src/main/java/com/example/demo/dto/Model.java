package com.example.demo.dto;

import com.example.demo.entity.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class Model {
    private Long id;
    private Manufacturer manufacturer;
    private String name;

    public Model(ModelEntity modelEntity) {
        this.id = modelEntity.getId();
        this.manufacturer = new Manufacturer(modelEntity.getManufacturerEntity());
        this.name = modelEntity.getName();
    }

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
