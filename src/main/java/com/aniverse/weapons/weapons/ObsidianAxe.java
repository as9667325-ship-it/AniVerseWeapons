package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;

public class ObsidianAxe extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location center = player.getLocation().add(0, 1, 0);
        double radius = 11.0;
        double damage = 17.0;
        double knockback = 2.5;

        // Play stone break sound
        player.getWorld().playSound(center, Sound.BLOCK_STONE_BREAK, 1.0f, 1.0f);

        // Create shatter particles
        for (int i = 0; i < 36; i++) {
            double angle = (Math.PI * 2 / 36) * i;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            player.getWorld().spawnParticle(Particle.BLOCK_DUST, center.clone().add(x, 0, z), 5);
        }

        // Damage and knockback nearby entities
        for (LivingEntity entity : center.getNearbyLivingEntities(radius, radius, radius)) {
            if (entity != player && entity.isValid()) {
                entity.damage(damage, player);
                entity.knockback(knockback, player.getLocation().getX(), player.getLocation().getZ());
                entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_STONE_PLACE, 1.0f, 1.0f);
            }
        }

        setCooldown(player);
    }
}