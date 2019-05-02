package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.demo.events.ExampleEventCreation;

@Service
public class ExampleEventProcessor {

    @Async
    @TransactionalEventListener
    public void handleBankTransferCompletedEvent(ExampleEventCreation event) {
	System.out.println("aloha: " + event);
    }
}
