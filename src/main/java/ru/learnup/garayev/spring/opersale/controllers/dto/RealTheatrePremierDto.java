package ru.learnup.garayev.spring.opersale.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.learnup.garayev.spring.opersale.model.Season;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealTheatrePremierDto {

    @JsonProperty
    private Long id; // Название
    @JsonProperty
    private String name; // Название
    @JsonProperty
    private String memo; // Подробное описание
    @JsonProperty
    private int ageFrom; // Возрастное ограничение
    @JsonProperty
    private int countPlace; // Количество доступных мест
    @JsonProperty
    private int countFreePlace;
    @JsonProperty
    private LocalDateTime datePremier;
    @JsonProperty
    private Season season;
}


