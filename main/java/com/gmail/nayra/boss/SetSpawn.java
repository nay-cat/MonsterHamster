package com.gmail.nayra.boss;

import com.gmail.nayra.MonsterHamster;
import org.bukkit.Location;
import org.bukkit.World;

public class SetSpawn {
    protected MonsterHamster hm = MonsterHamster.getPluginType();

    public void setSpawnSetup(World w, Location l){
        double xl = l.getBlockX();
        double yl = l.getBlockY();
        double zl = l.getBlockZ();
        hm.getPlayerData().set("Server.World.name", w.getName());
        hm.getPlayerData().set("Server.World.x", xl);
        hm.getPlayerData().set("Server.World.y", yl);
        hm.getPlayerData().set("Server.World.z", zl);
        hm.saveData();


    }
}
