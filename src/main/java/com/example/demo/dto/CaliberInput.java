package com.example.demo.dto;

import com.example.demo.entity.CaliberDictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CaliberInput {
    private String name;

    public CaliberInput(CaliberDictionary caliberDictionary) {
        this.name = caliberDictionary.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var caliberInput = (CaliberInput) o;
        return this.name.equals(caliberInput.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
