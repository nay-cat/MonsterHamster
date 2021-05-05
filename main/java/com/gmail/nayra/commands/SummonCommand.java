package com.gmail.nayra.commands;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.utils.Local;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class SummonCommand implements CommandExecutor {
    private MonsterHamster plugin;
    public SummonCommand(MonsterHamster plugin) {
        this.plugin = plugin;
    }
    



    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (player instanceof Player) {

            FileConfiguration config = plugin.getConfig();
            Player sender = (Player) player;
            String prefix = plugin.getMessages().getString("Messages.prefix");

            if (args.length == 0) {
                String parameters = plugin.getMessages().getString("Messages.noParameters");
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + parameters));
            } else {
                String bossSearch = "Config.Monsters." + args[0];
                if (config.contains(bossSearch)) {
                    if (sender.hasPermission("monsterhamster.summon")) {
                        World spawnWorld = sender.getWorld();
                        Location senderLocation = sender.getLocation();
                        String bossName = config.getString("Config.Monsters." + args[0] + ".name");
                        Double bossHealth = config.getDouble("Config.Monsters." + args[0] + ".health"), bossArmor = config.getDouble("Config.Monsters." + args[0] + ".armor"), bossDamage = config.getDouble("Config.Monsters." + args[0] + ".damage"), bossSpeed = config.getDouble("Config.Monsters." + args[0] + ".speed"), kbResistance = config.getDouble("Config.Monsters." + args[0] + ".knockback_resistance");
                        LivingEntity boss = (LivingEntity) spawnWorld.spawnEntity(senderLocation, EntityType.SKELETON);
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

                        for (String s : config.getStringList("Config.Monsters," + args[0] + ".effects")) {
                            PotionEffectType effect = null;
                            try {
                                effect = PotionEffectType.getByName(s);
                            } catch (Exception error) {
                                Local.log("&cAn error has ocurred: " + error);
                                break;
                            }
                            Integer multiplier = config.getInt("Config.Monsters." + args[0] + ".effect_multiplier");
                            boss.addPotionEffect(new PotionEffect(effect, 25500, multiplier));
                        }
                        String spawnEntity = plugin.getMessages().getString("Messages.spawnEntity");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + spawnEntity).replaceAll("%entity%", args[0]));



                    } else {
                        String noPermission = plugin.getMessages().getString("Messages.noPermission");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + noPermission));
                    }


                } else {
                    String uknEntity = plugin.getMessages().getString("Messages.unkownEntity");
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + uknEntity));
                }

                return true;
            }
            return true;
        }
        return true;
    }

}
