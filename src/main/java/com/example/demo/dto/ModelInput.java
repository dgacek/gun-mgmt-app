package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ModelInput {
    private Long manufacturerId;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var modelInput = (ModelInput) o;
        return this.manufacturerId.equals(modelInput.manufacturerId)
                && this.name.equals(modelInput.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturerId, name);
    }
}
