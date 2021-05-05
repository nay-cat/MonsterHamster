package com.gmail.nayra.commands;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.utils.EnchantmentList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class EnchantCommand implements CommandExecutor {
    private MonsterHamster plugin;
    public EnchantCommand(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getMessages().getString("Messages.noParameters")));

            }else{
                Player p = (Player) sender;
                if (p.hasPermission("monsterhamster.admin")) {
                    EnchantmentList ec = new EnchantmentList();
                    if (ec.enchantmentsList().contains(args[0])) {
                        ItemStack selectItem = p.getInventory().getItemInMainHand();
                        ItemMeta selectEnchantmentLore = selectItem.getItemMeta();
                        ArrayList<String> Lore = new ArrayList<>();
                        Lore.add(args[0]);
                        String msgEnchant = plugin.getMessages().getString("Messages.enchantedItem"), prefix = plugin.getMessages().getString("Messages.prefix");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + msgEnchant));
                        selectEnchantmentLore.setLore(Lore);
                        selectItem.setItemMeta(selectEnchantmentLore);
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getMessages().getString("Messages.unkownEnchantment")));
                    }
                }
            }
        }
        return false;
    }

}


