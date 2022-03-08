package ru.learnup.garayev.spring.opersale.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "list_season")
public class ListSeasonEntity {

    @Id
    @Column(name = "id_season")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "list_season_id_seq", sequenceName = "list_season_id_seq")
    private Long id;

    @Column(name = "name_season")
    private String nameSeason;

    @OneToMany(mappedBy = "season", fetch = FetchType.EAGER)
    private Collection<PremierEntity> premiers;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%s (%d)\n%s", nameSeason, id, premiers));
        return sb.toString();
    }
}
