package ru.learnup.garayev.spring.opersale.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.learnup.garayev.spring.opersale.controllers.dto.RealTheatrePremierDto;
import ru.learnup.garayev.spring.opersale.controllers.dto.SeasonDto;
import ru.learnup.garayev.spring.opersale.mappers.MyMapper;
import ru.learnup.garayev.spring.opersale.module.RealTheatrePremier;
import ru.learnup.garayev.spring.opersale.module.Season;
import ru.learnup.garayev.spring.opersale.service.PremierService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/premiers")
public class PremierController {
    private PremierService premierService;
    private MyMapper mapper;

    @Autowired
    public PremierController(PremierService premierService, MyMapper mapper) {
        this.premierService = premierService;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/premiers", method = RequestMethod.GET)
    public String showAllPremier(Model model) {
        final List<RealTheatrePremier> premiers = premierService.getAll();
        model.addAttribute("premiers", premiers);
        return "table";
    }

    @GetMapping
    public Collection<RealTheatrePremierDto> getAll() {
        final List<RealTheatrePremier> realTheatrePremiers = premierService.getAll();
        return realTheatrePremiers.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RealTheatrePremierDto get(@PathVariable("id") Long id) {
        final RealTheatrePremier realTheatrePremiers = premierService.get(id);
        return mapper.toDto(realTheatrePremiers);
    }

    @PostMapping
    public void create(@RequestBody RealTheatrePremierDto realTheatrePremiers){
        premierService.add(mapper.toDomain(realTheatrePremiers));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody RealTheatrePremierDto realTheatrePremierDto) {
        premierService.update(mapper.toDomain(realTheatrePremierDto));
    }

    @PutMapping("/pay/{id}")
    public void payTicket(@PathVariable("id") Long id, @RequestBody Integer count) {
        RealTheatrePremier realTheatrePremier = premierService.get(id);
        premierService.buyTicket(realTheatrePremier, count);
    }

    @PutMapping("/return/{id}")
    public void returnTicket(@PathVariable("id") Long id, @RequestBody Integer count) {
        RealTheatrePremier realTheatrePremier = premierService.get(id);
        premierService.returnTicket(realTheatrePremier, count);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        premierService.deleteById(id);
    }
}
