package ru.learnup.garayev.spring.opersale.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.entity.PremierEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PremieraRepository extends JpaRepository<PremierEntity, Long> {
    void deleteAllById(Long Id);

    PremierEntity findPremierEntityByDatePremier(LocalDateTime datePremier);

    List<PremierEntity> findAllByNameLike(String premierPattern);

    List<PremierEntity> getAllBySeason(ListSeasonEntity season);

    List<PremierEntity> getAllBySeason_Id(Long id);

}
