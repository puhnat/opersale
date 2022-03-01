package ru.learnup.garayev.spring.opersale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.garayev.spring.opersale.service.TheatreSeason;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootApplication
public class OpersaleApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(OpersaleApplication.class, args);

        ctx.getBean(OpersaleApplication.class);
        TheatreSeason theatreSeason = ctx.getBean(TheatreSeason.class);
        theatreSeason.setName("Февраль 2022");

        for (int i = 0; i < 3; i++) {
            theatreSeason.addTheatreSeason("Премьера " + i, "[" + i + "] Премьера супер спектакля", 0, 200, LocalDateTime.of(2022, Month.FEBRUARY, 1+i, 18, 0, 0));
        }

        // Все премьеры
        printAll(theatreSeason, "");

        // Одна премьера
        theatreSeason.infoAboutPremier(LocalDateTime.of(2022, Month.FEBRUARY, 1, 18, 0, 0));
        System.out.println();

        // Продажа билетов
        theatreSeason.buyTicket(theatreSeason.getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 1, 18, 0, 0)), 10);
        theatreSeason.buyTicket(theatreSeason.getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)), 20);
        theatreSeason.buyTicket(theatreSeason.getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 3, 18, 0, 0)), 50);

        // Все премьеры
        printAll(theatreSeason, "После продажи билетов");

        // Возврат билетов
        theatreSeason.returnTicket(theatreSeason.getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)), 20);

        // Все премьеры
        printAll(theatreSeason, "После возврата билетов");

        // Перенос премьеры
        theatreSeason.replaceTheatreSeason(theatreSeason.getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)), LocalDateTime.of(2022, Month.FEBRUARY, 15, 18, 0, 0));

        // Все премьеры
        printAll(theatreSeason, "После переноса премьеры");

    }

    public static void printAll(TheatreSeason theatreSeason, String postFix){
        System.out.println(theatreSeason.getName() + " " + postFix);
        theatreSeason.listAllPremier();
        System.out.println();
    }

}
