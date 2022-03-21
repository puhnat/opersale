package ru.learnup.garayev.spring.opersale.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.learnup.garayev.spring.opersale.controllers.dto.SeasonDto;
import ru.learnup.garayev.spring.opersale.mappers.MyMapper;
import ru.learnup.garayev.spring.opersale.model.Season;
import ru.learnup.garayev.spring.opersale.service.PremierService;
import ru.learnup.garayev.spring.opersale.service.SeasonService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seasons")
public class SeasonController {
    private SeasonService seasonService;
    private PremierService premierService;
    private MyMapper seasonMapper;

    @Autowired
    public SeasonController(SeasonService seasonService, PremierService premierService, MyMapper seasonMapper) {
        this.seasonService = seasonService;
        this.premierService = premierService;
        this.seasonMapper = seasonMapper;
    }

    @GetMapping
    public Collection<SeasonDto> getAll() {
        final List<Season> seasons = seasonService.getAll();
        return seasons.stream()
                .map(seasonMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SeasonDto get(@PathVariable("id") Long id) {
        final Season season = seasonService.get(id);
        final SeasonDto result = seasonMapper.toDto(season);

        return result;
    }

    @PostMapping
    public void create(@RequestBody SeasonDto season){
        seasonService.add(seasonMapper.toDomain(season));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody SeasonDto seasonDto) {
        seasonService.update(seasonMapper.toDomain(seasonDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        seasonService.deleteById(id);
    }
}
