package com.example.demo.dto;

import com.example.demo.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role {
    private Long id;
    private String name;

    public Role(RoleEntity roleEntity) {
        this.id = roleEntity.getId();
        this.name = roleEntity.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var role = (Role) o;
        return this.id.equals(role.id)
                && this.name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
