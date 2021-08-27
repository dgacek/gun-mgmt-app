package com.example.demo.model.entity;

import com.example.demo.security.Permission;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_gen")
    @SequenceGenerator(name = "role_seq_gen", sequenceName = "role_id_seq", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;

    @ElementCollection(targetClass = Permission.class)
    @Enumerated(EnumType.STRING)
    private Collection<Permission> permissions;

}
