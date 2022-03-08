package ru.learnup.garayev.spring.opersale.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    private Long id;
    private String name; // Название сезона
    private List<RealTheatrePremier> premiers;

}
