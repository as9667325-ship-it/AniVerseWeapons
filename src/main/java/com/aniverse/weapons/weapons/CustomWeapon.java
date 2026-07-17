package com.aniverse.weapons.weapons;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CustomWeapon {

    protected String weaponId;
    protected String displayName;
    protected String material;
    protected int customModelData;
    protected double damage;
    protected double cooldown;
    protected List<String> description;
    protected Map<String, Object> particles;
    protected Map<String, Object> sounds;
    protected Map<String, Object> ability;

    // Cooldown tracking
    private final Map<String, Long> cooldowns = new HashMap<>();

    public void loadConfig(FileConfiguration config, String path) {
        this.weaponId = path.replace("weapons.", "");
        this.displayName = config.getString(path + ".display-name", "Unknown Weapon");
        this.material = config.getString(path + ".material", "DIAMOND_SWORD");
        this.customModelData = config.getInt(path + ".custom-model-data", 1000);
        this.damage = config.getDouble(path + ".damage", 10.0);
        this.cooldown = config.getDouble(path + ".cooldown", 1.0);
        this.description = config.getStringList(path + ".description");
        this.particles = (Map<String, Object>) config.get(path + ".particles");
        this.sounds = (Map<String, Object>) config.get(path + ".sounds");
        this.ability = (Map<String, Object>) config.get(path + ".ability");
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(org.bukkit.Material.getMaterial(material));
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            // Set display name
            meta.displayName(Component.text(translateColors(displayName))
                    .decoration(TextDecoration.ITALIC, false));

            // Set custom model data
            meta.setCustomModelData(customModelData);

            // Set lore
            List<Component> lore = new ArrayList<>();
            for (String line : description) {
                lore.add(Component.text(translateColors(line))
                        .decoration(TextDecoration.ITALIC, false));
            }
            meta.lore(lore);

            item.setItemMeta(meta);
        }

        return item;
    }

    public abstract void onRightClick(Player player);

    public boolean isOnCooldown(Player player) {
        return cooldowns.containsKey(player.getUniqueId().toString()) &&
                System.currentTimeMillis() < cooldowns.get(player.getUniqueId().toString());
    }

    public void setCooldown(Player player) {
        cooldowns.put(player.getUniqueId().toString(), System.currentTimeMillis() + (long)(cooldown * 1000));
    }

    public double getRemainingCooldown(Player player) {
        if (!cooldowns.containsKey(player.getUniqueId().toString())) {
            return 0;
        }
        long remaining = cooldowns.get(player.getUniqueId().toString()) - System.currentTimeMillis();
        return Math.max(0, remaining / 1000.0);
    }

    protected String translateColors(String text) {
        return text.replace("&0", "\u00A70")
                   .replace("&1", "\u00A71")
                   .replace("&2", "\u00A72")
                   .replace("&3", "\u00A73")
                   .replace("&4", "\u00A74")
                   .replace("&5", "\u00A75")
                   .replace("&6", "\u00A76")
                   .replace("&7", "\u00A77")
                   .replace("&8", "\u00A78")
                   .replace("&9", "\u00A79")
                   .replace("&a", "\u00A7a")
                   .replace("&b", "\u00A7b")
                   .replace("&c", "\u00A7c")
                   .replace("&d", "\u00A7d")
                   .replace("&e", "\u00A7e")
                   .replace("&f", "\u00A7f")
                   .replace("&r", "\u00A7r");
    }

    // Getters
    public String getWeaponId() { return weaponId; }
    public String getDisplayName() { return displayName; }
    public double getDamage() { return damage; }
    public double getCooldown() { return cooldown; }
    public String getMaterial() { return material; }
    public int getCustomModelData() { return customModelData; }
}