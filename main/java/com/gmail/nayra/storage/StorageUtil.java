package com.gmail.nayra.storage;

import com.gmail.nayra.MonsterHamster;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StorageUtil {

    static MonsterHamster hm = MonsterHamster.getPluginType();


    public static void setPoints(Player sender, Integer number) {
        UUID playerID = sender.getUniqueId();
        hm.getPlayerData().set("Players." + playerID + ".bosspoints", number);
        hm.saveData();
    }

    public static void addPoints(UUID playerID, Integer number) {

        Integer coinNumber = hm.getPlayerData().getInt("Players." + playerID + ".bosspoints");
        Integer additionCoin = coinNumber + number;
        hm.getPlayerData().set("Players." + playerID + ".bosspoints", additionCoin);
        hm.saveData();
    }
}
