package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "manufacturers")
@Getter
@Setter
@NoArgsConstructor
public class ManufacturerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;

    public ManufacturerEntity(String name) {
        this.name = name;
    }
}
