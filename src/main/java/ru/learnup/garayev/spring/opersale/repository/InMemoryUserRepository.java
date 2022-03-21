package ru.learnup.garayev.spring.opersale.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.learnup.garayev.spring.opersale.model.User;
import ru.learnup.garayev.spring.opersale.repository.interfaces.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> db = new HashMap<>();

    private PasswordEncoder passwordEncoder;

    @Autowired
    public InMemoryUserRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init(){
        db.put("user", new User("user", passwordEncoder.encode("user123"), "ROLE_USER"));
        db.put("admin", new User("admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN"));
        log.info("Шифрованный пароль user123 : " + passwordEncoder.encode("user123"));
    }

    @Override
    public User findByLogin(String login) {
        return db.get(login);
    }
}
