package com.aniverse.weapons.crates;

public class Crate {

    private final String id;
    private final String displayName;
    private final int animationDuration;
    private final String[] items;
    private final int[] rewards;

    public Crate(String id, String displayName, int animationDuration, 
                String[] items, int[] rewards) {
        this.id = id;
        this.displayName = displayName;
        this.animationDuration = animationDuration;
        this.items = items;
        this.rewards = rewards;
    }

    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public int getAnimationDuration() { return animationDuration; }
    public String[] getItems() { return items; }
    public int[] getRewards() { return rewards; }

    public String getRandomItem() {
        return items[(int)(Math.random() * items.length)];
    }
}