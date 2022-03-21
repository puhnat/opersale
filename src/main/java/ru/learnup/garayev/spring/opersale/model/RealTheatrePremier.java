package ru.learnup.garayev.spring.opersale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealTheatrePremier {

    private Long id;
    private String name; // Название
    private String memo; // Подробное описание
    private int ageFrom; // Возрастное ограничение
    private int countPlace; // Количество доступных мест
    private int countFreePlace;
    private LocalDateTime datePremier;
    private Season season;

    public RealTheatrePremier(String name, String memo, int ageFrom, int countPlace, LocalDateTime datePremier) {
        this.name = name;
        this.memo = memo;
        this.ageFrom = ageFrom;
        this.countPlace = countPlace;
        this.countFreePlace = countPlace;
        this.datePremier = datePremier;
    }
}


