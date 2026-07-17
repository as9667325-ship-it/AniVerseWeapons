package com.aniverse.weapons.managers;

import com.aniverse.weapons.weapons.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeaponManager {

    private final JavaPlugin plugin;
    private final Map<String, CustomWeapon> weapons = new HashMap<>();
    private final ConfigManager configManager;

    public WeaponManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.configManager = new ConfigManager(plugin);
    }

    public void loadWeapons() {
        FileConfiguration weaponsConfig = configManager.getWeaponsConfig();
        Set<String> weaponKeys = weaponsConfig.getConfigurationSection("weapons").getKeys(false);

        plugin.getLogger().info("Loading " + weaponKeys.size() + " weapons...");

        for (String weaponKey : weaponKeys) {
            CustomWeapon weapon = loadWeapon(weaponKey, weaponsConfig);
            if (weapon != null) {
                weapons.put(weaponKey, weapon);
            }
        }

        plugin.getLogger().info("Loaded " + weapons.size() + " weapons successfully!");
    }

    private CustomWeapon loadWeapon(String weaponKey, FileConfiguration config) {
        String weaponPath = "weapons." + weaponKey;
        
        // Determine which weapon class to instantiate based on the key
        CustomWeapon weapon = switch(weaponKey) {
            case "phoenix_blade" -> new PhoenixBlade();
            case "frost_staff" -> new FrostStaff();
            case "shadow_dagger" -> new ShadowDagger();
            case "lightning_hammer" -> new LightningHammer();
            case "inferno_bow" -> new InfernoBow();
            case "void_scythe" -> new VoidScythe();
            case "crystal_staff" -> new CrystalStaff();
            case "obsidian_axe" -> new ObsidianAxe();
            case "celestial_sword" -> new CelestialSword();
            case "temporal_pickaxe" -> new TemporalPickaxe();
            // Framework weapons
            default -> new FrameworkWeapon(weaponKey);
        };

        weapon.loadConfig(config, weaponPath);
        return weapon;
    }

    public CustomWeapon getWeapon(String weaponKey) {
        return weapons.get(weaponKey.toLowerCase());
    }

    public Map<String, CustomWeapon> getAllWeapons() {
        return new HashMap<>(weapons);
    }

    public int getWeaponsCount() {
        return weapons.size();
    }

    public boolean weaponExists(String weaponKey) {
        return weapons.containsKey(weaponKey.toLowerCase());
    }
}