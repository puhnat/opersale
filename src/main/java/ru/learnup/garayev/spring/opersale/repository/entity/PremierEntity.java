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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "premiera_id_seq", sequenceName = "premiera_id_seq")
    private Long id;

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

    @JoinColumn(name = "id_season")
    @ManyToOne(fetch = FetchType.EAGER)
    private ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity season;

    @Column(name = "age_from")
    private int ageFrom;

    @Override
    public String toString() {
        //StringBuilder sb = new StringBuilder(String.format("%s (%d)", name, id));
        return  "[" + season.getNameSeason() + "] " +
                "[" + datePremier + "] " +
                name +
                " Общее кол-во мест : " + countPlace +
                " Свободное кол-во мест : " + countFreePlace;
        //return sb.toString();
    }
}
