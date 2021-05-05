package com.gmail.nayra.property.enchantments;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.utils.RandomNumber;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;

public class FlyEnchantment implements Listener {
    private MonsterHamster plugin;

    public FlyEnchantment(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onArmorWear(PlayerMoveEvent e) {
        FileConfiguration config = plugin.getConfig();
        if(config.getString("Config.Enchantments.Active_Enchantments").equals("true")) {
            Player p = e.getPlayer();
            if (p.getInventory().getBoots() == null) {
                if (p.isFlying()) {
                    if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                        if (!p.isOp()) {
                            if (!p.hasPermission("monsterhamster.bypass")) {
                                p.setAllowFlight(false);
                                p.setFlying(false);
                                return;
                            }
                        }
                    }
                }
            } else {
                if (!p.getInventory().getBoots().getItemMeta().hasLore()) return;
                if (p.getInventory().getBoots().getItemMeta().getLore().contains("Fly")) {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    int foodLevel = p.getFoodLevel();
                    if (foodLevel <= 1.0) {
                        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                            if (!p.isOp()) {
                                p.setAllowFlight(false);
                                p.setFlying(false);
                            }
                        }
                    } else {
                        RandomNumber rd = new RandomNumber();
                        if (rd.getRandonNumber(11) > 9) {
                            p.setFoodLevel(foodLevel - 1);
                        }
                    }
                } else {

                }
            }
        }
    }
}
