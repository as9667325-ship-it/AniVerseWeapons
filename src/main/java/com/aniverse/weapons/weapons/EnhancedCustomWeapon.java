package com.aniverse.weapons.weapons;

import com.aniverse.weapons.enums.WeaponRarity;
import com.aniverse.weapons.stats.WeaponStats;
import com.aniverse.weapons.upgrades.UpgradeManager;
import com.aniverse.weapons.xp.WeaponXP;
import com.aniverse.weapons.abilities.AbilitySystem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import java.util.ArrayList;
import java.util.List;

public class EnhancedCustomWeapon extends CustomWeapon {

    protected WeaponRarity rarity = WeaponRarity.COMMON;
    protected WeaponStats stats = new WeaponStats();
    protected UpgradeManager upgradeManager = new UpgradeManager();
    protected WeaponXP weaponXP = new WeaponXP();
    protected AbilitySystem abilitySystem = new AbilitySystem();

    @Override
    public void loadConfig(FileConfiguration config, String path) {
        super.loadConfig(config, path);
        
        // Load rarity if available
        String rarityStr = config.getString(path + ".rarity", "COMMON");
        this.rarity = WeaponRarity.fromString(rarityStr);

        // Load stats
        if (config.contains(path + ".stats")) {
            this.stats = new WeaponStats(
                config.getDouble(path + ".stats.damage", 10.0),
                config.getDouble(path + ".stats.critical-chance", 0.0),
                config.getDouble(path + ".stats.critical-damage", 1.5),
                config.getDouble(path + ".stats.attack-speed", 1.0),
                config.getDouble(path + ".stats.ability-damage", 1.0),
                config.getDouble(path + ".stats.lifesteal", 0.0),
                config.getDouble(path + ".stats.defense-penetration", 0.0)
            );
        }
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = super.createItem();
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            List<Component> lore = new ArrayList<>();

            // Rarity prefix
            lore.add(Component.text(rarity.getDisplayName()).decoration(TextDecoration.ITALIC, false));
            lore.add(Component.empty());

            // Stats
            lore.add(Component.text("§7--- Stats ---").decoration(TextDecoration.ITALIC, false));
            lore.add(Component.text(String.format("§cDamage: §f%.1f", stats.getDamage())).decoration(TextDecoration.ITALIC, false));
            lore.add(Component.text(String.format("§eCrit Chance: §f%.1f%%", stats.getCriticalChance() * 100)).decoration(TextDecoration.ITALIC, false));
            lore.add(Component.text(String.format("§6Attack Speed: §f%.2f", stats.getAttackSpeed())).decoration(TextDecoration.ITALIC, false));
            lore.add(Component.empty());

            // Upgrade level
            lore.add(Component.text("§7Upgrade: " + upgradeManager.getUpgradeLevelDisplay()).decoration(TextDecoration.ITALIC, false));

            // Weapon level
            lore.add(Component.text(weaponXP.getLevelDisplay()).decoration(TextDecoration.ITALIC, false));
            lore.add(Component.text(String.format("§7XP: §f%.0f%%", weaponXP.getProgressPercentage())).decoration(TextDecoration.ITALIC, false));

            meta.lore(lore);
            item.setItemMeta(meta);
        }

        return item;
    }

    public WeaponRarity getRarity() { return rarity; }
    public WeaponStats getStats() { return stats; }
    public UpgradeManager getUpgradeManager() { return upgradeManager; }
    public WeaponXP getWeaponXP() { return weaponXP; }
    public AbilitySystem getAbilitySystem() { return abilitySystem; }

    public void setRarity(WeaponRarity rarity) { this.rarity = rarity; }
    public void setStats(WeaponStats stats) { this.stats = stats; }
}