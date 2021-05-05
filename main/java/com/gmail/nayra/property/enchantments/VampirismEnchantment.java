package com.gmail.nayra.property.enchantments;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.utils.RandomNumber;
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

public class VampirismEnchantment implements Listener {
    private MonsterHamster plugin;

    public VampirismEnchantment(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void healOtherPlayer(EntityDamageByEntityEvent e){
        FileConfiguration config = plugin.getConfig();
        if(config.getString("Config.Enchantments.Active_Enchantments").equals("true")) {

            if (e.getEntityType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER) {
                Player healer = (Player) e.getDamager();
                ItemStack nothing = healer.getInventory().getItemInMainHand();
                if (nothing == null || nothing.getType() == Material.AIR) return;
                
                Player heal = (Player) e.getEntity();
                if(healer.getGameMode().equals(GameMode.CREATIVE) || heal.getGameMode().equals(GameMode.CREATIVE)) return;
                if (healer.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    if (healer.getInventory().getItemInMainHand().getItemMeta().getLore().contains("Vampirism")) {
                        double HealerHealth = healer.getHealth();
                        if (heal.getHealth() < 2.5D) {
                            healer.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getMessages().getString("Messages.cantrob")));
                            healer.spawnParticle(Particle.BARRIER, healer.getLocation(), 15);

                        } else {
                            RandomNumber rd = new RandomNumber();
                            if(rd.getRandonNumber(10) >= config.getInt("Config.Enchantments.Vampirism.Probability")) {
                                healer.setHealth(HealerHealth + config.getInt("Config.Enchantments.Vampirism.HealthRob"));
                                heal.setHealth(heal.getHealthScale() - config.getInt("Config.Enchantments.Vampirism.HealthRob"));
                                heal.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getMessages().getString("Messages.vampirism")).replaceAll("%vampire%", healer.getName()));
                                healer.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+plugin.getMessages().getString("Messages.vampirismrob")).replaceAll("%player%", heal.getName()));
                                heal.spawnParticle(Particle.REDSTONE, heal.getLocation(), 15);
                                healer.spawnParticle(Particle.REDSTONE, healer.getLocation(), 15);
                            }

                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
}
