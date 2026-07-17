package com.aniverse.weapons.abilities;

import java.util.HashMap;
import java.util.Map;

public class ManaManager {

    private double currentMana;
    private final double maxMana = 100.0;
    private final double manaRegenPerSecond = 0.5;

    public ManaManager() {
        this.currentMana = maxMana;
    }

    public void regenerateMana() {
        currentMana = Math.min(maxMana, currentMana + manaRegenPerSecond);
    }

    public boolean canUseMana(double amount) {
        return currentMana >= amount;
    }

    public void useMana(double amount) {
        currentMana = Math.max(0, currentMana - amount);
    }

    public void restoreMana(double amount) {
        currentMana = Math.min(maxMana, currentMana + amount);
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public double getManaPercentage() {
        return (currentMana / maxMana) * 100;
    }

    public void setCurrentMana(double mana) {
        this.currentMana = Math.max(0, Math.min(mana, maxMana));
    }

    public String getManaBar() {
        int filledBars = (int) ((currentMana / maxMana) * 10);
        StringBuilder bar = new StringBuilder("&3[");
        for (int i = 0; i < 10; i++) {
            if (i < filledBars) {
                bar.append("&b█");
            } else {
                bar.append("&8█");
            }
        }
        bar.append("&3] &b").append(String.format("%.0f", currentMana)).append("/").append(String.format("%.0f", maxMana));
        return bar.toString();
    }
}