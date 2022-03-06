package com.spiet.cqrs.core.domain;

import com.spiet.cqrs.core.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class AggregatorRoot {
    private String id;
    private int version = -1;

    private List<BaseEvent> changes = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(AggregatorRoot.class.getName());

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<BaseEvent> getUncomittedChanges() {
        return changes;
    }

    public void setChanges(List<BaseEvent> changes) {
        this.changes = changes;
    }

    public Logger getLogger() {
        return logger;
    }

    public void mockChangesAsCommited() {
        this.changes.clear();
    }

    public void applyChanges(BaseEvent event, Boolean isNewEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var method = getClass().getDeclaredMethod("apply", event.getClass());

        method.setAccessible(true);
        method.invoke(this, event);

        if (isNewEvent) {
            changes.add(event);
        }
    }

    public void raiseEvent(BaseEvent event) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        applyChanges(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> {
            try {
                applyChanges(event, false);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
