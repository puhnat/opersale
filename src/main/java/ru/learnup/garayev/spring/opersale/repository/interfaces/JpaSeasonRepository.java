package ru.learnup.garayev.spring.opersale.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaSeasonRepository extends JpaRepository<ListSeasonEntity, Long> {

    void deleteAllById(Long Id);

    ListSeasonEntity findPremierEntityByDatePremier(LocalDateTime datePremier);

    List<ListSeasonEntity> findAllByNameLike(String seasonPattern);
}