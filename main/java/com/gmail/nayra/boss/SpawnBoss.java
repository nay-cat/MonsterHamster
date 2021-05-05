package com.gmail.nayra.boss;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.utils.Local;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnBoss {
    private static MonsterHamster plugin;

    public SpawnBoss(MonsterHamster plugin) {
        this.plugin = plugin;
    }


    public static void SpawnEntityBoss(String name, String w, double x, double y, double z){
        FileConfiguration config = plugin.getConfig();


        Location location = new Location(plugin.getServer().getWorld(w), x, y, z);

        World selectedSpawn = plugin.getServer().getWorld(w);
        selectedSpawn.spawnEntity(location, EntityType.SKELETON);
        /*
        LivingEntity boss = (LivingEntity) plugin.getServer().getWorld(w).spawnEntity(location, EntityType.SKELETON);

        boss.setCustomName(ChatColor.translateAlternateColorCodes('&', bossName));



        ItemStack diamondHelmet = new ItemStack(Material.DIAMOND_HELMET);
        diamondHelmet.addEnchantment(Enchantment.DURABILITY, 3);
        boss.getEquipment().setHelmet(diamondHelmet);

        for (String s : config.getStringList("Config.Monsters," + name + ".effects")) {
            PotionEffectType effect = null;
            try {
                effect = PotionEffectType.getByName(s);
            } catch (Exception error) {
                Local.log("&6[MonsterHamster] &cAn error has ocurred: " + error);
                break;
            }
            int multiplier = config.getInt("Config.Monsters." + name + ".effect_multiplier");
            boss.addPotionEffect(new PotionEffect(effect, 25500, multiplier));
        }

         */

    }


}
