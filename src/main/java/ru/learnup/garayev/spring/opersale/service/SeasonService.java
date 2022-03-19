package ru.learnup.garayev.spring.opersale.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.interfaces.JpaSeasonRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@Data
@NoArgsConstructor
public class SeasonService {

    private JpaSeasonRepository repository;

    @Autowired
    public SeasonService(JpaSeasonRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public void printAll() {
        repository.findAll().forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public ListSeasonEntity getByName(String name) {
        return repository.findAllByNameSeasonLike(name);
    }

    @Transactional
    public ListSeasonEntity saveNew(ListSeasonEntity season) {
        return repository.save(season);
    }

    @Transactional
    public void delete(ListSeasonEntity season) {
        repository.delete(season);
    }

    @Transactional(readOnly = true)
    public void print(long id) {
        System.out.println(repository.findById(id));
    }

}
