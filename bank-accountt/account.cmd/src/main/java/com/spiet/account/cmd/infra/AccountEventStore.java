package com.spiet.account.cmd.infra;

import com.spiet.account.cmd.domain.AccountAggregate;
import com.spiet.cqrs.core.domain.EventStoreRepository;
import com.spiet.cqrs.core.events.BaseEvent;
import com.spiet.cqrs.core.events.EventModel;
import com.spiet.cqrs.core.exceptions.ConcurrencyException;
import com.spiet.cqrs.core.infra.AccountEventsProducer;
import com.spiet.cqrs.core.infra.EventStore;
import com.spiet.cqrs.core.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEventStore implements EventStore {

    @Autowired
    private EventProducer producer;

    @Autowired
    private  EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> baseEvents, Integer expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);

        if (expectedVersion != -1 && eventStream.get(eventStream.size()-1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }

        var version = expectedVersion;

        for (var event : baseEvents) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventData(event)
                    .eventType(event.getClass().getTypeName()).build();
            var persistedEvent = eventStoreRepository.save(eventModel);

            if (persistedEvent != null) {
                producer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) throws Exception {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null) {
            throw new  Exception();
        }

        return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
    }


}
