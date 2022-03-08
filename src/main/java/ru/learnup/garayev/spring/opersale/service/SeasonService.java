package ru.learnup.garayev.spring.opersale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnup.garayev.spring.opersale.mappers.MyMapper;
import ru.learnup.garayev.spring.opersale.module.Season;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.interfaces.JpaSeasonRepository;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonService {

    private JpaSeasonRepository repository;
    private MyMapper seasonMapper;

    @Autowired
    public SeasonService(JpaSeasonRepository repository, MyMapper seasonMapper) {
        this.repository = repository;
        this.seasonMapper = seasonMapper;
    }

    public List<Season> getAll() {
        return repository.findAll().stream()
                .map(seasonMapper::toDomain)
                .collect(Collectors.toList());
    }

    public void add(Season season) {
        repository.save(new ListSeasonEntity(0L, season.getName(), new ArrayList<>()));
    }

    @Transactional
    public void update(Season season) {
        final ListSeasonEntity premiers = repository.getById(season.getId());
        repository.save(new ListSeasonEntity(season.getId(), season.getName(), premiers.getPremiers()));
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

    public Season get(long id) {
        return seasonMapper.toDomain(repository.getById(id));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
