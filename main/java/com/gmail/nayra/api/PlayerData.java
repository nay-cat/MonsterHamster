package com.gmail.nayra.api;

import com.gmail.nayra.MonsterHamster;

import java.util.UUID;


public class PlayerData {

    public int getPlayerBossPoints(UUID requestData){
        return MonsterHamster.getPluginType().getPlayerData().getInt("Players."+requestData+".bosspoints");
    }

}
