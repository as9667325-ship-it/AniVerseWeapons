package com.aniverse.weapons.crates;

import org.bukkit.entity.Player;
import java.util.*;

public class CrateManager {

    private final Map<String, Crate> crates = new HashMap<>();

    public void registerCrate(Crate crate) {
        crates.put(crate.getId(), crate);
    }

    public Crate getCrate(String id) {
        return crates.get(id);
    }

    public void openCrate(Player player, Crate crate) {
        // TODO: Play animation
        // TODO: Award random item
    }

    public Collection<Crate> getAllCrates() {
        return crates.values();
    }
}