package ru.learnup.garayev.spring.opersale.module;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RealTheatrePremier {

    private String name; // Название
    private String memo; // Подробное описание
    private int ageFrom; // Возрастное ограничение
    private int countPlace; // Количество доступных мест
    private int countFreePlace;
    private LocalDateTime datePremier;

    public RealTheatrePremier(String name, String memo, int ageFrom, int countPlace, LocalDateTime datePremier) {
        this.name = name;
        this.memo = memo;
        this.ageFrom = ageFrom;
        this.countPlace = countPlace;
        this.countFreePlace = countPlace;
        this.datePremier = datePremier;
    }

    public RealTheatrePremier() {
    }

    @Override
    public String toString(){
        return this.name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(int ageFrom) {
        this.ageFrom = ageFrom;
    }

    public int getCountPlace() {
        return countPlace;
    }

    public void setCountPlace(int countPlace) {
        this.countPlace = countPlace;
    }
}
