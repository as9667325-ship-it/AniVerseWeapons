package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;

public class LightningHammer extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location center = player.getLocation().add(0, 1, 0);
        double radius = 10.0;
        double damage = 18.0;
        double knockback = 3.0;

        // Play thunder sound
        player.getWorld().playSound(center, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);

        // Create lightning bolt
        for (int i = 0; i < 40; i++) {
            double angle = (Math.PI * 2 / 40) * i;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, center.clone().add(x, 2, z), 3);
        }

        // Damage and knockback nearby entities
        for (LivingEntity entity : center.getNearbyLivingEntities(radius, radius, radius)) {
            if (entity != player && entity.isValid()) {
                entity.damage(damage, player);
                entity.knockback(knockback, player.getLocation().getX(), player.getLocation().getZ());
                entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0f, 1.0f);
            }
        }

        setCooldown(player);
    }
}