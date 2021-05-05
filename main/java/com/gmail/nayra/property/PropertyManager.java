package com.gmail.nayra.property;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.finder.RandomAbility;
import com.gmail.nayra.group.MonsterCreator;
import com.gmail.nayra.utils.RandomNumber;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PropertyManager implements Listener {
    private MonsterHamster plugin;
    public PropertyManager(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBossAttackUseAbility(EntityDamageByEntityEvent e) {
        EntityType entityBoss = e.getEntity().getType();
        if (entityBoss == EntityType.SKELETON && e.getDamager().getType().equals(EntityType.PLAYER)) {

            if (e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                e.getDamager().teleport(e.getEntity().getLocation());
            } else {

            Skeleton skellyBoss = (Skeleton) e.getEntity();
            ItemStack helmet = skellyBoss.getEquipment().getHelmet();
            int level = helmet.getEnchantmentLevel(Enchantment.DURABILITY);
            if (level == 3) {
                RandomNumber rn = new RandomNumber();
                if (rn.getRandonNumber(100) > 50) {

                    Player abilityPlayer = (Player) e.getDamager();
                    List<String> bossAbilityList = plugin.getConfig().getStringList("Config.Abilities");
                    RandomAbility rd = new RandomAbility();
                    String randomAbilitySuperName = rd.getRandomAbility(bossAbilityList);

                    switch (randomAbilitySuperName) {

                        case "lighting":
                            abilityPlayer.getWorld().strikeLightning(abilityPlayer.getLocation());
                            break;

                        case "poison":
                            abilityPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, rn.getRandonNumber(5)));
                            break;

                        case "shoot":

                            skellyBoss.launchProjectile(Fireball.class);
                            break;
                        case "teleport":

                            abilityPlayer.teleport(skellyBoss.getLocation());
                            break;
                    }
                }

            }
        }
    }
    }
}
