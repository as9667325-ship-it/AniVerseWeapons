package com.aniverse.weapons.events;

import java.util.*;

public class WorldEventManager {

    private final List<WorldEvent> activeEvents = new ArrayList<>();
    private final Random random = new Random();

    public void startEvent(WorldEvent event) {
        activeEvents.add(event);
        event.start();
    }

    public void stopEvent(WorldEvent event) {
        activeEvents.remove(event);
    }

    public List<WorldEvent> getActiveEvents() {
        return new ArrayList<>(activeEvents);
    }

    public void updateEvents() {
        List<WorldEvent> toRemove = new ArrayList<>();
        for (WorldEvent event : activeEvents) {
            if (!event.isActive()) {
                toRemove.add(event);
            } else {
                event.execute();
            }
        }
        toRemove.forEach(this::stopEvent);
    }

    public boolean hasActiveEvent(String eventId) {
        return activeEvents.stream().anyMatch(e -> e.getId().equals(eventId));
    }
}