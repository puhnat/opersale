package ru.learnup.garayev.spring.opersale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
//@Scope("prototype")
public class RealTheatrePremier extends TheatrePremier {

    private int countFreePlace;
    private LocalDateTime datePremier;

    public RealTheatrePremier() {
        super();
    }

    public RealTheatrePremier(String name, String memo, int ageFrom, int countPlace) {
        super(name, memo, ageFrom, countPlace);
        this.countFreePlace = countPlace;
    }

    public RealTheatrePremier(String name, String memo, int ageFrom, int countPlace, LocalDateTime datePremier) {
        super(name, memo, ageFrom, countPlace);
        this.countFreePlace = countPlace;
        this.datePremier = datePremier;
    }

    public int getCountFreePlace() {
        return countFreePlace;
    }

    public void setCountFreePlace(int countFreePlace) {
        this.countFreePlace = countFreePlace;
    }

    public LocalDateTime getDatePremier() {
        return datePremier;
    }

    public void setDatePremier(LocalDateTime datePremier) {
        this.datePremier = datePremier;
    }

    public boolean buyTicket(int i){
        if (this.countFreePlace >= i){
            this.countFreePlace -= i;
            return true;
        }
        return false;
    }

    public boolean returnTicket(int i){
        if ((this.countFreePlace + i) <= getCountPlace()){
            this.countFreePlace += i;
            return true;
        }
        return true;
    }
}
