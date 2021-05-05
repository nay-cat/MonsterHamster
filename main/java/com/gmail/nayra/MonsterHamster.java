package com.gmail.nayra;

import com.gmail.nayra.commands.*;
import com.gmail.nayra.gui.DenyCatch;
import com.gmail.nayra.gui.inventory.CreateInventory;
import com.gmail.nayra.gui.items.BuyItem;
import com.gmail.nayra.gui.items.InventoryItems;
import com.gmail.nayra.listeners.GetItemFromEnchantment;
import com.gmail.nayra.listeners.KillBossListener;
import com.gmail.nayra.property.PropertyManager;
import com.gmail.nayra.property.enchantments.*;
import com.gmail.nayra.scheduler.SpawnManager;
import com.gmail.nayra.utils.Local;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public final class MonsterHamster extends JavaPlugin {
    private File dataFile;
    private FileConfiguration dataFileConfig;
    private File messagesFile;
    private FileConfiguration messagesFileConfig;
    public String Configstr;
    public double version;
    public Inventory shop;
    public String prefix;
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        Local.log("&aLoading plugin...");
        createDataFile();
        createMessageFile();
        register();
        mainConfig();
        this.version = 1.1;
        Local.log("&aThanks for using this plugin! <3 :D");
        Local.log("&9Version: &b" + version);
        prefix = getMessages().getString("Messages.prefix");
        shop = Bukkit.createInventory(null, 9, getConfig().getString("Config.ShopGui.name"));
        addingInvetoryItems();
        SpawnManager.spawn();
        MonsterHamster.plugin = this;
    }


    public void addingInvetoryItems() {
        InventoryItems itv = new InventoryItems();
        // Yes config here lol
        FileConfiguration config = getConfig();
        shop.addItem(itv.createItem(Material.GOLD_NUGGET, "Boss Coin", "Cost: "+config.getInt("Config.Shop.BossCoin.price")));
        shop.addItem(itv.createItem(Material.ENCHANTED_BOOK, "Poison Enchantment", "Cost: "+config.getInt("Config.Shop.PoisonBook.price")));
        shop.addItem(itv.createItem(Material.ENCHANTED_BOOK, "Fireball Enchantment", "Cost: "+config.getInt("Config.Shop.FireballBook.price")));
        shop.addItem(itv.createItem(Material.ENCHANTED_BOOK, "Smelting Enchantment", "Cost: "+config.getInt("Config.Shop.SmeltingBook.price")));
        shop.addItem(itv.createItem(Material.ENCHANTED_BOOK, "Healer Enchantment", "Cost: "+config.getInt("Config.Shop.HealerBook.price")));
        shop.addItem(itv.createItem(Material.ENCHANTED_BOOK, "Vampirism Enchantment", "Cost: "+config.getInt("Config.Shop.VampirismBook.price")));
        shop.addItem(itv.createItem(Material.ENCHANTED_BOOK, "Fly Enchantment", "Cost: "+config.getInt("Config.Shop.FlyBook.price")));
        shop.addItem(itv.createItem(Material.ENCHANTED_BOOK, "Freeze Enchantment", "Cost: "+config.getInt("Config.Shop.FreezeBook.price")));
    }

    @Override
    public void onDisable() {
        Local.log("&cDisable...");
    }

    public void register() {
        FileConfiguration config = getConfig();
        PluginManager pm = getServer().getPluginManager();
        this.getCommand("monsterhamster").setExecutor(new PluginCommand(this));
        this.getCommand("monstersummon").setExecutor(new SummonCommand(this));
        this.getCommand("bosspoints").setExecutor(new UserCommands(this));
        this.getCommand("bshop").setExecutor(new ShopCommand(this));
        this.getCommand("menchant").setExecutor(new EnchantCommand(this));
        this.getCommand("msetspawn").setExecutor(new SetupSpawn(this));
        this.getCommand("mmodify").setExecutor(new AdminCommands(this));
        pm.registerEvents(new KillBossListener(this), this);
        pm.registerEvents(new PropertyManager(this), this);
        pm.registerEvents(new DenyCatch(this), this);
        pm.registerEvents(new BuyItem(this), this);
        pm.registerEvents(new GetItemFromEnchantment(this), this);
        if(config.getString("Config.Enchantments.Load_Enchantments").equals("true")){
            EnchantmentRegister();
            Local.log("&aLoading enchantments...");
        }else{
            Local.log("&cI can't load enchantments");
        }


    }

    public void EnchantmentRegister(){
        PluginManager enchantRegister = getServer().getPluginManager();
        enchantRegister.registerEvents(new PoisonEnchantment(this), this);
        enchantRegister.registerEvents(new FireballEnchantment(this), this);
        enchantRegister.registerEvents(new SmeltingEnchantment(this), this);
        enchantRegister.registerEvents(new FlyEnchantment(this), this);
        enchantRegister.registerEvents(new OtherSmeltingEnchantment(this), this);
        enchantRegister.registerEvents(new HealerEnchantment(this), this);
        enchantRegister.registerEvents(new VampirismEnchantment(this), this);
        enchantRegister.registerEvents(new FreezeEnchantment(this), this);
    }

    private void createDataFile() {
        dataFile = new File(getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
            Local.log("&6[MonsterHamster] &2Data.yml load");
        } else {
            Local.log("&6[MonsterHamster] &aData.yml created");
        }

        dataFileConfig = new YamlConfiguration();
        try {
            dataFileConfig.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            dataFileConfig.save(dataFile);
        } catch (IOException exception) {
            Local.log("&6[MonsterHamster] &cCouldn't save data");
            Local.log("&6[MonsterHamster] &cError: &4" + exception);
        }
    }

    public FileConfiguration getPlayerData() {
        return this.dataFileConfig;
    }

    private void createMessageFile() {
        messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            messagesFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
            Local.log("&6[MonsterHamster] &aMessages.yml load");
        } else {
            Local.log("&6[MonsterHamster] &aMessages.yml created");
        }

        messagesFileConfig = new YamlConfiguration();
        try {
            messagesFileConfig.load(messagesFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void mainConfig() {
        File config = new File(getDataFolder(), "config.yml");
        this.Configstr = config.getPath();
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
            Local.log("&6[MonsterHamster] &aConfig.yml load");
        } else {
            Local.log("&6[MonsterHamster] &aConfig.yml created");
        }
    }

    public FileConfiguration getMessages() {
        return this.messagesFileConfig;
    }

    public static MonsterHamster getPluginType() {
        return MonsterHamster.getPlugin(MonsterHamster.class);
    }

}
