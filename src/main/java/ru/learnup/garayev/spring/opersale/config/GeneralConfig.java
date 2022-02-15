package ru.learnup.garayev.spring.opersale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.learnup.garayev.spring.opersale.RealTheatrePremier;
import ru.learnup.garayev.spring.opersale.TheatreSeason;

@Configuration
public class GeneralConfig {

    @Bean("aaa")
    @Scope("prototype")
    public RealTheatrePremier realTheatrePremier(){
        return new RealTheatrePremier();
    }

    @Bean("bbb")
    public TheatreSeason theatreSeason(){
        return new TheatreSeason();
    }
}
