package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    private String email;
    private Long phone;
    private String username;
    private String password;

    public UserEntity(RoleEntity roleEntity, String email, Long phone, String username, String password) {
        this.roleEntity = roleEntity;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }
}
