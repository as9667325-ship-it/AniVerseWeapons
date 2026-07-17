# Development Guide - AniVerseWeapons

## Project Structure

```
AniVerseWeapons/
├── src/main/java/com/aniverse/weapons/
│   ├── AniVerseWeapons.java              # Main plugin class
│   ├── managers/
│   │   ├── WeaponManager.java            # Weapon loading and management
│   │   └── ConfigManager.java            # Configuration management
│   ├── weapons/
│   │   ├── CustomWeapon.java             # Base weapon class
│   │   ├── PhoenixBlade.java             # Fully implemented weapons
│   │   ├── FrostStaff.java
│   │   ├── ShadowDagger.java
│   │   ├── LightningHammer.java
│   │   ├── InfernoBow.java
│   │   ├── VoidScythe.java
│   │   ├── CrystalStaff.java
│   │   ├── ObsidianAxe.java
│   │   ├── CelestialSword.java
│   │   ├── TemporalPickaxe.java
│   │   └── FrameworkWeapon.java          # Template for new weapons
│   ├── listeners/
│   │   └── WeaponListener.java           # Event listeners
│   └── commands/
│       └── WeaponsCommand.java           # Command executor
├── src/main/resources/
│   ├── plugin.yml                        # Plugin metadata
│   ├── config.yml                        # Main configuration
│   └── weapons/weapons.yml               # Weapon definitions
└── pom.xml                               # Maven configuration
```

## Adding a New Weapon

### Step 1: Create Weapon Class

Create a new file in `src/main/java/com/aniverse/weapons/weapons/` extending `CustomWeapon`:

```java
package com.aniverse.weapons.weapons;

import org.bukkit.entity.Player;

public class MyWeapon extends CustomWeapon {
    @Override
    public void onRightClick(Player player) {
        if (isOnCooldown(player)) {
            double remaining = getRemainingCooldown(player);
            player.sendMessage("\u00a7cOn cooldown for \u00a7e" + String.format("%.1f", remaining) + "s");
            return;
        }
        
        // Implement ability here
        
        setCooldown(player);
    }
}
```

### Step 2: Add to WeaponManager

In `WeaponManager.java`, add your weapon to the switch statement:

```java
case "my_weapon_id" -> new MyWeapon();
```

### Step 3: Add Configuration

Add weapon configuration to `weapons/weapons.yml`:

```yaml
my_weapon_id:
  display-name: "&6My Weapon"
  material: "DIAMOND_SWORD"
  custom-model-data: 1031
  damage: 12.0
  cooldown: 2.5
  description:
    - "&7My awesome weapon"
  particles:
    ability: "FLAME"
    ability-count: 20
  sounds:
    use: "ITEM_FIRECHARGE_USE"
    hit: "ENTITY_ARROW_HIT"
  ability:
    type: "my_ability"
```

## Useful Classes and Methods

### Particles

```java
player.getWorld().spawnParticle(Particle.FLAME, location, count);
```

### Sounds

```java
player.getWorld().playSound(location, Sound.ITEM_FIRECHARGE_USE, volume, pitch);
```

### Damage

```java
entity.damage(amount, player);
entity.knockback(strength, originX, originZ);
```

### Potion Effects

```java
entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, amplifier));
```

## Testing

1. Build: `mvn clean package`
2. Copy JAR to test server
3. Restart server
4. Use `/avweapons give @s <weapon_id>`
5. Right-click with weapon

## Common Issues

- **Weapon not appearing**: Check custom model data in configuration
- **Ability not working**: Verify weapon class implements `onRightClick`
- **Particles not showing**: Check particle names in `org.bukkit.Particle` enum
- **Sounds not playing**: Verify sound names in `org.bukkit.Sound` enum