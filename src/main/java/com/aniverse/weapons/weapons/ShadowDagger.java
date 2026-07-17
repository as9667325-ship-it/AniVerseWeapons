package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class ShadowDagger extends CustomWeapon {

    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7c[AniVerseWeapons] Weapon on cooldown for \u00a7e" + String.format("%.1f", remaining) + "s\u00a7c!");
            return;
        }

        Location startLoc = player.getLocation().add(0, 1, 0);
        Vector direction = player.getLocation().getDirection().normalize();
        double distance = 15.0;
        double damage = 20.0;

        // Play teleport sound
        player.getWorld().playSound(startLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);

        // Create smoke trail
        for (int i = 0; i < distance; i++) {
            Location loc = startLoc.clone().add(direction.clone().multiply(i));
            player.getWorld().spawnParticle(Particle.SMOKE, loc, 8);
        }

        // Teleport player
        Location endLoc = startLoc.clone().add(direction.clone().multiply(distance));
        player.teleport(endLoc);

        // Play hit sound
        player.getWorld().playSound(endLoc, Sound.ENTITY_ARROW_HIT_PLAYER, 1.0f, 1.0f);

        // Damage nearby entities at destination
        for (LivingEntity entity : endLoc.getNearbyLivingEntities(5, 5, 5)) {
            if (entity != player && entity.isValid()) {
                entity.damage(damage, player);
            }
        }

        setCooldown(player);
    }
}