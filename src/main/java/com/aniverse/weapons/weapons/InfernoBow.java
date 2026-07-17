package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.entity.Arrow;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerInteractEvent;

public class InfernoBow extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        // Play bow fire sound
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_BOW_FIRE, 1.0f, 1.0f);

        // Create flame particles at player
        for (int i = 0; i < 35; i++) {
            player.getWorld().spawnParticle(Particle.FLAME, player.getEyeLocation(), 5);
        }

        setCooldown(player);
    }
}