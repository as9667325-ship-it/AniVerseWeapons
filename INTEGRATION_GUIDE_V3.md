# Version 3.0 Integration Guide

## New Systems Quick Start

### 1. Boss System
```java
BossManager bossManager = plugin.getBossManager();
Boss boss = bossManager.getBoss("infernus");
boss.spawn(location);
```

### 2. Dungeon Creation
```java
DungeonManager dungeonManager = plugin.getDungeonManager();
dungeonManager.createDungeon("dungeon_1", "Infernal Lair", "Hard", 5, "infernus", 30);
```

### 3. Weapon Souls
```java
WeaponSoul soul = plugin.getSoulManager().getSoul("soul_of_flames");
weapon.infuseSoul(soul);
```

### 4. Gem Sockets
```java
Gem gem = plugin.getGemManager().getGem("ruby");
weapon.socketGem(gem, 0);
```

### 5. Daily Quests
```java
QuestManager questManager = plugin.getQuestManager();
questManager.updateQuests(player, "mob_kill", 50);
```

### 6. World Events
```java
WorldEventManager eventManager = plugin.getWorldEventManager();
WorldEvent event = new WorldEvent("meteor_shower", "Meteor Shower", "...", 60, location);
eventManager.startEvent(event);
```

### 7. Crates
```java
CrateManager crateManager = plugin.getCrateManager();
Crate crate = crateManager.getCrate("legendary");
crateManager.openCrate(player, crate);
```

### 8. Database Operations
```java
Database db = plugin.getDatabase();
db.saveWeaponAsync(player, "phoenix_blade", 50, 5000, 10);
db.loadWeaponAsync(player, "phoenix_blade").thenAccept(data -> {
    // Use loaded data
});
```

### 9. Admin GUI
```java
AdminGUI adminGUI = plugin.getAdminGUI();
adminGUI.openAdminMenu(player);
```

## Performance Considerations

- All database operations are async
- Boss AI runs on separate threads
- Events update on configurable intervals
- Batch processing for bulk saves
- Efficient particle spawning
- Optimized entity handling

## Extending the System

Create custom bosses:
```java
public class CustomBoss extends Boss {
    @Override
    protected void fireBreath() {
        // Your custom implementation
    }
}
```

Create custom events:
```java
public class CustomEvent extends WorldEvent {
    @Override
    public void execute() {
        // Your custom event logic
    }
}
```

Create custom quests:
```java
DailyQuest customQuest = new DailyQuest(
    "custom_id",
    "Custom Quest",
    "Description",
    100, // target
    1000, // materials
    2000, // xp
    null // special reward
);
```

## Database Schema

SQLite and MySQL use identical schemas:
- weapons (id, player_id, weapon_id, level, xp, upgrade_level, skin)
- player_stats (player_id, total_kills, bosses_defeated, materials, coins)
- gems (player_id, weapon_id, gem_id, socket_index)
- souls (player_id, weapon_id, soul_id)

## Troubleshooting

**Boss not spawning?**
- Check spawn location format in bosses.yml
- Verify boss-bar-enabled is true

**Database errors?**
- For MySQL: Verify connection settings
- For SQLite: Check file permissions
- Enable async mode if getting lag

**Events not triggering?**
- Check world-events.yml enabled: true
- Verify interval settings
- Check server log for errors