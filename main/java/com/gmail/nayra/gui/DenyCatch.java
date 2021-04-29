package com.gmail.nayra.gui;

import com.gmail.nayra.MonsterHamster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class DenyCatch implements Listener {
    private MonsterHamster plugin;

    public DenyCatch(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void denyDrag(InventoryDragEvent event) {
        Inventory shopGui = plugin.shop;
            event.setCancelled(true);
        }

}
