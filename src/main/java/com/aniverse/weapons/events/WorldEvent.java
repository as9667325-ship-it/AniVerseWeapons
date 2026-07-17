package com.aniverse.weapons.events;

import org.bukkit.Location;
import org.bukkit.World;

public class WorldEvent {

    private final String id;
    private final String displayName;
    private final String description;
    private final int durationSeconds;
    private final Location center;
    private long startTime;

    public WorldEvent(String id, String displayName, String description, 
                     int durationSeconds, Location center) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.durationSeconds = durationSeconds;
        this.center = center;
        this.startTime = System.currentTimeMillis();
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public boolean isActive() {
        long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
        return elapsedSeconds < durationSeconds;
    }

    public double getTimeRemaining() {
        long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
        return Math.max(0, durationSeconds - elapsedSeconds);
    }

    public void execute() {
        // Override in subclasses
    }

    // Getters
    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public String getDescription() { return description; }
    public int getDurationSeconds() { return durationSeconds; }
    public Location getCenter() { return center; }
}