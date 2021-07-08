package com.example.demo.dto;

import com.example.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Setter
public class UserInput {
    private Long id;
    private Long roleId;
    private String email;
    private Long phone;
    private String username;
    private String password;

    public UserInput(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.roleId = userEntity.getRoleEntity().getId();
        this.email = userEntity.getEmail();
        this.phone = userEntity.getPhone();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        UserInput user = (UserInput) o;
        return this.id.equals(user.id)
                && this.roleId.equals(user.roleId)
                && this.email.equals(user.email)
                && this.phone.equals(user.phone)
                && this.username.equals(user.username)
                && this.password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phone, username);
    }
}
