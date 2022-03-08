package ru.learnup.garayev.spring.opersale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.service.PremierService;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootApplication
public class OpersaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpersaleApplication.class, args);


//        final ConfigurableApplicationContext ctx = SpringApplication.run(OpersaleApplication.class, args);
//
//        ctx.getBean(OpersaleApplication.class);
//        PremierService theatreSeason = ctx.getBean(PremierService.class);
//        theatreSeason.setName("Февраль 2022");
//
//
//        // Инициализация данных в таблицах
//        theatreSeason.clearStart();
//
//        // Код не на базе данных
//        for (int i = 0; i < 3; i++) {
//            theatreSeason.addTheatreSeasonDB("Премьера " + i, "[" + i + "] Премьера супер спектакля", 0 + i * 10, 200, LocalDateTime.of(2022, Month.FEBRUARY, 1 + i, 18, 0, 0), new ListSeasonEntity(1L, "Февраль 2022", null));
//        }
//
//        // Все премьеры
//        printAll(theatreSeason, "");
//
//        // Одна премьера
//        theatreSeason.infoAboutPremierDB(LocalDateTime.of(2022, Month.FEBRUARY, 1, 18, 0, 0));
//        System.out.println();
//
//        // Продажа билетов
//        theatreSeason.buyTicketDB(theatreSeason.getRealTheatrePremierDB(LocalDateTime.of(2022, Month.FEBRUARY, 1, 18, 0, 0)), 10);
//        theatreSeason.buyTicketDB(theatreSeason.getRealTheatrePremierDB(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)), 20);
//        theatreSeason.buyTicketDB(theatreSeason.getRealTheatrePremierDB(LocalDateTime.of(2022, Month.FEBRUARY, 3, 18, 0, 0)), 50);
//
//        // Все премьеры
//        printAll(theatreSeason, "После продажи билетов");
//
//        // Возврат билетов
//        theatreSeason.returnTicketDB(theatreSeason.getRealTheatrePremierDB(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)), 20);
//
//        // Все премьеры
//        printAll(theatreSeason, "После возврата билетов");
//
//        // Перенос премьеры
//        theatreSeason.replaceTheatreSeasonDB(theatreSeason.getRealTheatrePremierDB(LocalDateTime.of(2022, Month.FEBRUARY, 2, 18, 0, 0)), LocalDateTime.of(2022, Month.FEBRUARY, 15, 18, 0, 0));
//
//        // Все премьеры
//        printAll(theatreSeason, "После переноса премьеры");
//
//        System.out.println("Результаты отбора :");
//        theatreSeason.printAllLike("%0%");
    }

    public static void printAll(PremierService theatreSeason, String postFix) {
        System.out.println(theatreSeason.getName() + " " + postFix);
        theatreSeason.listAllPremierDB();
        System.out.println();
    }


}
