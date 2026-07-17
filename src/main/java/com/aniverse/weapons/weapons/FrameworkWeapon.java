package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;

public class FrameworkWeapon extends CustomWeapon {

    private String weaponName;

    public FrameworkWeapon(String weaponName) {
        this.weaponName = weaponName;
    }

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location center = player.getLocation().add(0, 1, 0);

        // Basic placeholder effect
        player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, center, 20);
        player.getWorld().playSound(center, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);

        player.sendMessage("\u00a7e[AniVerseWeapons] \u00a77" + displayName + " ability used (Framework - not yet implemented)");

        setCooldown(player);
    }
}