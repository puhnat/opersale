package ru.learnup.garayev.spring.opersale.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ru.learnup.garayev.spring.opersale.annotations.Loggable;
import ru.learnup.garayev.spring.opersale.events.BuyTicketEvent;
import ru.learnup.garayev.spring.opersale.module.RealTheatrePremier;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class TheatreSeason implements ApplicationContextAware {

    private String name;
    private SortedMap<LocalDateTime, RealTheatrePremier> theatreSeason = new TreeMap<>();
    private ApplicationContext ctx;

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
    public boolean buyTicket(RealTheatrePremier realTheatrePremier, int i) {
        if (realTheatrePremier.getCountFreePlace() >= i) {
            realTheatrePremier.setCountFreePlace(realTheatrePremier.getCountFreePlace() - i);
            //ctx.publishEvent(new BuyTicketEvent("Продажа билетов. Количество : " + i)); // отключили от spring boot
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

    public RealTheatrePremier getRealTheatrePremier(LocalDateTime date) {
        return theatreSeason.get(date);
    }

    public void listAllPremier() {
        Set<LocalDateTime> keys = theatreSeason.keySet();
        for (LocalDateTime key : keys) {
            infoAboutPremier(key);
        }
    }

    public void infoAboutPremier(LocalDateTime date) {
        RealTheatrePremier rp = theatreSeason.get(date);
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

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }
}
