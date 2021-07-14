package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "guns")
public class GunEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gun_seq_gen")
    @SequenceGenerator(name = "gun_seq_gen", sequenceName = "gun_id_seq", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;

    private Long productionYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private ModelEntity model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caliber_id")
    private CaliberDictionary caliber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TypeDictionary type;
}
