package com.aniverse.weapons.quests;

import org.bukkit.entity.Player;
import java.util.*;

public class QuestManager {

    private final Map<Player, List<DailyQuest>> playerQuests = new HashMap<>();

    public void assignDailyQuests(Player player, List<DailyQuest> quests) {
        playerQuests.put(player, new ArrayList<>(quests));
    }

    public List<DailyQuest> getPlayerQuests(Player player) {
        return playerQuests.getOrDefault(player, new ArrayList<>());
    }

    public void completeQuest(Player player, String questId) {
        List<DailyQuest> quests = getPlayerQuests(player);
        for (DailyQuest quest : quests) {
            if (quest.getId().equals(questId) && quest.isComplete()) {
                // TODO: Award rewards
                quests.remove(quest);
                break;
            }
        }
    }

    public void updateQuests(Player player, String type, int amount) {
        List<DailyQuest> quests = getPlayerQuests(player);
        for (DailyQuest quest : quests) {
            if (quest.getId().contains(type)) {
                quest.addProgress(amount);
                if (quest.isComplete()) {
                    // TODO: Notify player of completion
                }
            }
        }
    }

    public void resetDaily(Player player) {
        playerQuests.put(player, new ArrayList<>());
    }
}