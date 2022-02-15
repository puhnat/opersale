package ru.learnup.garayev.spring.opersale;

import org.springframework.stereotype.Component;

@Component
public class TheatrePremier {

    private String name; // Название
    private String memo; // Подробное описание
    private int ageFrom; // Возрастное ограничение
    private int countPlace; // Количество доступных мест

    public TheatrePremier() {
    }

    public TheatrePremier(String name, String memo, int ageFrom, int countPlace) {
        this.name = name;
        this.memo = memo;
        this.ageFrom = ageFrom;
        this.countPlace = countPlace;
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
