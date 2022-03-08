package ru.learnup.garayev.spring.opersale.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.interfaces.JpaSeasonRepository;

import java.util.List;

@Service
@Data
@NoArgsConstructor
public class SeasonService {

    private JpaSeasonRepository repository;

    @Autowired
    public SeasonService(JpaSeasonRepository repository) {
        this.repository = repository;
    }

    public void printAll() {
        repository.findAll().forEach(System.out::println);
    }

    public ListSeasonEntity getByName(String name) {
        return repository.findAllByNameSeasonLike(name);
    }

    public ListSeasonEntity saveNew(ListSeasonEntity season) {
        return repository.save(season);
    }

    public void delete(ListSeasonEntity season) {
        repository.delete(season);
    }

    public void print(long id) {
        System.out.println(repository.findById(id));
    }

}
