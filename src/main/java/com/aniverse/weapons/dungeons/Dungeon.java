package com.aniverse.weapons.dungeons;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.*;

public class Dungeon {

    private final String id;
    private final String name;
    private final String difficulty;
    private final int maxPlayers;
    private final String bossId;
    private final Set<Player> players = new HashSet<>();
    private final long startTime;
    private final long durationMinutes;
    private Location bossRoom;
    private Location rewardRoom;

    public Dungeon(String id, String name, String difficulty, int maxPlayers, 
                   String bossId, long durationMinutes) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.bossId = bossId;
        this.durationMinutes = durationMinutes;
        this.startTime = System.currentTimeMillis();
    }

    public void addPlayer(Player player) {
        if (players.size() < maxPlayers) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public boolean isTimeExpired() {
        long elapsedMinutes = (System.currentTimeMillis() - startTime) / 60000;
        return elapsedMinutes >= durationMinutes;
    }

    public double getTimeRemaining() {
        long elapsedMinutes = (System.currentTimeMillis() - startTime) / 60000;
        return Math.max(0, durationMinutes - elapsedMinutes);
    }

    public void generateArena() {
        // Generate dungeon arena with boss room and reward room
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDifficulty() { return difficulty; }
    public int getMaxPlayers() { return maxPlayers; }
    public String getBossId() { return bossId; }
    public Set<Player> getPlayers() { return new HashSet<>(players); }
    public int getPlayerCount() { return players.size(); }
    public Location getBossRoom() { return bossRoom; }
    public Location getRewardRoom() { return rewardRoom; }
    public void setBossRoom(Location location) { this.bossRoom = location; }
    public void setRewardRoom(Location location) { this.rewardRoom = location; }
}