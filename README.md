# AniVerseWeapons

A comprehensive custom weapons plugin for Paper 1.21.x with advanced particle effects, sounds, and abilities.

## Features

- **30 Custom Weapons**: 10 fully implemented with unique abilities, 20 framework weapons ready for implementation
- **Unique Abilities**: Each weapon has right-click abilities with cooldowns
- **Particle Effects**: Beautiful visual effects for each weapon ability
- **Sound Effects**: Custom audio feedback for weapon usage
- **Custom Model Data**: Support for custom textures via resource packs
- **Configuration**: YAML-based configuration for easy customization
- **Permission System**: Full permission support for weapon usage
- **Commands**:
  - `/avweapons give <player> <weapon>` - Give weapons to players
  - `/avweapons list` - List all available weapons
  - `/avweapons reload` - Reload configuration

## Fully Implemented Weapons

1. **Phoenix Blade** - Fire burst that ignites enemies
2. **Frost Staff** - Freeze wave that applies slowness
3. **Shadow Dagger** - Dash ability for quick movement and damage
4. **Lightning Hammer** - Strike that knockbacks nearby entities
5. **Inferno Bow** - Fire arrows with ignite effect
6. **Void Scythe** - Pull enemies closer ability
7. **Crystal Staff** - Barrier that grants absorption
8. **Obsidian Axe** - Ground shatter with knockback
9. **Celestial Sword** - Slash that heals the user
10. **Temporal Pickaxe** - Time slow effect

## Framework Weapons (20)

11. Infernal Mace
12. Aquatic Trident
13. Forest Bow
14. Titan Sword
15. Spectral Staff
16. Dragon Claw
17. Cursed Scythe
18. Holy Mace
19. Storm Staff
20. Void Blade
21. Solar Bow
22. Lunar Dagger
23. Magma Hammer
24. Glacier Staff
25. Shadowflame Sword
26. Nature Staff
27. Chaos Axe
28. Cosmic Blade
29. Ethereal Bow
30. Abyssal Reaper

## Building

```bash
mvn clean package
```

The compiled JAR will be in `target/AniVerseWeapons-1.0.0.jar`

## Installation

1. Build the plugin: `mvn clean package`
2. Copy the JAR to your Paper server's `plugins` folder
3. Restart the server
4. Configure weapons in `plugins/AniVerseWeapons/weapons/weapons.yml`
5. Use `/avweapons give <player> <weapon>` to give weapons

## Configuration

Edit `plugins/AniVerseWeapons/weapons/weapons.yml` to customize:
- Weapon display names
- Damage values
- Cooldowns
- Particle effects
- Sounds
- Ability parameters

## Requirements

- Java 21
- Paper 1.21.x
- Maven 3.8+

## License

AniVerse © 2024