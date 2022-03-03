package ru.learnup.garayev.spring.opersale.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "premiera")
public class PremierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "premiera_id_seq", sequenceName = "premiera_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "memo")
    private String memo;

    @Column(name = "count_place")
    private int countPlace;

    @Column(name = "count_free_place")
    private int countFreePlace;

    @Column(name = "date_premier")
    private LocalDateTime datePremier;
}
