package com.example.demo.entity;

import com.example.demo.entity.base.AbsIntEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Library extends AbsIntEntity {
    @Column(nullable = false)
    private Integer floorCount;
    @Column(nullable = false)
    private Integer caseCount;
    @Column(nullable = false)
    private Integer shelfCount;
    @Column(nullable = false)
    private Integer bookCount;
    @Column(nullable = false)
    private Integer maxCount;


    {
        floorCount = 4;
        caseCount = (floorCount * 20);
        shelfCount = (caseCount * 10);
        maxCount = (shelfCount * 20);
        bookCount = 0;
    }
}
