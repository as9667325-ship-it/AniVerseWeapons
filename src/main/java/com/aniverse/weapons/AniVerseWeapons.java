package com.aniverse.weapons;

import org.bukkit.plugin.java.JavaPlugin;
import com.aniverse.weapons.commands.WeaponsCommand;
import com.aniverse.weapons.listeners.WeaponListener;
import com.aniverse.weapons.managers.WeaponManager;
import com.aniverse.weapons.managers.ConfigManager;

public class AniVerseWeapons extends JavaPlugin {

    private static AniVerseWeapons instance;
    private WeaponManager weaponManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        
        getLogger().info("================================");
        getLogger().info("AniVerseWeapons v1.0.0 Loading...");
        getLogger().info("================================");

        // Initialize configuration
        configManager = new ConfigManager(this);
        configManager.loadConfigs();

        // Initialize weapon manager
        weaponManager = new WeaponManager(this);
        weaponManager.loadWeapons();

        // Register commands
        getCommand("avweapons").setExecutor(new WeaponsCommand(this));

        // Register event listeners
        getServer().getPluginManager().registerEvents(new WeaponListener(this), this);

        getLogger().info("AniVerseWeapons successfully enabled!");
        getLogger().info("Loaded " + weaponManager.getWeaponsCount() + " weapons");
        getLogger().info("================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("AniVerseWeapons disabled!");
    }

    public static AniVerseWeapons getInstance() {
        return instance;
    }

    public WeaponManager getWeaponManager() {
        return weaponManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}