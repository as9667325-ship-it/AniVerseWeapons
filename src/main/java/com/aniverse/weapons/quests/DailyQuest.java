package com.aniverse.weapons.quests;

import org.bukkit.entity.Player;

public class DailyQuest {

    private final String id;
    private final String name;
    private final String description;
    private final int target;
    private final int rewardMaterials;
    private final int rewardXP;
    private final String specialReward; // Optional
    private int progress = 0;

    public DailyQuest(String id, String name, String description, int target,
                     int rewardMaterials, int rewardXP, String specialReward) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.target = target;
        this.rewardMaterials = rewardMaterials;
        this.rewardXP = rewardXP;
        this.specialReward = specialReward;
    }

    public void addProgress(int amount) {
        this.progress = Math.min(progress + amount, target);
    }

    public boolean isComplete() {
        return progress >= target;
    }

    public double getProgressPercentage() {
        return (progress / (double) target) * 100;
    }

    public void reset() {
        this.progress = 0;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getTarget() { return target; }
    public int getProgress() { return progress; }
    public int getRewardMaterials() { return rewardMaterials; }
    public int getRewardXP() { return rewardXP; }
    public String getSpecialReward() { return specialReward; }
}