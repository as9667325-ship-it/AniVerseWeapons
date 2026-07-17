package com.aniverse.weapons.abilities;

import com.aniverse.weapons.enums.AbilityType;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.HashMap;
import java.util.Map;

public class AbilitySystem {

    private final Map<String, Ability> abilities = new HashMap<>();
    private Ability passiveAbility;
    private Ability activeAbility;
    private Ability ultimateAbility;

    public void loadAbilities(FileConfiguration config) {
        // Load passive abilities
        if (config.contains("passive-abilities")) {
            for (String key : config.getConfigurationSection("passive-abilities").getKeys(false)) {
                String path = "passive-abilities." + key;
                Ability ability = new Ability(
                    key,
                    config.getString(path + ".name", "Unknown"),
                    config.getString(path + ".description", ""),
                    AbilityType.PASSIVE,
                    config.getDouble(path + ".mana-cost", 0),
                    0,
                    1.0
                );
                abilities.put(key, ability);
            }
        }

        // Load active abilities
        if (config.contains("active-abilities")) {
            for (String key : config.getConfigurationSection("active-abilities").getKeys(false)) {
                String path = "active-abilities." + key;
                Ability ability = new Ability(
                    key,
                    config.getString(path + ".name", "Unknown"),
                    config.getString(path + ".description", ""),
                    AbilityType.ACTIVE,
                    config.getDouble(path + ".mana-cost", 30),
                    config.getLong(path + ".cooldown", 5),
                    config.getDouble(path + ".damage-multiplier", 2.0)
                );
                abilities.put(key, ability);
            }
        }

        // Load ultimate abilities
        if (config.contains("ultimate-abilities")) {
            for (String key : config.getConfigurationSection("ultimate-abilities").getKeys(false)) {
                String path = "ultimate-abilities." + key;
                Ability ability = new Ability(
                    key,
                    config.getString(path + ".name", "Unknown"),
                    config.getString(path + ".description", ""),
                    AbilityType.ULTIMATE,
                    config.getDouble(path + ".mana-cost", 100),
                    config.getLong(path + ".cooldown", 30),
                    config.getDouble(path + ".damage-multiplier", 5.0)
                );
                abilities.put(key, ability);
            }
        }
    }

    public Ability getAbility(String id) {
        return abilities.get(id);
    }

    public Map<String, Ability> getAllAbilities() {
        return new HashMap<>(abilities);
    }

    public void setPassiveAbility(Ability ability) {
        this.passiveAbility = ability;
    }

    public void setActiveAbility(Ability ability) {
        this.activeAbility = ability;
    }

    public void setUltimateAbility(Ability ability) {
        this.ultimateAbility = ability;
    }

    public Ability getPassiveAbility() { return passiveAbility; }
    public Ability getActiveAbility() { return activeAbility; }
    public Ability getUltimateAbility() { return ultimateAbility; }
}