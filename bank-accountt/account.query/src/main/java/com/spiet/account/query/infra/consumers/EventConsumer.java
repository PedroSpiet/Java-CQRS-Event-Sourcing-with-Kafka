package com.spiet.account.query.infra.consumers;

import com.spiet.account.common.events.AccountClosedEvent;
import com.spiet.account.common.events.FundsDepositEvent;
import com.spiet.account.common.events.FundsWithDrawEvents;
import com.spiet.account.common.events.OpenAccountEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload OpenAccountEvent event, Acknowledgment ack);
    void consume(@Payload FundsDepositEvent event, Acknowledgment ack);
    void consume(@Payload FundsWithDrawEvents event, Acknowledgment ack);
    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
