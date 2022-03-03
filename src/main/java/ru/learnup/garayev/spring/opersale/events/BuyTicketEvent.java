package ru.learnup.garayev.spring.opersale.events;

import org.springframework.context.ApplicationEvent;

public class BuyTicketEvent extends ApplicationEvent {

    public BuyTicketEvent(Object source) {
        super(source);
    }
}
