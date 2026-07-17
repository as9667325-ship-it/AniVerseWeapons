package com.aniverse.weapons.dungeons;

import java.util.*;

public class DungeonManager {

    private final Map<String, Dungeon> activeDungeons = new HashMap<>();

    public void createDungeon(String id, String name, String difficulty, 
                             int maxPlayers, String bossId, long durationMinutes) {
        Dungeon dungeon = new Dungeon(id, name, difficulty, maxPlayers, bossId, durationMinutes);
        activeDungeons.put(id, dungeon);
    }

    public Dungeon getDungeon(String id) {
        return activeDungeons.get(id);
    }

    public void removeDungeon(String id) {
        activeDungeons.remove(id);
    }

    public Collection<Dungeon> getActiveDungeons() {
        return activeDungeons.values();
    }

    public void updateDungeons() {
        List<String> toRemove = new ArrayList<>();
        for (Dungeon dungeon : activeDungeons.values()) {
            if (dungeon.isTimeExpired() && dungeon.getPlayerCount() == 0) {
                toRemove.add(dungeon.getId());
            }
        }
        toRemove.forEach(this::removeDungeon);
    }
}