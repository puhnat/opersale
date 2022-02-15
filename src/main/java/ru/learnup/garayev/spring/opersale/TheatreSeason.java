package ru.learnup.garayev.spring.opersale;

import org.springframework.stereotype.Component;

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

    public void cancelTheatreSeason(RealTheatrePremier realTheatrePremier) {
        theatreSeason.remove(realTheatrePremier.getDatePremier());
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
