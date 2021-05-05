package com.gmail.nayra.gui.items;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.storage.StorageUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class ShopManager {

    private MonsterHamster plugin;
    public ShopManager(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    public void fastShop(String itemName, Material mt, String pricePath, Player p, String lore){
        StorageUtil su = new StorageUtil();
        int price = plugin.getConfig().getInt(pricePath);
        if(su.getPoints(p.getUniqueId()) >= price){
            StorageUtil.addPoints(p.getUniqueId(), -price);
            ItemStack itemToShop = new ItemStack(mt, 1);
            ItemMeta editItem = itemToShop.getItemMeta();
            editItem.setDisplayName(itemName);
            ArrayList<String> LoreCoin = new ArrayList<>();
            LoreCoin.add(lore);
            editItem.setLore(LoreCoin);
            itemToShop.setItemMeta(editItem);
            p.getInventory().addItem(itemToShop);
        }
    }
}
