package ru.learnup.garayev.spring.opersale.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.learnup.garayev.spring.opersale.repository.entity.PremierEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeasonDto {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String name; // Название сезона
}
