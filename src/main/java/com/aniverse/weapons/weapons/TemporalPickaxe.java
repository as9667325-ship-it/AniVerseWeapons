package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TemporalPickaxe extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location center = player.getLocation().add(0, 1, 0);
        double radius = 10.0;

        // Play amethyst sound
        player.getWorld().playSound(center, Sound.BLOCK_AMETHYST_CLUSTER_HIT, 1.0f, 1.0f);

        // Create temporal particles
        for (int i = 0; i < 42; i++) {
            double angle = (Math.PI * 2 / 42) * i;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            player.getWorld().spawnParticle(Particle.WITCH, center.clone().add(x, 0, z), 4);
        }

        // Apply slowness to nearby entities
        for (LivingEntity entity : center.getNearbyLivingEntities(radius, radius, radius)) {
            if (entity != player && entity.isValid()) {
                entity.addPotionEffect(new PotionEffect(
                    PotionEffectType.SLOWNESS,
                    100, // 5 seconds
                    1,   // amplifier level 1
                    true,
                    false
                ));
            }
        }

        // Apply haste to player for the cooldown duration
        player.addPotionEffect(new PotionEffect(
            PotionEffectType.HASTE,
            (int)(cooldown * 20),
            0,
            true,
            false
        ));

        setCooldown(player);
    }
}