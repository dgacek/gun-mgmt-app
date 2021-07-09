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
public class ManufacturerInput {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var manufacturerInput = (ManufacturerInput) o;
        return this.name.equals(manufacturerInput.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
