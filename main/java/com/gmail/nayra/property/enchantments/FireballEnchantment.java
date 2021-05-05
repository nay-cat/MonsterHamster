package com.gmail.nayra.property.enchantments;

import com.gmail.nayra.MonsterHamster;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FireballEnchantment implements Listener {
    private MonsterHamster plugin;

    public FireballEnchantment(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onHitEntityWithEnchant(EntityShootBowEvent e){
        FileConfiguration config = plugin.getConfig();
        if(e.getBow().getItemMeta().hasLore()) {
            if (config.getString("Config.Enchantments.Active_Enchantments").equals("true")) {

                if (e.getEntity().getType().equals((EntityType.PLAYER))) {

                    Player damage = (Player) e.getEntity();
                    ItemStack weaponfirst = damage.getInventory().getItemInMainHand(), weaponsecond = damage.getInventory().getItemInOffHand();
                    if (weaponfirst.getItemMeta().getLore().contains("Fireball") || (weaponsecond.getItemMeta().getLore().contains("Fireball"))) {
                        damage.launchProjectile(Fireball.class);
                    } else {
                        return;
                    }


                } else {
                    return;
                }
            }
        }else{
            return;
        }
    }
}