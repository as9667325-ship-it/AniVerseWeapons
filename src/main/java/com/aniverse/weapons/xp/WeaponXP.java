package com.aniverse.weapons.xp;

public class WeaponXP {

    private int level = 1;
    private long currentXP = 0;
    private final int maxLevel = 100;

    public WeaponXP() {
    }

    public void addXP(long amount) {
        if (level >= maxLevel) return;

        currentXP += amount;
        long requiredXP = getXPRequiredForLevel(level);

        while (currentXP >= requiredXP && level < maxLevel) {
            currentXP -= requiredXP;
            level++;
            requiredXP = getXPRequiredForLevel(level);
        }
    }

    public long getXPRequiredForLevel(int level) {
        if (level <= 10) {
            return 100L;
        } else if (level <= 25) {
            return 250L;
        } else if (level <= 50) {
            return 500L;
        } else if (level <= 75) {
            return 1000L;
        } else {
            return 2000L;
        }
    }

    public double getProgressPercentage() {
        if (level >= maxLevel) return 100.0;
        long required = getXPRequiredForLevel(level);
        return (currentXP / (double) required) * 100;
    }

    public int getLevel() {
        return level;
    }

    public long getCurrentXP() {
        return currentXP;
    }

    public long getXPForCurrentLevel() {
        return getXPRequiredForLevel(level);
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public boolean isMaxLevel() {
        return level >= maxLevel;
    }

    public boolean hasUnlockedAbility(String abilityType) {
        return switch (abilityType) {
            case "PASSIVE" -> true; // Always unlocked
            case "ACTIVE" -> level >= 10;
            case "ULTIMATE" -> level >= 50;
            default -> false;
        };
    }

    public String getLevelDisplay() {
        if (level < 25) {
            return "&7Lvl " + level;
        } else if (level < 50) {
            return "&bLvl " + level;
        } else if (level < 75) {
            return "&eLvl " + level;
        } else {
            return "&cLvl " + level;
        }
    }
}