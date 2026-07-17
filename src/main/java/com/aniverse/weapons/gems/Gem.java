package com.aniverse.weapons.gems;

public class Gem {

    private final String id;
    private final String displayName;
    private final String color;
    private final double damageBonus;
    private final double critChanceBonus;
    private final double attackSpeedBonus;
    private final double abilityDamageBonus;
    private final double lifestealBonus;
    private final double defPenetrationBonus;

    public Gem(String id, String displayName, String color,
               double damageBonus, double critChanceBonus, double attackSpeedBonus,
               double abilityDamageBonus, double lifestealBonus, double defPenetrationBonus) {
        this.id = id;
        this.displayName = displayName;
        this.color = color;
        this.damageBonus = damageBonus;
        this.critChanceBonus = critChanceBonus;
        this.attackSpeedBonus = attackSpeedBonus;
        this.abilityDamageBonus = abilityDamageBonus;
        this.lifestealBonus = lifestealBonus;
        this.defPenetrationBonus = defPenetrationBonus;
    }

    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public String getColor() { return color; }
    public double getDamageBonus() { return damageBonus; }
    public double getCritChanceBonus() { return critChanceBonus; }
    public double getAttackSpeedBonus() { return attackSpeedBonus; }
    public double getAbilityDamageBonus() { return abilityDamageBonus; }
    public double getLifestealBonus() { return lifestealBonus; }
    public double getDefPenetrationBonus() { return defPenetrationBonus; }
}