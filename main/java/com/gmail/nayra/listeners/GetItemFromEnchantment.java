package com.gmail.nayra.listeners;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.gui.items.ItemGiver;
import com.gmail.nayra.register.PlayerRegister;
import com.gmail.nayra.utils.EnchantmentList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GetItemFromEnchantment implements Listener {
    private MonsterHamster plugin;
    public GetItemFromEnchantment(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void getItemFromBook(PlayerInteractEvent e){
        ItemStack nothing = e.getPlayer().getInventory().getItemInMainHand();
        if (nothing == null || nothing.getType() == Material.AIR) return;
            EnchantmentList el = new EnchantmentList();
            if(el.enchantmentsList().contains(e.getItem().getItemMeta().getLore().get(0))) {
                if (e.getItem().getType().equals(Material.ENCHANTED_BOOK) || e.getItem().getType().equals(Material.GOLD_NUGGET)) {
                    if (e.getItem().getItemMeta().hasLore()) {
                        ItemGiver
                                ig = new ItemGiver();
                        FileConfiguration config = plugin.getConfig();
                        switch (e.getItem().getItemMeta().getLore().get(0)) {

                            case "Fireball":
                                ig.itemGive(Material.BOW, e.getPlayer(), config.getString("Config.Enchantments.Items.firebow"), "Fireball");
                                break;

                            case "Freeze":
                                ig.itemGive(Material.DIAMOND_AXE, e.getPlayer(), config.getString("Config.Enchantments.Items.freezeaxe"), "Freeze");
                                break;

                            case "Smelting":
                                ig.itemGive(Material.DIAMOND_PICKAXE, e.getPlayer(), config.getString("Config.Enchantments.Items.smeltingpickaxe"), "Smelting");
                                break;

                            case "Poison":
                                ig.itemGive(Material.DIAMOND_SWORD, e.getPlayer(), config.getString("Config.Enchantments.Items.poisonsword"), "Poison");
                                break;

                            case "Healer":
                                ig.itemGive(Material.DIAMOND_HOE, e.getPlayer(), config.getString("Config.Enchantments.Items.healerhoe"), "Healer");
                                break;

                            case "Vampirism":
                                ig.itemGive(Material.DIAMOND_SWORD, e.getPlayer(), config.getString("Config.Enchantments.Items.bloodsword"), "Vampirism");
                                break;

                            case "Fly":
                                ig.itemGive(Material.DIAMOND_BOOTS, e.getPlayer(), config.getString("Config.Enchantments.Items.flyboots"), "Fly");
                                break;

                            case "An epic coin from epic boss":
                                e.getPlayer().getInventory().remove(e.getPlayer().getItemInHand());
                                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 4.0F, 0.3F);
                                PlayerRegister.playerRegister(e.getPlayer().getUniqueId());
                                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getMessages().getString("Messages.claimPointsFromEpicCoin")).replaceAll("%points%", String.valueOf(config.getInt("Config.rewardKillBoss"))));
                                break;

                        }
                    }
                }
            }
        }
    }

