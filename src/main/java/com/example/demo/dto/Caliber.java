package com.example.demo.dto;

import com.example.demo.entity.CaliberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Caliber {
    private Long id;
    private String name;

    public Caliber(CaliberEntity caliberEntity) {
        this.id = caliberEntity.getId();
        this.name = caliberEntity.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var caliber = (Caliber) o;

        return this.id.equals(caliber.id)
                && this.name.equals(caliber.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
