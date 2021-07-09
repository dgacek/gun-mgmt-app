package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "models")
@Getter
@Setter
@NoArgsConstructor
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private ManufacturerEntity manufacturerEntity;

    private String name;

    public ModelEntity(ManufacturerEntity manufacturerEntity, String name) {
        this.manufacturerEntity = manufacturerEntity;
        this.name = name;
    }
}
