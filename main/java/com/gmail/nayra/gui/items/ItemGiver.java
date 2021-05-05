package com.gmail.nayra.gui.items;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemGiver {

    public void itemGive(Material m, Player p, String name, String lore){

        ItemStack itemTo = new ItemStack(m, 1);
        ItemMeta editor = itemTo.getItemMeta();
        editor.setDisplayName(name);
        ArrayList<String> LoreCoin = new ArrayList<>();
        LoreCoin.add(lore);
        editor.setLore(LoreCoin);
        itemTo.setItemMeta(editor);
        p.getInventory().addItem(itemTo);
        p.getInventory().remove(p.getItemInHand());
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 4.0F, 0.3F);

    }
}
