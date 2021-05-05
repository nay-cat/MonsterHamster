package com.gmail.nayra.commands;

import com.gmail.nayra.MonsterHamster;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public class ShopCommand implements CommandExecutor {

    private MonsterHamster plugin;
    public ShopCommand(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
        if(p.hasPermission("monsterhamster.shop")){
            p.openInventory(plugin.shop);

        }else{
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+plugin.getMessages().getString("Messages.noPermission")));
        }


        }

        return false;
    }
}
