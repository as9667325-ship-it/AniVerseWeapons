package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class PhoenixBlade extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location center = player.getLocation().add(0, 1, 0);
        double radius = 8.0;
        double damage = 15.0;

        // Play sound
        player.getWorld().playSound(center, Sound.ITEM_FIRECHARGE_USE, 1.0f, 1.0f);

        // Create fire burst particles
        for (int i = 0; i < 20; i++) {
            double angle = (Math.PI * 2 / 20) * i;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            player.getWorld().spawnParticle(Particle.FLAME, center.clone().add(x, 0, z), 5);
        }

        // Damage nearby enemies
        for (LivingEntity entity : center.getNearbyLivingEntities(radius, radius, radius)) {
            if (entity != player && entity.isValid()) {
                entity.damage(damage, player);
                entity.setFireTicks(60); // 3 seconds at 20 ticks/second
                entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);
            }
        }

        setCooldown(player);
    }
}