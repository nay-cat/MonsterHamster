package com.gmail.nayra.register;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.storage.StorageUtil;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerRegister {

    public static void playerRegister(UUID playerID){

        if(MonsterHamster.getPluginType().getPlayerData().contains("Players."+playerID)){
            StorageUtil.addPoints(playerID, 1);
        }else{
            MonsterHamster.getPluginType().getPlayerData().set("Players."+playerID+".bosspoints", 1);
            MonsterHamster.getPluginType().saveData();
        }

    }
}
