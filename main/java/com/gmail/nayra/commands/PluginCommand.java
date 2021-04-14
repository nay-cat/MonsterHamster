package com.gmail.nayra.commands;

import com.gmail.nayra.MonsterHamster;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginCommand implements CommandExecutor {
    private MonsterHamster plugin;

    public PluginCommand(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (player instanceof Player){


        Player sender = (Player)player;
        String prefix = plugin.getMessages().getString("Messages.prefix");
        String noPermission = plugin.getMessages().getString("Messages.noPermission");

        if (args.length == 0) {
            if (sender.hasPermission("monsterhamster.admin")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Test"));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noPermission));
            }
        } else {
            switch (args[0]) {
                case "reload":
                    if (sender.hasPermission("monsterhamster.admin")) {

                        String reloadMessage = plugin.getMessages().getString("Messages.reload");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+reloadMessage));
                        this.plugin.reloadConfig();
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noPermission));
                    }
                    return true;
            }
            String unkown = plugin.getMessages().getString("Messages.unkown");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+unkown));
            return true;
        }
        return true;
    }
        return true;
    }

    }

