package ru.learnup.garayev.spring.opersale.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.entity.SeasonEntity;

import java.util.Collection;

@Repository
public interface SeasonRepository {

    Collection<ListSeasonEntity> getAll();
    void saveSeason(ListSeasonEntity season);
    void deleteSeason(long id);
}
