package com.gmail.nayra;

import com.gmail.nayra.commands.PluginCommand;
import com.gmail.nayra.commands.SummonCommand;
import com.gmail.nayra.commands.UserCommands;
import com.gmail.nayra.listeners.KillBossListener;
import com.gmail.nayra.property.PropertyManager;
import com.gmail.nayra.utils.Local;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class MonsterHamster extends JavaPlugin {
    private File dataFile;
    private FileConfiguration dataFileConfig;
    private File messagesFile;
    private FileConfiguration messagesFileConfig;
    public String Configstr;
    public double version;

    @Override
    public void onEnable() {
        Local.log("&6[MonsterHamster] &aLoading plugin...");
        createDataFile();
        createMessageFile();
        register();
        mainConfig();
        this.version = 1.0;
        Local.log("&6[MonsterHamster] &aThanks for using this plugin! <3 :D");
        Local.log("&6[MonsterHamster] &9Version: &b"+version);
    }

    @Override
    public void onDisable() {
        Local.log("&6[MonsterHamster] &cDisable...");
    }

    public void register(){
        PluginManager pm = getServer().getPluginManager();
        this.getCommand("monsterhamster").setExecutor(new PluginCommand(this));
        this.getCommand("monstersummon").setExecutor(new SummonCommand(this));
        this.getCommand("bosspoints").setExecutor(new UserCommands(this));
        pm.registerEvents(new KillBossListener(this), this);
        pm.registerEvents(new PropertyManager(this), this);

    }
    private void createDataFile() {
        dataFile = new File(getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
            Local.log("&6[MonsterHamster] &2Data.yml load");
        }else{
            Local.log("&6[MonsterHamster] &aData.yml created");
        }

        dataFileConfig= new YamlConfiguration();
        try {
            dataFileConfig.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveData(){
        try{
            dataFileConfig.save(dataFile);
        }catch(IOException exception){
            Local.log("&6[MonsterHamster] &cCouldn't save data");
            Local.log("&6[MonsterHamster] &cError: &4"+exception);
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
        }else{
            Local.log("&6[MonsterHamster] &aMessages.yml created");
        }

        messagesFileConfig= new YamlConfiguration();
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
        }else{
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
