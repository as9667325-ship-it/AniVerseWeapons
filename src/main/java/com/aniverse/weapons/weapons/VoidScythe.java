package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VoidScythe extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location center = player.getLocation().add(0, 1, 0);
        double radius = 14.0;
        double pullStrength = 2.0;
        double damage = 16.0;

        // Play void sound
        player.getWorld().playSound(center, Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f);

        // Create void particles
        for (int i = 0; i < 28; i++) {
            double angle = (Math.PI * 2 / 28) * i;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, center.clone().add(x, 0, z), 5);
        }

        // Pull and damage nearby entities
        for (LivingEntity entity : center.getNearbyLivingEntities(radius, radius, radius)) {
            if (entity != player && entity.isValid()) {
                // Pull towards player
                Vector direction = player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize();
                entity.setVelocity(direction.multiply(pullStrength));
                entity.damage(damage, player);
                entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1.0f, 1.0f);
            }
        }

        setCooldown(player);
    }
}