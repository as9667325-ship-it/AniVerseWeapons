package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CrystalStaff extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location center = player.getLocation().add(0, 1, 0);
        double radius = 6.0;

        // Play crystal sound
        player.getWorld().playSound(center, Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);

        // Create crystal particles
        for (int i = 0; i < 32; i++) {
            double angle = (Math.PI * 2 / 32) * i;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            player.getWorld().spawnParticle(Particle.END_ROD, center.clone().add(x, 0, z), 3);
        }

        // Apply absorption to nearby players
        for (Player nearbyPlayer : player.getWorld().getPlayers()) {
            if (nearbyPlayer.getLocation().distance(center) <= radius) {
                nearbyPlayer.addPotionEffect(new PotionEffect(
                    PotionEffectType.ABSORPTION,
                    160, // 8 seconds
                    0,
                    true,
                    false
                ));
            }
        }

        setCooldown(player);
    }
}