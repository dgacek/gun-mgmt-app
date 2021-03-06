package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Table(name = "dictionary")
@NoArgsConstructor
@Getter
@Setter
public class DictionaryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dictionary_seq_gen")
    @SequenceGenerator(name = "dictionary_seq_gen", sequenceName = "dictionary_id_seq", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    protected Long id;
    protected String name;
}
