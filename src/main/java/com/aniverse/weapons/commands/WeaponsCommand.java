package com.aniverse.weapons.commands;

import com.aniverse.weapons.AniVerseWeapons;
import com.aniverse.weapons.managers.WeaponManager;
import com.aniverse.weapons.managers.ConfigManager;
import com.aniverse.weapons.weapons.CustomWeapon;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeaponsCommand implements CommandExecutor {

    private final AniVerseWeapons plugin;
    private final WeaponManager weaponManager;
    private final ConfigManager configManager;

    public WeaponsCommand(AniVerseWeapons plugin) {
        this.plugin = plugin;
        this.weaponManager = plugin.getWeaponManager();
        this.configManager = plugin.getConfigManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendUsage(sender);
            return true;
        }

        String subcommand = args[0].toLowerCase();

        switch (subcommand) {
            case "give":
                return handleGive(sender, args);
            case "reload":
                return handleReload(sender);
            case "list":
                return handleList(sender);
            default:
                sender.sendMessage("\u00a7c[AniVerseWeapons] Unknown subcommand. Use \u00a7e/avweapons list \u00a7cfor help.");
                return true;
        }
    }

    private boolean handleGive(CommandSender sender, String[] args) {
        if (!sender.hasPermission("avweapons.admin") && !sender.hasPermission("avweapons.give")) {
            sender.sendMessage("\u00a7c[AniVerseWeapons] You don't have permission to use this command!");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage("\u00a7c[AniVerseWeapons] Usage: /avweapons give <player> <weapon>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage("\u00a7c[AniVerseWeapons] Player \u00a7e" + args[1] + "\u00a7c not found!");
            return true;
        }

        String weaponId = args[2].toLowerCase();
        CustomWeapon weapon = weaponManager.getWeapon(weaponId);

        if (weapon == null) {
            sender.sendMessage("\u00a7c[AniVerseWeapons] Weapon \u00a7e" + args[2] + "\u00a7c not found!");
            return true;
        }

        target.getInventory().addItem(weapon.createItem());
        sender.sendMessage("\u00a7a[AniVerseWeapons] You have given \u00a7e" + weapon.getDisplayName() + "\u00a7a to \u00a7e" + target.getName() + "\u00a7a!");
        target.sendMessage("\u00a7a[AniVerseWeapons] \u00a7e" + sender.getName() + "\u00a7a has given you \u00a7e" + weapon.getDisplayName() + "\u00a7a!");

        return true;
    }

    private boolean handleReload(CommandSender sender) {
        if (!sender.hasPermission("avweapons.admin") && !sender.hasPermission("avweapons.reload")) {
            sender.sendMessage("\u00a7c[AniVerseWeapons] You don't have permission to use this command!");
            return true;
        }

        configManager.reloadConfigs();
        weaponManager.loadWeapons();
        sender.sendMessage("\u00a7a[AniVerseWeapons] Configuration reloaded successfully!");

        return true;
    }

    private boolean handleList(CommandSender sender) {
        if (!sender.hasPermission("avweapons.admin") && !sender.hasPermission("avweapons.list")) {
            sender.sendMessage("\u00a7c[AniVerseWeapons] You don't have permission to use this command!");
            return true;
        }

        sender.sendMessage("\u00a7b[AniVerseWeapons] \u00a7eAvailable Weapons:");
        for (CustomWeapon weapon : weaponManager.getAllWeapons().values()) {
            sender.sendMessage("\u00a7b[AniVerseWeapons] \u00a77- \u00a7e" + weapon.getDisplayName() + 
                "\u00a77 (" + weapon.getDamage() + " damage, " + weapon.getCooldown() + "s cooldown)");
        }

        return true;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage("\u00a7b[AniVerseWeapons] \u00a7eAniVerseWeapons Commands:");
        sender.sendMessage("\u00a7b[AniVerseWeapons] \u00a7e/avweapons give <player> <weapon> \u00a77- Give a weapon to a player");
        sender.sendMessage("\u00a7b[AniVerseWeapons] \u00a7e/avweapons list \u00a77- List all available weapons");
        sender.sendMessage("\u00a7b[AniVerseWeapons] \u00a7e/avweapons reload \u00a77- Reload configuration files");
    }
}