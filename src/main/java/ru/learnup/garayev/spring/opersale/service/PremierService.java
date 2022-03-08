package ru.learnup.garayev.spring.opersale.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ru.learnup.garayev.spring.opersale.annotations.Loggable;
import ru.learnup.garayev.spring.opersale.mappers.MyMapper;
import ru.learnup.garayev.spring.opersale.module.RealTheatrePremier;
import ru.learnup.garayev.spring.opersale.module.Season;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.entity.PremierEntity;
import ru.learnup.garayev.spring.opersale.repository.interfaces.JpaSeasonRepository;
import ru.learnup.garayev.spring.opersale.repository.interfaces.PremieraRepository;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PremierService {

    private String name;
    private SortedMap<LocalDateTime, RealTheatrePremier> theatreSeason = new TreeMap<>();
    private PremieraRepository premieraRepository;
    private JpaSeasonRepository seasonRepository;
    private MyMapper mapper;


    @Autowired
    public PremierService(PremieraRepository repository, JpaSeasonRepository seasonRepository, MyMapper mapper) {
        this.premieraRepository = repository;
        this.seasonRepository = seasonRepository;
        this.mapper = mapper;
    }

    public SortedMap<LocalDateTime, RealTheatrePremier> getTheatreSeason() {
        return theatreSeason;
    }

    public void setTheatreSeason(SortedMap<LocalDateTime, RealTheatrePremier> theatreSeason) {
        this.theatreSeason = theatreSeason;
    }

    public List<RealTheatrePremier> getAll() {
        return premieraRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public void add(RealTheatrePremier premier) {
        premieraRepository.save(new PremierEntity(0L, premier.getName(), premier.getMemo(), premier.getCountPlace(),
                premier.getCountFreePlace(), premier.getDatePremier(), new ListSeasonEntity(), premier.getAgeFrom()));
    }

    @Transactional
    public void update(RealTheatrePremier premier) {
        final PremierEntity premiersEntity = premieraRepository.getById(premier.getId());
        premieraRepository.save(new PremierEntity(premiersEntity.getId(), premier.getName(), premier.getMemo(), premier.getCountPlace(),
                premier.getCountFreePlace(), premier.getDatePremier(),
                new ListSeasonEntity(premier.getSeason().getId(), premier.getSeason().getName(), new ArrayList<>()),
                premier.getAgeFrom()));
    }

    public RealTheatrePremier get(Long id) {
        return mapper.toDomain(premieraRepository.getById(id));
    }

    @Loggable
    public void addTheatreSeason(RealTheatrePremier realTheatrePremier) {
        if (!theatreSeason.containsKey(realTheatrePremier.getDatePremier())) {
            theatreSeason.put(realTheatrePremier.getDatePremier(), realTheatrePremier);
            //ctx.publishEvent(new BuyTicketEvent("Новая премьера. " + realTheatrePremier.getName())); // отключили от spring boot
        }
    }

    @Loggable
    public void addTheatreSeason(String name, String memo, int ageFrom, int countPlace, LocalDateTime datePremier) {
        if (!theatreSeason.containsKey(datePremier)) {
            theatreSeason.put(datePremier, new RealTheatrePremier(name, memo, ageFrom, countPlace, datePremier));
            //ctx.publishEvent(new BuyTicketEvent("Новая премьера. " + name)); // отключили от spring boot
        }
    }

    @Loggable
    public void addTheatreSeasonDB(String name, String memo, int ageFrom, int countPlace, LocalDateTime datePremier, ListSeasonEntity season) {
        premieraRepository.save(new PremierEntity(null, name, memo, countPlace, countPlace, datePremier, season, ageFrom));
    }

    @Loggable
    public boolean buyTicket(RealTheatrePremier realTheatrePremier, int i) {
        if (realTheatrePremier.getCountFreePlace() >= i) {
            realTheatrePremier.setCountFreePlace(realTheatrePremier.getCountFreePlace() - i);
            //ctx.publishEvent(new BuyTicketEvent("Продажа билетов. Количество : " + i)); // отключили от spring boot
            return true;
        }
        return false;
    }

    @Loggable
    public boolean buyTicketDB(PremierEntity realTheatrePremier, int i) {
        int countFreePlace = realTheatrePremier.getCountFreePlace();
        if (countFreePlace >= i) {
            realTheatrePremier.setCountFreePlace(countFreePlace - i);
            premieraRepository.save(realTheatrePremier);
            return true;
        }
        return false;
    }

    @Loggable
    public boolean returnTicketDB(PremierEntity realTheatrePremier, int i) {
        int countFreePlace = realTheatrePremier.getCountFreePlace();
        int countPlace = realTheatrePremier.getCountPlace();
        if ((countFreePlace + i) <= countPlace) {
            realTheatrePremier.setCountFreePlace(countFreePlace + i);
            premieraRepository.save(realTheatrePremier);
            return true;
        }
        return false;
    }

    public boolean returnTicket(RealTheatrePremier realTheatrePremier, int i) {
        if ((realTheatrePremier.getCountFreePlace() + i) <= realTheatrePremier.getCountPlace()) {
            realTheatrePremier.setCountFreePlace(realTheatrePremier.getCountFreePlace() + i);
            return true;
        }
        return false;
    }

    public void cancelTheatreSeason(RealTheatrePremier realTheatrePremier) {
        theatreSeason.remove(realTheatrePremier.getDatePremier());
    }

    public void cancelTheatreSeasonDB(PremierEntity realTheatrePremier) {
        premieraRepository.deleteAllById(realTheatrePremier.getId());
    }

    @Loggable
    public boolean replaceTheatreSeason(RealTheatrePremier realTheatrePremier, LocalDateTime newDate) {
        if (theatreSeason.containsKey(newDate)) {
            //ctx.publishEvent(new BuyTicketEvent("Премьера [ " + realTheatrePremier.getName() + "] не перенесена . Новая дата занята")); // отключили от spring boot
            return false;
        }
        theatreSeason.remove(realTheatrePremier.getDatePremier());
        realTheatrePremier.setDatePremier(newDate);
        theatreSeason.put(realTheatrePremier.getDatePremier(), realTheatrePremier);
        //ctx.publishEvent(new BuyTicketEvent("Премьера [ " + realTheatrePremier.getName() + "] перенесена . Новая дата " + realTheatrePremier.getDatePremier().toString())); // отключили от spring boot
        return true;
    }

    @Loggable
    @Transactional
    public boolean replaceTheatreSeasonDB(PremierEntity realTheatrePremier, LocalDateTime newDate) {
        premieraRepository.deleteAllById(realTheatrePremier.getId());
        realTheatrePremier.setDatePremier(newDate);
        premieraRepository.save(realTheatrePremier);
        return true;
    }

    public RealTheatrePremier getRealTheatrePremier(LocalDateTime date) {
        return theatreSeason.get(date);
    }

    public PremierEntity getRealTheatrePremierDB(LocalDateTime date) {
        return premieraRepository.findPremierEntityByDatePremier(date);
    }

    public void listAllPremier() {
        Set<LocalDateTime> keys = theatreSeason.keySet();
        for (LocalDateTime key : keys) {
            infoAboutPremier(key);
        }
    }

    public void clearStart() {
        premieraRepository.deleteAll();
    }

    @Transactional
    public void listAllPremierDB() {
        premieraRepository.findAll().forEach(System.out::println);

    }

    public void infoAboutPremier(LocalDateTime date) {
        RealTheatrePremier rp = theatreSeason.get(date);
        System.out.println("[" + rp.getDatePremier() + "] " +
                rp.getName() +
                " Общее кол-во мест : " + rp.getCountPlace() +
                " Свободное кол-во мест : " + rp.getCountFreePlace());
    }

    public void infoAboutPremierDB(LocalDateTime date) {
        PremierEntity rp = premieraRepository.findPremierEntityByDatePremier(date);
        System.out.println("[" + rp.getDatePremier() + "] " +
                rp.getName() +
                " Общее кол-во мест : " + rp.getCountPlace() +
                " Свободное кол-во мест : " + rp.getCountFreePlace());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printAllLike(String pattern) {
        for (PremierEntity premierEntity : premieraRepository.findAllByNameLike(pattern)) {
            System.out.println(premierEntity.toString());
        }
    }

    public List<RealTheatrePremier> listAllPremiersBySeason(Season season) {
        return premieraRepository.getAllBySeason_Id(season.getId()).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        premieraRepository.deleteById(id);
    }
}

