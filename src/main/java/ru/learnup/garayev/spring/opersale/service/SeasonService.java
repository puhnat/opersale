package ru.learnup.garayev.spring.opersale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.interfaces.JpaSeasonRepository;

import java.util.List;

@Service
public class SeasonService {

    private JpaSeasonRepository repository;

    @Autowired
    public SeasonService(JpaSeasonRepository repository) {
        this.repository = repository;
    }

    public void printAll() {
        repository.findAll().forEach(System.out::println);
    }

    public List<ListSeasonEntity> getByName(String name) {
        return repository.findAllByNameLike(name);
    }

    public ListSeasonEntity saveNew(ListSeasonEntity authorEntity) {
        return repository.save(authorEntity);
    }

    public void delete(ListSeasonEntity author) {
        repository.delete(author);
    }

    public void print(long id) {
        System.out.println(repository.findById(id));
    }

}
