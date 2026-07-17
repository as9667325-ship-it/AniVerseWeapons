package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;

public class CelestialSword extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location center = player.getLocation().add(0, 1, 0);
        double radius = 9.0;
        double damage = 19.0;
        double healAmount = 6.0;

        // Play enchantment sound
        player.getWorld().playSound(center, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);

        // Create celestial particles
        for (int i = 0; i < 38; i++) {
            double angle = (Math.PI * 2 / 38) * i;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, center.clone().add(x, 1, z), 3);
        }

        // Damage enemies and heal player
        for (LivingEntity entity : center.getNearbyLivingEntities(radius, radius, radius)) {
            if (entity != player && entity.isValid()) {
                entity.damage(damage, player);
                player.heal(Math.min(healAmount, player.getMaxHealth() - player.getHealth()));
                entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.0f);
            }
        }

        setCooldown(player);
    }
}