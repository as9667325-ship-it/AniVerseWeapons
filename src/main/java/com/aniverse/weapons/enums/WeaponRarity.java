package com.aniverse.weapons.enums;

public enum WeaponRarity {
    COMMON("&7Common", "200,200,200"),
    RARE("&3Rare", "0,200,255"),
    EPIC("&9Epic", "100,100,255"),
    MYTHIC("&5Mythic", "200,0,255"),
    LEGENDARY("&6Legendary", "255,200,0"),
    DIVINE("&c✦ Divine ✦", "255,0,0");

    private final String displayName;
    private final String color;

    WeaponRarity(String displayName, String color) {
        this.displayName = displayName;
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getColor() {
        return color;
    }

    public static WeaponRarity fromString(String name) {
        try {
            return WeaponRarity.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return COMMON;
        }
    }
}