package com.spiet.account.query.infra.consumers;

import com.spiet.account.common.events.AccountClosedEvent;
import com.spiet.account.common.events.FundsDepositEvent;
import com.spiet.account.common.events.FundsWithDrawEvents;
import com.spiet.account.common.events.OpenAccountEvent;
import com.spiet.account.query.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class Consumer implements EventConsumer{

    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "OpenAccountEvent", groupId = "${spring.kafka.consumer.bankaccConsumer}")
    @Override
    public void consume(OpenAccountEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsDepositEvent", groupId = "${spring.kafka.consumer.bankaccConsumer}")
    @Override
    public void consume(FundsDepositEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundsWithDrawEvents", groupId = "${spring.kafka.consumer.bankaccConsumer}")
    @Override
    public void consume(FundsWithDrawEvents event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.bankaccConsumer}")
    @Override
    public void consume(AccountClosedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
