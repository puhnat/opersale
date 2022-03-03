package ru.learnup.garayev.spring.opersale.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "list_season")
public class ListSeasonEntity {

    @Id
    //@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "list_season_id_seq", sequenceName = "list_season_id_seq")
    private long id;

    @Column(name = "name_season")
    private String nameSeason;
}
