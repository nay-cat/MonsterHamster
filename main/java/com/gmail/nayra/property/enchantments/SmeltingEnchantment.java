package com.gmail.nayra.property.enchantments;

import com.gmail.nayra.MonsterHamster;
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

public class SmeltingEnchantment implements Listener {
    private MonsterHamster plugin;

    public SmeltingEnchantment(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Smelting(BlockBreakEvent event){
        FileConfiguration config = plugin.getConfig();
        if(config.getString("Config.Enchantments.Smelting.SmeltingEnchantment").equals("true")){
        if(config.getString("Config.Enchantments.Smelting.SmeltOres").equals("true")){
        if(config.getString("Config.Enchantments.Active_Enchantments").equals("true")) {
            ItemStack nothing = event.getPlayer().getInventory().getItemInMainHand();
            if (nothing == null || nothing.getType() == Material.AIR) return;
            if (nothing.getItemMeta().hasLore()) {
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("Smelting")) {
            /*
            Optimizar esta parte
             */
                    InventoryViewver iv = new InventoryViewver();
                    int check = iv.checkFull(event.getPlayer());
                    if (event.getBlock().getType().equals(Material.IRON_ORE)) {
                        if (check == -1) {
                            Player eplayer = (Player) event.getPlayer();
                            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                event.getBlock().setType(Material.AIR);
                                ItemStack ironDrop = new ItemStack(Material.IRON_INGOT, 1 + eplayer.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS));
                                event.getPlayer().getWorld().dropItem(event.getBlock().getLocation(), ironDrop);
                                event.getPlayer().spawnParticle(Particle.FLAME, event.getBlock().getLocation(), 10);


                            } else {
                                event.getBlock().setType(Material.AIR);
                                ItemStack ironDrop = new ItemStack(Material.IRON_INGOT, 1);
                                event.getPlayer().getWorld().dropItem(event.getBlock().getLocation(), ironDrop);
                                event.getPlayer().spawnParticle(Particle.FLAME, event.getBlock().getLocation(), 10);

                            }
                        } else {

                            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                event.getBlock().setType(Material.AIR);
                                ItemStack iron = new ItemStack(Material.IRON_INGOT, event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1);
                                event.getPlayer().getInventory().addItem(iron);
                                event.getPlayer().spawnParticle(Particle.LAVA, event.getBlock().getLocation(), 10);

                            } else {
                                event.getBlock().setType(Material.AIR);
                                ItemStack iron = new ItemStack(Material.IRON_INGOT, 1);
                                event.getPlayer().getInventory().addItem(iron);
                                event.getPlayer().spawnParticle(Particle.LAVA, event.getBlock().getLocation(), 10);
                            }

                        }
                    } else {
                        if (event.getBlock().getType().equals(Material.GOLD_ORE)) {
                            if (check == -1) {
                                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                    event.getBlock().setType(Material.AIR);
                                    ItemStack goldDrop = new ItemStack(Material.GOLD_INGOT, 1 + event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS));
                                    event.getPlayer().getWorld().dropItem(event.getBlock().getLocation(), goldDrop);
                                    event.getPlayer().spawnParticle(Particle.FLAME, event.getBlock().getLocation(), 10);

                                } else {
                                    event.getBlock().setType(Material.AIR);
                                    ItemStack goldDrop = new ItemStack(Material.GOLD_INGOT, 1);
                                    event.getPlayer().getWorld().dropItem(event.getBlock().getLocation(), goldDrop);
                                    event.getPlayer().spawnParticle(Particle.FLAME, event.getBlock().getLocation(), 10);

                                }
                            } else {
                                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
                                    event.getBlock().setType(Material.AIR);
                                    ItemStack gold = new ItemStack(Material.GOLD_INGOT, event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1);
                                    event.getPlayer().getInventory().addItem(gold);
                                    event.getPlayer().spawnParticle(Particle.FLAME, event.getBlock().getLocation(), 10);

                                } else {
                                    event.getBlock().setType(Material.AIR);
                                    ItemStack gold = new ItemStack(Material.GOLD_INGOT, 1);
                                    event.getPlayer().getInventory().addItem(gold);
                                    event.getPlayer().spawnParticle(Particle.FLAME, event.getBlock().getLocation(), 10);
                                }

                            }
                        } else {
                        }
                    }
                } else {
                    return;
                }
            } else {
            }
        }
        }
        }
    }
}
