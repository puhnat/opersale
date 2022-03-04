package ru.learnup.garayev.spring.opersale.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Season {


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class RealTheatrePremier {

        private Long id;
        private String name; // Название сезона
    }


}
