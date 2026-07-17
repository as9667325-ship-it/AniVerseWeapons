package com.aniverse.weapons.commands;

import com.aniverse.weapons.AniVerseWeapons;
import com.aniverse.weapons.admin.AdminGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommand implements CommandExecutor {

    private final AniVerseWeapons plugin;
    private final AdminGUI adminGUI;

    public AdminCommand(AniVerseWeapons plugin, AdminGUI adminGUI) {
        this.plugin = plugin;
        this.adminGUI = adminGUI;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("&cOnly players can use this command!");
            return true;
        }

        if (!player.hasPermission("avweapons.admin")) {
            player.sendMessage("&c[AniVerseWeapons] You don't have permission!");
            return true;
        }

        adminGUI.openAdminMenu(player);
        return true;
    }
}