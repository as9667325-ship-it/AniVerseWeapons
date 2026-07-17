package com.aniverse.weapons.gui;

import com.aniverse.weapons.AniVerseWeapons;
import com.aniverse.weapons.weapons.CustomWeapon;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.*;

public class WeaponGUI {

    private final AniVerseWeapons plugin;
    private static final int ITEMS_PER_PAGE = 45;

    public WeaponGUI(AniVerseWeapons plugin) {
        this.plugin = plugin;
    }

    public void openMainMenu(Player player) {
        Inventory menu = Bukkit.createInventory(null, 54, "§b§lAniVerse Weapons");

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

        // Weapon List Button
        ItemStack weaponList = createButton(Material.DIAMOND_SWORD, "§e§lWeapon Collection", 
            "§7View all weapons", "§7Click to browse");
        menu.setItem(11, weaponList);

        // My Weapons Button
        ItemStack myWeapons = createButton(Material.CHEST, "§a§lMy Weapons", 
            "§7Weapons you own", "§7Click to view");
        menu.setItem(13, myWeapons);

        // Stats Button
        ItemStack stats = createButton(Material.BOOK, "§c§lStats & Info", 
            "§7View weapon stats", "§7Click to view");
        menu.setItem(15, stats);

        // Settings Button
        ItemStack settings = createButton(Material.REDSTONE, "§6§lSettings", 
            "§7Adjust preferences", "§7Click to open");
        menu.setItem(20, settings);

        // Close Button
        ItemStack close = createButton(Material.BARRIER, "§c§lClose", 
            "§7Close this menu", "");
        menu.setItem(49, close);

        player.openInventory(menu);
    }

    public void openWeaponList(Player player, int page) {
        List<CustomWeapon> weapons = new ArrayList<>(plugin.getWeaponManager().getAllWeapons().values());
        
        Inventory inv = Bukkit.createInventory(null, 54, "§b§lWeapon Collection - Page " + (page + 1));

        // Fill background
        ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta bgMeta = background.getItemMeta();
        if (bgMeta != null) {
            bgMeta.displayName(Component.text(" ").decoration(TextDecoration.ITALIC, false));
            background.setItemMeta(bgMeta);
        }
        for (int i = 0; i < 54; i++) {
            inv.setItem(i, background);
        }

        // Display weapons
        int startIndex = page * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, weapons.size());
        int slot = 0;

        for (int i = startIndex; i < endIndex && slot < ITEMS_PER_PAGE; i++) {
            if (slot == 45) break;
            CustomWeapon weapon = weapons.get(i);
            inv.setItem(slot++, weapon.createItem());
        }

        // Navigation buttons
        if (page > 0) {
            ItemStack prevPage = createButton(Material.ARROW, "§a« Previous", "", "");
            inv.setItem(47, prevPage);
        }

        if (endIndex < weapons.size()) {
            ItemStack nextPage = createButton(Material.ARROW, "§aNext »", "", "");
            inv.setItem(51, nextPage);
        }

        ItemStack back = createButton(Material.BARRIER, "§c§lBack", "", "");
        inv.setItem(45, back);

        player.openInventory(inv);
    }

    private ItemStack createButton(Material material, String name, String lore1, String lore2) {
        ItemStack button = new ItemStack(material);
        ItemMeta meta = button.getItemMeta();

        if (meta != null) {
            meta.displayName(Component.text(name).decoration(TextDecoration.ITALIC, false));
            List<Component> lore = new ArrayList<>();
            if (!lore1.isEmpty()) {
                lore.add(Component.text(lore1).decoration(TextDecoration.ITALIC, false));
            }
            if (!lore2.isEmpty()) {
                lore.add(Component.text(lore2).decoration(TextDecoration.ITALIC, false));
            }
            meta.lore(lore);
            button.setItemMeta(meta);
        }

        return button;
    }
}