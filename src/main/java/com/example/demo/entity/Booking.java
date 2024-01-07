package com.example.demo.entity;

import com.example.demo.entity.base.AbsAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Booking extends AbsAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
    private LocalDate bronDate;
    private LocalDate deadlineDate;
    private Integer count;
    {
        bronDate =LocalDate.now();
        deadlineDate=bronDate.plusDays(3);
    }

    private boolean isSuccess;

}
