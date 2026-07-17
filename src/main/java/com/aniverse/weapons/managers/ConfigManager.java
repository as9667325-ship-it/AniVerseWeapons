package com.aniverse.weapons.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final JavaPlugin plugin;
    private FileConfiguration mainConfig;
    private FileConfiguration weaponsConfig;
    private File configFile;
    private File weaponsFile;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadConfigs() {
        // Create data folder if it doesn't exist
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        // Create weapons directory
        File weaponsDir = new File(plugin.getDataFolder(), "weapons");
        if (!weaponsDir.exists()) {
            weaponsDir.mkdirs();
        }

        // Load main config
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        mainConfig = YamlConfiguration.loadConfiguration(configFile);

        // Load weapons config
        weaponsFile = new File(weaponsDir, "weapons.yml");
        if (!weaponsFile.exists()) {
            plugin.saveResource("weapons/weapons.yml", false);
        }
        weaponsConfig = YamlConfiguration.loadConfiguration(weaponsFile);

        plugin.getLogger().info("Configuration files loaded successfully!");
    }

    public void reloadConfigs() {
        mainConfig = YamlConfiguration.loadConfiguration(configFile);
        weaponsConfig = YamlConfiguration.loadConfiguration(weaponsFile);
        plugin.getLogger().info("Configurations reloaded!");
    }

    public FileConfiguration getMainConfig() {
        return mainConfig;
    }

    public FileConfiguration getWeaponsConfig() {
        return weaponsConfig;
    }

    public void saveConfig() {
        try {
            mainConfig.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save config.yml: " + e.getMessage());
        }
    }
}