package com.aniverse.weapons.database;

public class WeaponData {

    private final String playerId;
    private final String weaponId;
    private final int level;
    private final long xp;
    private final int upgradeLevel;
    private final String skin;

    public WeaponData(String playerId, String weaponId, int level, long xp, 
                     int upgradeLevel, String skin) {
        this.playerId = playerId;
        this.weaponId = weaponId;
        this.level = level;
        this.xp = xp;
        this.upgradeLevel = upgradeLevel;
        this.skin = skin;
    }

    public String getPlayerId() { return playerId; }
    public String getWeaponId() { return weaponId; }
    public int getLevel() { return level; }
    public long getXp() { return xp; }
    public int getUpgradeLevel() { return upgradeLevel; }
    public String getSkin() { return skin; }
}