package com.spiet.account.cmd.infra;

import com.spiet.account.cmd.domain.AccountAggregate;
import com.spiet.cqrs.core.domain.AggregatorRoot;
import com.spiet.cqrs.core.infra.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class EventSourceHandler implements com.spiet.cqrs.core.handlers.EventSourceHandler<AccountAggregate> {

    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregatorRoot aggregatorRoot) {
        eventStore.saveEvents(aggregatorRoot.id, aggregatorRoot.getUncomittedChanges(), aggregatorRoot.getVersion());
        aggregatorRoot.mockChangesAsCommited();
    }

    @Override
    public AccountAggregate getById(String id) throws Exception {
       var aggregate = new AccountAggregate();
       var events = eventStore.getEvents(id);

       aggregate.replayEvents(events);
       var latestVersion = events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
       aggregate.setVersion(latestVersion.get());

       return aggregate;
    }
}
