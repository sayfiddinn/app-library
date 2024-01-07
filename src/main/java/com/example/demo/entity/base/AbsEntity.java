package com.example.demo.entity.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class AbsEntity extends AbsAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

}
