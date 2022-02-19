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
        ctx.getBean(TheatreSeason.class).setName("Февраль 2022");

        for (int i = 0; i < 3; i++) {
            ctx.getBean(TheatreSeason.class).addTheatreSeason("Премьера " + i, "[" + i + "] Премьера супер спектакля", 0, 200, LocalDateTime.of(2022, Month.FEBRUARY, 1+i, 18, 0, 0));
        }

        // Все премьеры
        printAll(ctx.getBean(TheatreSeason.class), "");

        // Одна премьера
        ctx.getBean(TheatreSeason.class).infoAboutPremier(LocalDateTime.of(2022, Month.FEBRUARY, 1, 18, 0, 0));
        System.out.println();

        // Продажа билетов
        ctx.getBean(TheatreSeason.class).buyTicket(ctx.getBean(TheatreSeason.class).getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 1, 18, 0, 0)), 10);
        ctx.getBean(TheatreSeason.class).buyTicket(ctx.getBean(TheatreSeason.class).getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)), 20);
        ctx.getBean(TheatreSeason.class).buyTicket(ctx.getBean(TheatreSeason.class).getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 3, 18, 0, 0)), 50);

        // Все премьеры
        printAll(ctx.getBean(TheatreSeason.class), "После продажи билетов");

        // Возврат билетов
        ctx.getBean(TheatreSeason.class).returnTicket(ctx.getBean(TheatreSeason.class).getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)), 20);

        // Все премьеры
        printAll(ctx.getBean(TheatreSeason.class), "После возврата билетов");

        // Перенос премьеры
        ctx.getBean(TheatreSeason.class).replaceTheatreSeason(ctx.getBean(TheatreSeason.class).getRealTheatrePremier(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)), LocalDateTime.of(2022, Month.FEBRUARY, 15, 18, 0, 0));

        // Все премьеры
        printAll(ctx.getBean(TheatreSeason.class), "После переноса премьеры");

    }

    public static void printAll(TheatreSeason theatreSeason, String postFix){
        System.out.println(theatreSeason.getName() + " " + postFix);
        theatreSeason.listAllPremier();
        System.out.println();
    }

}
