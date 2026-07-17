package com.aniverse.weapons.listeners;

import com.aniverse.weapons.AniVerseWeapons;
import com.aniverse.weapons.weapons.CustomWeapon;
import com.aniverse.weapons.managers.WeaponManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WeaponListener implements Listener {

    private final AniVerseWeapons plugin;
    private final WeaponManager weaponManager;

    public WeaponListener(AniVerseWeapons plugin) {
        this.plugin = plugin;
        this.weaponManager = plugin.getWeaponManager();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null || item.getItemMeta() == null) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (!meta.hasCustomModelData()) {
            return;
        }

        int customModelData = meta.getCustomModelData();

        // Find weapon by custom model data
        for (CustomWeapon weapon : weaponManager.getAllWeapons().values()) {
            if (weapon.getCustomModelData() == customModelData) {
                // Check permission
                if (!player.hasPermission("avweapons.use.*") && 
                    !player.hasPermission("avweapons.use." + weapon.getWeaponId())) {
                    player.sendMessage("\u00a7c[AniVerseWeapons] You don't have permission to use this weapon!");
                    return;
                }

                weapon.onRightClick(player);
                event.setCancelled(true);
                return;
            }
        }
    }
}