package com.aniverse.weapons.commands;

import com.aniverse.weapons.AniVerseWeapons;
import com.aniverse.weapons.gui.WeaponGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {

    private final AniVerseWeapons plugin;
    private final WeaponGUI gui;

    public MenuCommand(AniVerseWeapons plugin, WeaponGUI gui) {
        this.plugin = plugin;
        this.gui = gui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        if (!player.hasPermission("avweapons.menu")) {
            player.sendMessage("§c[AniVerseWeapons] You don't have permission to use this command!");
            return true;
        }

        gui.openMainMenu(player);
        return true;
    }
}