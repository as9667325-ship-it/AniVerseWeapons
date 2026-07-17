package com.aniverse.weapons.abilities;

import com.aniverse.weapons.enums.AbilityType;
import com.aniverse.weapons.stats.WeaponStats;

public class Ability {

    private final String id;
    private final String name;
    private final String description;
    private final AbilityType type;
    private final double manaCost;
    private final long cooldownSeconds;
    private final double damageMultiplier;

    public Ability(String id, String name, String description, AbilityType type, 
                   double manaCost, long cooldownSeconds, double damageMultiplier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.manaCost = manaCost;
        this.cooldownSeconds = cooldownSeconds;
        this.damageMultiplier = damageMultiplier;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public AbilityType getType() { return type; }
    public double getManaCost() { return manaCost; }
    public long getCooldownSeconds() { return cooldownSeconds; }
    public double getDamageMultiplier() { return damageMultiplier; }

    public double calculateDamage(WeaponStats stats) {
        return stats.getAbilityDamage() * damageMultiplier;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", name, type.getDisplayName(), description);
    }
}