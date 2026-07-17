package com.aniverse.weapons.abilities;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    private final Map<String, Long> cooldowns = new HashMap<>();

    public void setCooldown(String abilityName, long cooldownSeconds) {
        cooldowns.put(abilityName, System.currentTimeMillis() + (cooldownSeconds * 1000));
    }

    public boolean isOnCooldown(String abilityName) {
        return cooldowns.containsKey(abilityName) && 
               System.currentTimeMillis() < cooldowns.get(abilityName);
    }

    public long getRemainingCooldown(String abilityName) {
        if (!cooldowns.containsKey(abilityName)) {
            return 0;
        }
        long remaining = cooldowns.get(abilityName) - System.currentTimeMillis();
        return Math.max(0, remaining / 1000); // Convert to seconds
    }

    public void resetCooldown(String abilityName) {
        cooldowns.remove(abilityName);
    }

    public void resetAllCooldowns() {
        cooldowns.clear();
    }

    public double getRemainingCooldownDecimal(String abilityName) {
        if (!cooldowns.containsKey(abilityName)) {
            return 0;
        }
        long remaining = cooldowns.get(abilityName) - System.currentTimeMillis();
        return Math.max(0, remaining / 1000.0);
    }
}