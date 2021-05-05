package com.gmail.nayra.listeners;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.register.PlayerRegister;
import com.gmail.nayra.storage.StorageUtil;
import com.gmail.nayra.utils.Local;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class KillBossListener implements Listener {
    private MonsterHamster plugin;
    public KillBossListener(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBossDeath(EntityDeathEvent e){
        if(e.getEntity().isDead()) {
                if (e.getEntity().getKiller().getType().equals(EntityType.PLAYER)) {
                    String prefix = plugin.getMessages().getString("Messages.prefix");
                    EntityType entityBoss = e.getEntity().getType();
                    if (entityBoss == EntityType.SKELETON) {
                        Skeleton skellyBoss = (Skeleton) e.getEntity();
                        ItemStack helmet = skellyBoss.getEquipment().getHelmet();
                        int level = helmet.getEnchantmentLevel(Enchantment.DURABILITY);
                        if (level == 3) {
                            String broadcastKill = plugin.getMessages().getString("Messages.bossDeathBroadcast");
                    /*
                    Identificar a la entidad de forma fácil  (Sin necesidad de revisar su encantamiento de casco (Pendiente)
                     */



                            if(plugin.getConfig().getString("Config.NetworkMode").equals("true")) {
                                ItemStack enchantedCoin = new ItemStack(Material.GOLD_NUGGET, 1);
                                ItemMeta editorCoin = enchantedCoin.getItemMeta();
                                editorCoin.setDisplayName("Boss Epic Coin");
                                ArrayList<String> LoreCoin = new ArrayList<>();
                                LoreCoin.add("An epic coin from epic boss");
                                editorCoin.setLore(LoreCoin);
                                enchantedCoin.setItemMeta(editorCoin);

                                e.getDrops().set(plugin.getConfig().getInt("Config.rewardKillBoss"), enchantedCoin);
                            }else{

                                Local.bdc(prefix + broadcastKill.replaceAll("%killer%", e.getEntity().getKiller().getName()).replaceAll("%boss%", e.getEntity().getName()));
                                e.getDrops().clear();
                                PlayerRegister.playerRegister(e.getEntity().getKiller().getUniqueId());
                            }
                        } else {
                        }


                    }
                }

            } else {


            }




        }


}
