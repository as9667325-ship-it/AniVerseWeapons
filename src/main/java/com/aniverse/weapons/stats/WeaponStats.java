package com.aniverse.weapons.stats;

public class WeaponStats {

    private double damage;
    private double criticalChance;
    private double criticalDamage;
    private double attackSpeed;
    private double abilityDamage;
    private double lifesteal;
    private double defensePenetration;

    public WeaponStats() {
        this.damage = 10.0;
        this.criticalChance = 0.0;
        this.criticalDamage = 1.5;
        this.attackSpeed = 1.0;
        this.abilityDamage = 1.0;
        this.lifesteal = 0.0;
        this.defensePenetration = 0.0;
    }

    public WeaponStats(double damage, double critChance, double critDamage, 
                       double attackSpeed, double abilityDamage, 
                       double lifesteal, double defPenetration) {
        this.damage = damage;
        this.criticalChance = critChance;
        this.criticalDamage = critDamage;
        this.attackSpeed = attackSpeed;
        this.abilityDamage = abilityDamage;
        this.lifesteal = lifesteal;
        this.defensePenetration = defPenetration;
    }

    // Getters
    public double getDamage() { return damage; }
    public double getCriticalChance() { return criticalChance; }
    public double getCriticalDamage() { return criticalDamage; }
    public double getAttackSpeed() { return attackSpeed; }
    public double getAbilityDamage() { return abilityDamage; }
    public double getLifesteal() { return lifesteal; }
    public double getDefensePenetration() { return defensePenetration; }

    // Setters
    public void setDamage(double damage) { this.damage = damage; }
    public void setCriticalChance(double chance) { this.criticalChance = chance; }
    public void setCriticalDamage(double damage) { this.criticalDamage = damage; }
    public void setAttackSpeed(double speed) { this.attackSpeed = speed; }
    public void setAbilityDamage(double damage) { this.abilityDamage = damage; }
    public void setLifesteal(double lifesteal) { this.lifesteal = lifesteal; }
    public void setDefensePenetration(double penetration) { this.defensePenetration = penetration; }

    // Add to stats
    public void addDamage(double amount) { this.damage += amount; }
    public void addCriticalChance(double amount) { this.criticalChance += amount; }
    public void addCriticalDamage(double amount) { this.criticalDamage += amount; }
    public void addAttackSpeed(double amount) { this.attackSpeed += amount; }
    public void addAbilityDamage(double amount) { this.abilityDamage += amount; }
    public void addLifesteal(double amount) { this.lifesteal += amount; }
    public void addDefensePenetration(double amount) { this.defensePenetration += amount; }

    // Multiply stats (for upgrades)
    public void multiplyStats(double multiplier) {
        this.damage *= multiplier;
        this.criticalDamage *= multiplier;
        this.attackSpeed *= multiplier;
        this.abilityDamage *= multiplier;
    }

    @Override
    public String toString() {
        return String.format(
            "Stats{Dmg:%.1f, CritC:%.1f%%, CritD:%.1f, AtkSpd:%.2f, AbilDmg:%.1f, LS:%.1f%%, DefPen:%.1f%%}",
            damage, criticalChance * 100, criticalDamage, attackSpeed, abilityDamage, 
            lifesteal * 100, defensePenetration * 100
        );
    }
}