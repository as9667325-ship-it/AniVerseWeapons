package com.aniverse.weapons.bosses;

import org.bukkit.configuration.file.FileConfiguration;
import java.util.*;

public class BossManager {

    private final Map<String, Boss> bosses = new HashMap<>();

    public void loadBosses(FileConfiguration config) {
        if (!config.contains("bosses")) return;

        for (String key : config.getConfigurationSection("bosses").getKeys(false)) {
            String path = "bosses." + key;
            Boss boss = new Boss(
                key,
                config.getString(path + ".display-name", "Unknown Boss"),
                config.getDouble(path + ".health", 100),
                config.getDouble(path + ".damage", 10),
                config.getDouble(path + ".speed", 0.2),
                config.getStringList(path + ".attack-patterns"),
                config.getStringList(path + ".loot"),
                config.getDouble(path + ".legendary-drop-chance", 0.25)
            );
            bosses.put(key, boss);
        }
    }

    public Boss getBoss(String id) {
        return bosses.get(id);
    }

    public Map<String, Boss> getAllBosses() {
        return new HashMap<>(bosses);
    }

    public Collection<Boss> getBosses() {
        return bosses.values();
    }
}