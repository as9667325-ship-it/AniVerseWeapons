package com.aniverse.weapons.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import com.aniverse.weapons.AniVerseWeapons;

public class GUIListener implements Listener {

    private final AniVerseWeapons plugin;
    private final WeaponGUI gui;
    private final java.util.Map<Player, Integer> playerPages = new java.util.HashMap<>();

    public GUIListener(AniVerseWeapons plugin, WeaponGUI gui) {
        this.plugin = plugin;
        this.gui = gui;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();

        if (title.contains("AniVerse Weapons")) {
            event.setCancelled(true);
            handleMainMenuClick(event);
        } else if (title.contains("Weapon Collection")) {
            event.setCancelled(true);
            handleWeaponListClick(event, player);
        }
    }

    private void handleMainMenuClick(InventoryClickEvent event) {
        int slot = event.getRawSlot();
        Player player = (Player) event.getWhoClicked();

        switch (slot) {
            case 11: // Weapon Collection
                gui.openWeaponList(player, 0);
                playerPages.put(player, 0);
                break;
            case 49: // Close
                player.closeInventory();
                break;
        }
    }

    private void handleWeaponListClick(InventoryClickEvent event, Player player) {
        int slot = event.getRawSlot();

        if (slot == 47) { // Previous page
            int currentPage = playerPages.getOrDefault(player, 0);
            if (currentPage > 0) {
                currentPage--;
                playerPages.put(player, currentPage);
                gui.openWeaponList(player, currentPage);
            }
        } else if (slot == 51) { // Next page
            int currentPage = playerPages.getOrDefault(player, 0);
            currentPage++;
            playerPages.put(player, currentPage);
            gui.openWeaponList(player, currentPage);
        } else if (slot == 45) { // Back
            gui.openMainMenu(player);
        }
    }
}