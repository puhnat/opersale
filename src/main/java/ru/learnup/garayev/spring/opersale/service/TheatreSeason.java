package ru.learnup.garayev.spring.opersale.service;

import org.springframework.stereotype.Component;
import ru.learnup.garayev.spring.opersale.module.RealTheatrePremier;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class TheatreSeason {

    private String name;
    private SortedMap<LocalDateTime, RealTheatrePremier> theatreSeason = new TreeMap<>();

    public TheatreSeason() {
        this.name = "default";
    }

    public TheatreSeason(String name) {
        this.name = name;
    }

    public SortedMap<LocalDateTime, RealTheatrePremier> getTheatreSeason() {
        return theatreSeason;
    }

    public void setTheatreSeason(SortedMap<LocalDateTime, RealTheatrePremier> theatreSeason) {
        this.theatreSeason = theatreSeason;
    }

    public void addTheatreSeason(RealTheatrePremier realTheatrePremier) {
        theatreSeason.put(realTheatrePremier.getDatePremier(), realTheatrePremier);
    }

    public void addTheatreSeason(String name, String memo, int ageFrom, int countPlace, LocalDateTime datePremier) {
        theatreSeason.put(datePremier, new RealTheatrePremier(name, memo, ageFrom, countPlace, datePremier));
    }

    public boolean buyTicket(RealTheatrePremier realTheatrePremier, int i){
        if (realTheatrePremier.getCountFreePlace() >= i){
            realTheatrePremier.setCountFreePlace(realTheatrePremier.getCountFreePlace() - i);
            return true;
        }
        return false;
    }

    public boolean returnTicket(RealTheatrePremier realTheatrePremier, int i){
        if ((realTheatrePremier.getCountFreePlace() + i) <= realTheatrePremier.getCountPlace()){
            realTheatrePremier.setCountFreePlace(realTheatrePremier.getCountFreePlace() + i);
            return true;
        }
        return false;
    }

    public void cancelTheatreSeason(RealTheatrePremier realTheatrePremier) {
        theatreSeason.remove(realTheatrePremier.getDatePremier());
    }

    public boolean replaceTheatreSeason(RealTheatrePremier realTheatrePremier, LocalDateTime newDate) {
        if (theatreSeason.containsKey(newDate)) {
            return false;
        }
        theatreSeason.remove(realTheatrePremier.getDatePremier());
        realTheatrePremier.setDatePremier(newDate);
        theatreSeason.put(realTheatrePremier.getDatePremier(), realTheatrePremier);
        return true;
    }

    public RealTheatrePremier getRealTheatrePremier(LocalDateTime date){
        return theatreSeason.get(date);
    }

    public void listAllPremier() {
        Set<LocalDateTime> keys = theatreSeason.keySet();
        for (LocalDateTime key: keys)
        {
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
}
