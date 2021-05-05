package com.gmail.nayra.property.enchantments;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.property.FurnaceBook;
import com.gmail.nayra.property.InventoryViewver;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class OtherSmeltingEnchantment implements Listener {
    private MonsterHamster plugin;

    public OtherSmeltingEnchantment(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Smelting(BlockBreakEvent event) {
        FileConfiguration config = plugin.getConfig();
        if(config.getString("Config.Enchantments.Smelting.SmeltingEnchantment").equals("true")){
            if(config.getString("Config.Enchantments.Smelting.SmeltBlocks").equals("true")) {

                if (config.getString("Config.Enchantments.Active_Enchantments").equals("true")) {
                    ItemStack nothing = event.getPlayer().getInventory().getItemInMainHand();
                    if (nothing == null || nothing.getType() == Material.AIR) return;

                    if (nothing.getItemMeta().hasLore()) {

                        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("Smelting")) {

                            FurnaceBook fb = new FurnaceBook();

                            fb.getFurnaceMaterial(event.getBlock(), event.getPlayer(), Material.CACTUS, Material.INK_SACK, 2, 2);
                            fb.getFurnaceMaterial(event.getBlock(), event.getPlayer(), Material.SAND, Material.GLASS, 2, 0);

                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

}