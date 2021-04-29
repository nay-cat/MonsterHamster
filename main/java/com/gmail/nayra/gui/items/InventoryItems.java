package com.gmail.nayra.gui.items;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InventoryItems {

    public ItemStack createItem(final Material item_material, final String item_name, final String... lore) {
        ItemStack inventoryItem = new ItemStack(item_material, 1);
        ItemMeta meta = inventoryItem.getItemMeta();
        meta.setDisplayName(item_name);
        meta.setLore(Arrays.asList(lore));
        inventoryItem.setItemMeta(meta);
        return inventoryItem;
    }

}
