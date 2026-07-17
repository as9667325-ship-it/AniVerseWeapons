package com.aniverse.weapons.upgrades;

public class UpgradeManager {

    private int currentLevel = 1;
    private final int maxLevel = 15;

    public UpgradeManager() {
    }

    public boolean attemptUpgrade(boolean hasSafeScroll, boolean hasProtectionScroll) {
        double baseChance = 0.90;
        double chancePerLevel = -0.05;
        double successChance = baseChance + (chancePerLevel * (currentLevel - 1));

        if (hasSafeScroll) {
            successChance = 1.0;
        } else if (hasProtectionScroll) {
            successChance = Math.min(1.0, successChance + 0.50);
        }

        boolean success = Math.random() < successChance;

        if (success && currentLevel < maxLevel) {
            currentLevel++;
        } else if (!success && currentLevel > 1) {
            currentLevel--; // Downgrade on fail
        }

        return success;
    }

    public double getSuccessChance() {
        double baseChance = 0.90;
        double chancePerLevel = -0.05;
        return Math.max(0.1, baseChance + (chancePerLevel * (currentLevel - 1)));
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int level) {
        this.currentLevel = Math.max(1, Math.min(level, maxLevel));
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public boolean canUpgrade() {
        return currentLevel < maxLevel;
    }

    public String getUpgradeLevelDisplay() {
        if (currentLevel < 5) {
            return "&7+" + currentLevel;
        } else if (currentLevel < 10) {
            return "&b+" + currentLevel;
        } else if (currentLevel < 15) {
            return "&e+" + currentLevel;
        } else {
            return "&c+" + currentLevel;
        }
    }
}