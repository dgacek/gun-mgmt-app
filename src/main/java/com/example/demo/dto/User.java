package com.example.demo.dto;

import com.example.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class User {
    private Long id;
    private Role role;
    private String email;
    private Long phone;
    private String username;

    public User(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.role = new Role(userEntity.getRole().getId());
        this.email = userEntity.getEmail();
        this.phone = userEntity.getPhone();
        this.username = userEntity.getUsername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var user = (User) o;
        return this.id.equals(user.id)
                && this.role.equals(user.role)
                && this.email.equals(user.email)
                && this.phone.equals(user.phone)
                && this.username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phone, username);
    }
}
