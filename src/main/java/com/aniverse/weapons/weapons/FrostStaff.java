package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FrostStaff extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location center = player.getLocation().add(0, 1, 0);
        double radius = 12.0;

        // Play sound
        player.getWorld().playSound(center, Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);

        // Create frost wave particles
        for (int i = 0; i < 30; i++) {
            double angle = (Math.PI * 2 / 30) * i;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            player.getWorld().spawnParticle(Particle.CLOUD, center.clone().add(x, 0, z), 10);
        }

        // Apply slowness to nearby entities
        for (LivingEntity entity : center.getNearbyLivingEntities(radius, radius, radius)) {
            if (entity != player && entity.isValid()) {
                entity.addPotionEffect(new PotionEffect(
                    PotionEffectType.SLOWNESS,
                    80, // 4 seconds at 20 ticks/second
                    2,  // amplifier level 2
                    true,
                    false
                ));
                entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_GLASS_PLACE, 1.0f, 1.0f);
            }
        }

        setCooldown(player);
    }
}