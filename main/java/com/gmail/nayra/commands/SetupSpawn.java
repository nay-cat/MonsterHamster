package com.gmail.nayra.commands;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.boss.SetSpawn;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupSpawn implements CommandExecutor {
    private MonsterHamster plugin;

    public SetupSpawn(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player s = (Player)sender;
            if(s.hasPermission("monsterhamster.admin")){
                SetSpawn sp = new SetSpawn();
                sp.setSpawnSetup(s.getWorld(), s.getLocation());
                s.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+plugin.getMessages().getString("Messages.selectSpawnPoint")));
            }
        }
        return false;
    }
}
