package ru.learnup.garayev.spring.opersale.events;

import org.springframework.context.ApplicationEvent;

public class ReplacePremierEvent extends ApplicationEvent {

    public ReplacePremierEvent(Object source) {
        super(source);
    }
}
