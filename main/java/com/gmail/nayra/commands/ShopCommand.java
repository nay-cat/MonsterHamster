package com.gmail.nayra.commands;

import com.gmail.nayra.MonsterHamster;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    private MonsterHamster plugin;
    public ShopCommand(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            p.openInventory(plugin.shop);
            p.sendMessage("Trying");

        }

        return false;
    }
}
