package com.gmail.nayra.property.enchantments;

import com.gmail.nayra.MonsterHamster;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HealerEnchantment implements Listener {
    private MonsterHamster plugin;

    public HealerEnchantment(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void healOtherPlayer(EntityDamageByEntityEvent e){
        FileConfiguration config = plugin.getConfig();
        if(config.getString("Config.Enchantments.Active_Enchantments").equals("true")) {
            if(config.getString("Config.Enchantments.Healer.HealerEnchantment").equals("true")) {
                if (e.getEntityType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER) {
                    Player healer = (Player) e.getDamager();
                    ItemStack nothing = healer.getInventory().getItemInMainHand();
                    if (nothing == null || nothing.getType() == Material.AIR) return;

                    Player heal = (Player) e.getEntity();
                    if (healer.getGameMode().equals(GameMode.CREATIVE) || heal.getGameMode().equals(GameMode.CREATIVE))
                        return;
                    if (healer.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                        if (healer.getInventory().getItemInMainHand().getItemMeta().getLore().contains("Healer")) {
                            double HealerHealth = healer.getHealth();
                            if (HealerHealth <= config.getInt("Config.Enchanments.Healer.HealerStopHeal")) {
                                healer.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getMessages().getString("Messages.cantheal")));
                                healer.spawnParticle(Particle.VILLAGER_ANGRY, healer.getLocation(), 10);

                            } else {
                                healer.setHealth(HealerHealth - config.getInt("Config.Enchantments.Healer.HealerDamage"));
                                heal.setHealth(20.0D);
                                heal.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getMessages().getString("Messages.heal")).replaceAll("%healer%", healer.getName()));
                                heal.spawnParticle(Particle.VILLAGER_HAPPY, heal.getLocation(), 10);
                                healer.spawnParticle(Particle.VILLAGER_HAPPY, healer.getLocation(), 10);


                            }
                        }
                    } else {
                        /* LOLOLOLOLOLOLLOLLOL returns inútiles = más kilobytes pero que genia que soy */
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }
}
