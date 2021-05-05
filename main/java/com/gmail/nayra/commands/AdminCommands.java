package com.gmail.nayra.commands;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.storage.StorageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {

    private MonsterHamster plugin;

    public AdminCommands(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("monsterhamster.admin")){

            if(args.length == 0 || args.length == 1){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+"Invalid parameters, /mmodify set|add|remove <player> <number>"));

            }else {
                Player target = Bukkit.getPlayer(args[1]);
                int number = Integer.parseInt(args[2]);
                switch (args[0]) {

                    case "set":
                        StorageUtil.setPoints(target, (number));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+"Setted points of: "+target.getName()+" on"+number));
                        break;

                    case "add":
                        StorageUtil.addPoints(target.getUniqueId(), (number));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+"Added points to: "+target.getName()+" +"+number));

                        break;

                    case "remove":
                        StorageUtil.addPoints(target.getUniqueId(), -(number));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+"Removed points: "+target.getName()+" -"+number));

                        break;
                }
            }
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+plugin.getMessages().getString("Messages.noPermission")));
        }


        return false;
    }
}
