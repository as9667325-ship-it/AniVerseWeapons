package com.aniverse.weapons.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import java.util.ArrayList;
import java.util.List;

public class AdminGUI {

    public void openAdminMenu(Player player) {
        Inventory menu = Bukkit.createInventory(null, 54, "&c&l[ADMIN] AniVerse Weapons");

        // Fill background
        ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta bgMeta = background.getItemMeta();
        if (bgMeta != null) {
            bgMeta.displayName(Component.text(" ").decoration(TextDecoration.ITALIC, false));
            background.setItemMeta(bgMeta);
        }
        for (int i = 0; i < 54; i++) {
            menu.setItem(i, background);
        }

        // Weapon Management
        ItemStack weaponMgmt = createButton(Material.DIAMOND_SWORD, "&e&lManage Weapons", 
            "&7View and edit weapons");
        menu.setItem(11, weaponMgmt);

        // Spawn Bosses
        ItemStack spawnBosses = createButton(Material.WITHER_SKELETON_SKULL, "&c&lSpawn Bosses", 
            "&7Spawn custom bosses");
        menu.setItem(13, spawnBosses);

        // Reload Config
        ItemStack reloadConfig = createButton(Material.REDSTONE, "&6&lReload Config", 
            "&7Reload all configs");
        menu.setItem(15, reloadConfig);

        // Give Items
        ItemStack giveItems = createButton(Material.CHEST, "&a&lGive Items", 
            "&7Give items to players");
        menu.setItem(20, giveItems);

        // Player Data
        ItemStack playerData = createButton(Material.BOOK, "&9&lPlayer Data", 
            "&7View player statistics");
        menu.setItem(22, playerData);

        // Events
        ItemStack events = createButton(Material.NETHER_STAR, "&d&lWorld Events", 
            "&7Manage world events");
        menu.setItem(24, events);

        player.openInventory(menu);
    }

    private ItemStack createButton(Material material, String name, String lore) {
        ItemStack button = new ItemStack(material);
        ItemMeta meta = button.getItemMeta();
        if (meta != null) {
            meta.displayName(Component.text(name).decoration(TextDecoration.ITALIC, false));
            List<Component> loreList = new ArrayList<>();
            loreList.add(Component.text(lore).decoration(TextDecoration.ITALIC, false));
            meta.lore(loreList);
            button.setItemMeta(meta);
        }
        return button;
    }
}