package com.gmail.nayra.property;

import org.bukkit.entity.Player;

public class InventoryViewver {

    public int checkFull(Player sender)
    {
        int full = sender.getInventory().firstEmpty();
        return full;
    }

}
