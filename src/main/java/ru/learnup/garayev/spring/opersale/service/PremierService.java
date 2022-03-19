package ru.learnup.garayev.spring.opersale.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ru.learnup.garayev.spring.opersale.annotations.Loggable;
import ru.learnup.garayev.spring.opersale.module.RealTheatrePremier;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.entity.PremierEntity;
import ru.learnup.garayev.spring.opersale.repository.interfaces.PremieraRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class PremierService implements ApplicationContextAware {

    private String name;
    private SortedMap<LocalDateTime, RealTheatrePremier> theatreSeason = new TreeMap<>();
    private ApplicationContext ctx;
    private PremieraRepository premieraRepository;
    //private ListSeasonRepository seasonRepository;


    @Autowired
    public PremierService(PremieraRepository repository) {
        this.premieraRepository = repository;
    }

    public void listAllSeason() {
        //Collection<ListSeasonEntity> result = repository.getAll();
        System.out.println(premieraRepository.findAll());
    }


    public SortedMap<LocalDateTime, RealTheatrePremier> getTheatreSeason() {
        return theatreSeason;
    }

    public void setTheatreSeason(SortedMap<LocalDateTime, RealTheatrePremier> theatreSeason) {
        this.theatreSeason = theatreSeason;
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
    @Transactional
    public void addTheatreSeasonDB(String name, String memo, int ageFrom, int countPlace, LocalDateTime datePremier, ListSeasonEntity season) {
        try {
            premieraRepository.save(new PremierEntity(null, name, memo, countPlace, countPlace, datePremier, season, ageFrom));
        } catch (Exception err) {
            System.out.println(err);
        }
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
    @Transactional
    public boolean buyTicketDB(PremierEntity realTheatrePremier, int i) {
        int countFreePlace = realTheatrePremier.getCountFreePlace();
        if (countFreePlace >= i) {
            realTheatrePremier.setCountFreePlace(countFreePlace - i);
            try {
                premieraRepository.save(realTheatrePremier);
            } catch (Exception err) {
                System.out.println(err);
            }
            return true;
        }
        return false;
    }

    @Loggable
    @Transactional
    public boolean returnTicketDB(PremierEntity realTheatrePremier, int i) {
        int countFreePlace = realTheatrePremier.getCountFreePlace();
        int countPlace = realTheatrePremier.getCountPlace();
        if ((countFreePlace + i) <= countPlace) {
            realTheatrePremier.setCountFreePlace(countFreePlace + i);
            try {
                premieraRepository.save(realTheatrePremier);
            } catch (Exception err) {
                System.out.println(err);
            }
            return true;
        }
        return false;
    }

    public boolean returnTicket(RealTheatrePremier realTheatrePremier, int i) {
        if ((realTheatrePremier.getCountFreePlace() + i) <= realTheatrePremier.getCountPlace()) {
            try {
                realTheatrePremier.setCountFreePlace(realTheatrePremier.getCountFreePlace() + i);
            } catch (Exception err) {
                System.out.println(err);
            }
            return true;
        }
        return false;
    }

    public void cancelTheatreSeason(RealTheatrePremier realTheatrePremier) {
        try {
            theatreSeason.remove(realTheatrePremier.getDatePremier());
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    @Transactional
    public void cancelTheatreSeasonDB(PremierEntity realTheatrePremier) {
        try {
            premieraRepository.deleteAllById(realTheatrePremier.getId());
        } catch (Exception err) {
            System.out.println(err);
        }
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
        try {
            premieraRepository.deleteAllById(realTheatrePremier.getId());
            realTheatrePremier.setDatePremier(newDate);
            premieraRepository.save(realTheatrePremier);
        } catch (Exception err) {
            System.out.println(err);
        }
        return true;
    }

    public RealTheatrePremier getRealTheatrePremier(LocalDateTime date) {
        return theatreSeason.get(date);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public void listAllPremierDB() {
        try {
            premieraRepository.findAll().forEach(System.out::println);
        } catch (Exception err) {
            System.out.println(err);
        }

    }

    public void infoAboutPremier(LocalDateTime date) {
        RealTheatrePremier rp = theatreSeason.get(date);
        System.out.println("[" + rp.getDatePremier() + "] " +
                rp.getName() +
                " Общее кол-во мест : " + rp.getCountPlace() +
                " Свободное кол-во мест : " + rp.getCountFreePlace());
    }

    @Transactional(readOnly = true)
    public void infoAboutPremierDB(LocalDateTime date) {
        try {
            PremierEntity rp = premieraRepository.findPremierEntityByDatePremier(date);
        System.out.println("[" + rp.getDatePremier() + "] " +
                rp.getName() +
                " Общее кол-во мест : " + rp.getCountPlace() +
                " Свободное кол-во мест : " + rp.getCountFreePlace());
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

    @Transactional(readOnly = true)
    public void printAllLike(String pattern) {
        try {
            for (PremierEntity premierEntity : premieraRepository.findAllByNameLike(pattern)) {
                System.out.println(premierEntity.toString());
            }
        } catch (Exception err) {
            System.out.println(err);
        }

    }


}

