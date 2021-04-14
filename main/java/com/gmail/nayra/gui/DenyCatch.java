package com.gmail.nayra.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class DenyCatch implements Listener {

    @EventHandler
    public void denyDrag(final InventoryDragEvent e) {
        String guiName = e.getInventory().getTitle();
        if(guiName.equals("MonsterHamster - Shop")) {
            e.setCancelled(true);
        }
    }

}
