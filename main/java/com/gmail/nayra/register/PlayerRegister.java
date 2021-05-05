package com.gmail.nayra.register;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.storage.StorageUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerRegister {
    public static MonsterHamster hm = MonsterHamster.getPluginType();

    public static void playerRegister(UUID playerID){
        FileConfiguration config = hm.getConfig();

        if(MonsterHamster.getPluginType().getPlayerData().contains("Players."+playerID)){
            StorageUtil.addPoints(playerID, config.getInt("Config.rewardKillBoss"));
        }else{
            MonsterHamster.getPluginType().getPlayerData().set("Players."+playerID+".bosspoints", 1);
            MonsterHamster.getPluginType().saveData();
        }

    }
}
