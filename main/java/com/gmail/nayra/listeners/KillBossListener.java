package com.gmail.nayra.listeners;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.register.PlayerRegister;
import com.gmail.nayra.storage.StorageUtil;
import com.gmail.nayra.utils.Local;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class KillBossListener implements Listener {
    private MonsterHamster plugin;
    public KillBossListener(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBossDeath(EntityDeathEvent e){
        String prefix = plugin.getMessages().getString("Messages.prefix");
        if(e.getEntity().getKiller().getType().equals(EntityType.PLAYER)){
        EntityType entityBoss = e.getEntity().getType();
        if(entityBoss == EntityType.SKELETON) {
            Skeleton skellyBoss = (Skeleton) e.getEntity();
            ItemStack helmet = skellyBoss.getEquipment().getHelmet();
            Integer level = helmet.getEnchantmentLevel(Enchantment.DURABILITY);
            if (level == 3) {
                String broadcastKill = plugin.getMessages().getString("Messages.bossDeathBroadcast");

                Local.bdc(prefix + broadcastKill.replaceAll("%killer%", e.getEntity().getKiller().getName()).replaceAll("%boss%", e.getEntity().getName()));

                PlayerRegister.playerRegister(e.getEntity().getKiller().getUniqueId());



            }

        }else{

        }

        }

        }


}
