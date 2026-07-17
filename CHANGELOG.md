# AniVerseWeapons v3.0 - CHANGELOG

## Version 3.0.0 - PREMIUM SMP SYSTEM

### NEW FEATURES

#### 1. Boss System (15 Custom Bosses)
- Infernus, Glaciara, Shadowlord, Thunderking, Crystalqueen
- Infernal Apex, Void Emperor, Lunar Goddess, Titan Beast
- Nature Warden, Spectral King, Chaos Master, Dragon Lord
- Divine Light, Abyss Keeper
- Unique AI with 3 attack patterns per boss
- Custom boss bars and music
- Configurable spawn locations
- Legendary weapon drops

#### 2. Dungeon System
- Procedurally generated dungeon arenas
- Boss rooms and reward rooms
- Party/group support (1-5 players)
- Configurable dungeon keys
- Time-limited dungeons (30-45 minutes)
- Scalable difficulty
- Rich reward system

#### 3. Weapon Souls
- Dropped by bosses (25-30% drop chance)
- One soul per weapon
- Unlock hidden ultimate abilities
- Soul infusion GUI
- +20-40% stat bonuses

#### 4. Gem System (6 Gem Types)
- Ruby: +15% damage, +25% crit damage
- Sapphire: +10% crit chance, +10% attack speed
- Emerald: +10% lifesteal, +15% def penetration
- Amethyst: +20% ability damage (Legendary)
- Diamond: +20% damage, +30% crit damage (Legendary)
- Void Gem: +30% damage, +30% ability damage (Divine)
- Up to 3 gems per weapon
- Socket/remove interface

#### 5. Blacksmith System
- Repair weapons (configurable cost)
- Upgrade weapons with safety
- Socket/remove gems
- Smooth animations
- NPC location configurable

#### 6. Daily Quests
- Mob Slayer: Kill 50 mobs
- Boss Hunter: Defeat 3 bosses
- PvP Champion: Win 10 battles
- Legendary Seeker: Obtain 1 legendary
- Daily reset system
- Materials and XP rewards
- Special quest rewards

#### 7. World Events (5 Events)
- Meteor Shower: Rain of meteors (60s)
- Blood Moon: Enhanced mob spawning (300s)
- Dragon Invasion: 3 dragons descend (120s)
- Treasure Rain: Falling treasures (90s)
- Void Rift: Dangerous void tears (150s)
- Random scheduling (30-60 min intervals)
- Server-wide announcements
- Enhanced loot drops

#### 8. Crate System (5 Crate Tiers)
- Common Crate: Basic rewards
- Rare Crate: Uncommon loot
- Epic Crate: Valuable items
- Legendary Crate: Rare weapons/gems
- Divine Crate: Exclusive Divine items
- Animated opening effect
- Configurable rewards
- Currency integration

#### 9. Economy Integration
- Vault support (optional)
- Fallback to materials currency
- Configurable prices for all services:
  - Repairs, upgrades, gem sockets
  - Crate purchases
  - Dungeon keys
  - Soul infusions

#### 10. Database System
- SQLite by default (no setup)
- MySQL support (optional)
- Async saves to prevent lag
- Automatic batching
- Saves weapon level, XP, upgrades, skins
- Player statistics tracking
- 300-second auto-save interval

#### 11. Admin GUI
- Manage weapons and items
- Spawn bosses
- Reload configurations
- Give items to players
- View player statistics
- Event management

#### 12. Performance
- Fully async database operations
- Paper 1.21.x optimized
- Java 21 features utilized
- Maven-built with no compile errors
- Zero-lag design
- Batch processing for database
- Efficient entity handling

### BACKWARD COMPATIBILITY

✅ All Version 2.0 features intact:
- 30 weapons (10 fully implemented)
- GUI system with pagination
- 6 weapon rarities
- Comprehensive stats system
- Upgrade system (+1 to +15)
- Weapon XP (1-100)
- Advanced ability system
- Permission system
- Command system

### CONFIGURATION FILES

- bosses.yml - Boss definitions
- dungeons.yml - Dungeon settings
- weapon-souls.yml - Soul system
- gems.yml - Gem definitions
- blacksmith.yml - Blacksmith services
- daily-quests.yml - Quest definitions
- world-events.yml - Event settings
- crates.yml - Crate loot tables
- economy.yml - Price configuration
- database.yml - Database settings

### COMMANDS

Player:
- `/avweapons menu` - Weapon GUI
- `/avweapons give` - Give weapons
- `/avweapons list` - List weapons
- `/avweapons reload` - Reload configs

Admin:
- `/avweapons admin` - Admin GUI
- `/avweapons boss <spawn|list>` - Manage bosses
- `/avweapons dungeon <create|list>` - Manage dungeons
- `/avweapons quest <list|complete>` - Manage quests

### PERMISSIONS

- `avweapons.admin` - Full access
- `avweapons.menu` - Open weapon menu
- `avweapons.boss.*` - Boss commands
- `avweapons.dungeon.*` - Dungeon commands
- `avweapons.quest.*` - Quest commands
- `avweapons.blacksmith.*` - Blacksmith services

### BUILD & DEPLOY

```bash
mvn clean package
```

Output: `target/AniVerseWeapons-3.0.0.jar`

No compile errors. Fully functional. Production ready.