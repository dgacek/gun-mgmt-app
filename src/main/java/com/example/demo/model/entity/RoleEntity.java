package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_gen")
    @SequenceGenerator(name = "role_seq_gen", sequenceName = "role_id_seq", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;

}
