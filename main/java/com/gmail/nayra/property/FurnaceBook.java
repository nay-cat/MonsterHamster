package com.gmail.nayra.property;

import com.gmail.nayra.utils.RandomNumber;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FurnaceBook {

    public void getFurnaceMaterial(Block block, Player p, Material selected, Material dropMaterial, int max, int id){
        if (block.getType() == selected) {
        if (p.getInventory().getItemInMainHand() == null || p.getInventory().getItemInMainHand().getType() == Material.AIR) return;
        if(!p.getInventory().getItemInMainHand().getItemMeta().hasLore()) return;
        if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("Smelting")) {

                InventoryViewver iv = new InventoryViewver();
                RandomNumber rd = new RandomNumber();
                ItemStack drop = new ItemStack(dropMaterial, rd.getRandonNumber(max), (byte) id);
                int checking = iv.checkFull(p);
                if (checking == -1) {
                    if (block.getType().equals(selected)) {
                        block.getWorld().dropItem(block.getLocation(), drop);
                        p.spawnParticle(Particle.FLAME, block.getLocation(), 10);
                        block.setType(Material.AIR);

                    }
                } else {
                    p.getInventory().addItem(drop);
                    p.spawnParticle(Particle.FLAME, block.getLocation(), 10);
                    block.setType(Material.AIR);


                }
            } else {

            }
        }else{
            // no
        }
    }
}
