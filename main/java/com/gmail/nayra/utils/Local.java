package com.gmail.nayra.utils;

import com.gmail.nayra.MonsterHamster;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Local {
    protected MonsterHamster plugin;
    public Local(MonsterHamster plugin) {
        this.plugin = plugin;
    }


    public static void log(String msg){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[MonsterHamster] "+msg));
    }

    public static void bdc(String msg){
        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }


}
