package ru.learnup.garayev.spring.opersale.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;

@Component
public interface ListSeasonRepository extends JpaRepository<ListSeasonEntity, Long> {

    void deleteAllById(Long id);
}
