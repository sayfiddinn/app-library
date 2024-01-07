package com.example.demo.entity;

import com.example.demo.entity.base.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book extends AbsEntity {
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private Integer count;
    @Column(nullable = false)
    private Double price;
    @JsonIgnore
    @ManyToOne
    private Library library;
}
