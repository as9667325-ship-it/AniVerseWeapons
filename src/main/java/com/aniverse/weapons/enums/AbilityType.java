package com.aniverse.weapons.enums;

public enum AbilityType {
    PASSIVE("Passive", true),
    ACTIVE("Active", false),
    ULTIMATE("Ultimate", false);

    private final String displayName;
    private final boolean alwaysActive;

    AbilityType(String displayName, boolean alwaysActive) {
        this.displayName = displayName;
        this.alwaysActive = alwaysActive;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isAlwaysActive() {
        return alwaysActive;
    }
}