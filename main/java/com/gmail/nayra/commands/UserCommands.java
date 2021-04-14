package com.gmail.nayra.commands;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.storage.StorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UserCommands implements CommandExecutor {
    private MonsterHamster plugin;

    public UserCommands(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (player instanceof Player){


            Player sender = (Player)player;
            String prefix = plugin.getMessages().getString("Messages.prefix");
            String noPermission = plugin.getMessages().getString("Messages.noPermission");
            if(sender.hasPermission("monsterhamster.player")){
                String seeStats = plugin.getMessages().getString("Messages.bossPoints");
                UUID searchPoints = sender.getUniqueId();
                Integer pointsNumber = plugin.getPlayerData().getInt("Players."+searchPoints+".bosspoints");
                if(pointsNumber == 0){
                    String noPoints = plugin.getMessages().getString("Messages.noBossPoints");
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noPoints).replaceAll("%player%", sender.getName()));
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + seeStats).replaceAll("%points%", pointsNumber.toString()).replaceAll("%player%", player.getName()));
                }
                }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noPermission));
            }

                return true;
            }
            return true;
        }

}
