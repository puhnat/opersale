package ru.learnup.garayev.spring.opersale.repository.interfaces;

import org.springframework.stereotype.Repository;
import ru.learnup.garayev.spring.opersale.model.User;

public interface UserRepository {

    User findByLogin(String login);

}
