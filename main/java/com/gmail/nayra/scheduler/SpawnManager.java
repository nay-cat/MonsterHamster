package com.gmail.nayra.scheduler;


import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.boss.GetBoss;
import com.gmail.nayra.boss.SpawnBoss;
import com.gmail.nayra.finder.RandomAbility;
import com.gmail.nayra.utils.Local;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Random;

public class SpawnManager {
    private MonsterHamster plugin;
    public SpawnManager(MonsterHamster plugin) {
        this.plugin = plugin;
    }
    public static MonsterHamster hm = MonsterHamster.getPluginType();

    public static void spawn() {
        FileConfiguration config = hm.getConfig();
        if(hm.getPlayerData().contains("Server.World.name")) {
            if (config.getString("Config.spawnTimer").equals("true")) {
                BukkitScheduler scheduler = MonsterHamster.getPluginType().getServer().getScheduler();
                scheduler.scheduleSyncRepeatingTask(MonsterHamster.getPluginType(), new Runnable() {

                    @Override
                    public void run() {

                        if (hm.getConfig().getString("Config.spawnTimer").equals("false")) {
                            scheduler.cancelAllTasks();
                        }

                        List<String> bossList = MonsterHamster.getPluginType().getConfig().getStringList("Config.AllowBosses");
                        GetBoss r = new GetBoss();
                        String randomAbilitySuperName = r.getRandomBoss(bossList);

                        String getWorldBoss = hm.getPlayerData().getString("Server.World.name");
                        World findWorldbyName = hm.getServer().getWorld(getWorldBoss);
                        Location location = new Location(findWorldbyName, hm.getPlayerData().getDouble("Server.World.x"), hm.getPlayerData().getDouble("Server.World.z"), hm.getPlayerData().getDouble("Server.World.y"));

                        LivingEntity boss = (LivingEntity) findWorldbyName.spawnEntity(location, EntityType.SKELETON);

                        String bossName = config.getString("Config.Monsters." + randomAbilitySuperName + ".name");
                        double bossHealth = config.getDouble("Config.Monsters." + randomAbilitySuperName + ".health"), bossArmor = config.getDouble("Config.Monsters." + randomAbilitySuperName + ".armor"), bossDamage = config.getDouble("Config.Monsters." + randomAbilitySuperName + ".damage"), bossSpeed = config.getDouble("Config.Monsters." + randomAbilitySuperName + ".speed"), kbResistance = config.getDouble("Config.Monsters." + randomAbilitySuperName + ".knockback_resistance");

                        boss.setCustomName(ChatColor.translateAlternateColorCodes('&', bossName));

                        AttributeInstance armor = boss.getAttribute(Attribute.GENERIC_ARMOR), damage = boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE), speed = boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED), knockback = boss.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
                        armor.setBaseValue(bossArmor);
                        damage.setBaseValue(bossDamage);
                        knockback.setBaseValue(kbResistance);
                        speed.setBaseValue(bossSpeed);
                        AttributeInstance health = boss.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                        health.setBaseValue(5000);
                        boss.setHealth(bossHealth);
                        boss.setCanPickupItems(false);
                        boss.setGlowing(true);
                        boss.setAI(true);

                        ItemStack diamondHelmet = new ItemStack(Material.DIAMOND_HELMET);
                        diamondHelmet.addEnchantment(Enchantment.DURABILITY, 3);
                        boss.getEquipment().setHelmet(diamondHelmet);


                        if (config.getString("Config.spawnBroadcast").equals("true")) {
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', hm.prefix + hm.getMessages().getString("Messages.spawn").replaceAll("%boss%", bossName)).replaceAll("%location%", "X: " + hm.getPlayerData().getDouble("Server.World.x") + "Y: " + hm.getPlayerData().getDouble("Server.World.y") + "Z: " + hm.getPlayerData().getDouble("Server.World.z")));
                        }
                    }

                }, 0L, config.getInt("Config.spawnTime"));

            }
        }else{

        }
    }

}

