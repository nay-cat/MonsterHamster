package com.gmail.nayra.gui;

import com.gmail.nayra.MonsterHamster;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class DenyCatch implements Listener {
    private MonsterHamster plugin;

    public DenyCatch(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void denyDrag(InventoryClickEvent event) {
        if (event.getInventory().getName().equals("MonsterShop")) {
            if (event.getCurrentItem() == null) {
                event.setCancelled(true);
            }
            event.setCancelled(true);
        }
    }


}
