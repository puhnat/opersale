package ru.learnup.garayev.spring.opersale;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@SpringBootApplication
public class OpersaleApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(OpersaleApplication.class, args);

        TheatreSeason theatreSeason = (TheatreSeason) ctx.getBean("bbb");//("TheatreSeason");
        theatreSeason.setName("Февраль 2022");

        for (int i = 0; i < 3; i++) {
            RealTheatrePremier realTheatrePremier = (RealTheatrePremier) ctx.getBean("aaa");//("RealTheatrePremier");
            realTheatrePremier.setName("Премьера " + i);
            realTheatrePremier.setDatePremier(LocalDateTime.of(2022, Month.FEBRUARY, 1+i, 18, 0, 0));
            realTheatrePremier.setAgeFrom(0);
            realTheatrePremier.setMemo("[" + i + "] Премьера супер спектакля");
            realTheatrePremier.setCountPlace(200);
            realTheatrePremier.setCountFreePlace(realTheatrePremier.getCountPlace());
            theatreSeason.addTheatreSeason(realTheatrePremier);
        }

        // Все премьеры
        System.out.println(theatreSeason.getName());
        theatreSeason.listAllPremier();
        System.out.println();

        // Одна премьера
        theatreSeason.infoAboutPremier(LocalDateTime.of(2022, Month.FEBRUARY, 1, 18, 0, 0));
        System.out.println();

        // Продажа билетов
        theatreSeason.getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 1, 18, 0, 0)).buyTicket(10);
        theatreSeason.getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)).buyTicket(20);
        theatreSeason.getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 3, 18, 0, 0)).buyTicket(50);

        System.out.println(theatreSeason.getName());
        theatreSeason.listAllPremier();
        System.out.println();

        // Возврат билетов
        theatreSeason.getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)).returnTicket(20);

        System.out.println(theatreSeason.getName());
        theatreSeason.listAllPremier();
        System.out.println();
    }

}
