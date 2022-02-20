package ru.learnup.garayev.spring.opersale.events;

import org.springframework.context.ApplicationEvent;

public class NewPremierEvent extends ApplicationEvent {

    public NewPremierEvent(Object source) {
        super(source);
    }
}
