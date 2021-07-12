package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleInput {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var roleInput = (RoleInput) o;
        return this.name.equals(roleInput.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
