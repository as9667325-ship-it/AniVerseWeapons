package com.aniverse.weapons.souls;

import com.aniverse.weapons.abilities.Ability;
import com.aniverse.weapons.enums.AbilityType;

public class WeaponSoul {

    private final String id;
    private final String displayName;
    private final String description;
    private final String bossDropId;
    private final Ability hiddenAbility;
    private final double damageBonus;
    private final double abilityDamageBonus;

    public WeaponSoul(String id, String displayName, String description, 
                      String bossDropId, Ability hiddenAbility, 
                      double damageBonus, double abilityDamageBonus) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.bossDropId = bossDropId;
        this.hiddenAbility = hiddenAbility;
        this.damageBonus = damageBonus;
        this.abilityDamageBonus = abilityDamageBonus;
    }

    // Getters
    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public String getDescription() { return description; }
    public String getBossDropId() { return bossDropId; }
    public Ability getHiddenAbility() { return hiddenAbility; }
    public double getDamageBonus() { return damageBonus; }
    public double getAbilityDamageBonus() { return abilityDamageBonus; }
}