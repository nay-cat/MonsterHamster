package com.gmail.nayra.gui.items;

import com.gmail.nayra.MonsterHamster;
import com.gmail.nayra.storage.StorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class BuyItem implements Listener {
    private MonsterHamster plugin;
    public BuyItem(MonsterHamster plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void getItemFromShop(InventoryClickEvent event){

        final ItemStack debug = event.getCurrentItem();
        if (debug == null || debug.getType() == Material.AIR) return;

        FileConfiguration config = plugin.getConfig();
    if(event.getCurrentItem().getType() == Material.ENCHANTED_BOOK || event.getCurrentItem().getType() == Material.GOLD_NUGGET){
        if(event.getInventory().getName().equals(config.getString("Config.ShopGui.name"))) {
                ItemStack currentSelect = event.getCurrentItem();
                ItemStack enchantedBookPoison = new ItemStack(Material.ENCHANTED_BOOK, 1);
                ItemMeta editor = enchantedBookPoison.getItemMeta();
                editor.setDisplayName("Epic Enchanted Book");
                ArrayList<String> Lore = new ArrayList<>();
                StorageUtil sg = new StorageUtil();
                Player sender = (Player) event.getView().getPlayer();
                UUID playerUUID = event.getView().getPlayer().getUniqueId();
                String noMoney = plugin.getMessages().getString("Messages.noMoney");
                String prefix = plugin.getMessages().getString("Messages.prefix");
        /*
        Sorry for the bad code in this class, I make this fast because i want end the plugin.
         */
            switch (currentSelect.getItemMeta().getDisplayName()) {
                    case "Boss Coin" :
                        if (sg.getPoints(playerUUID) >= config.getInt("Config.Shop.BossCoin.price")) {
                            StorageUtil.addPoints(playerUUID, -config.getInt("Config.Shop.BossCoin.price"));
                            ItemStack enchantedCoin = new ItemStack(Material.GOLD_NUGGET, 1);
                            ItemMeta editorCoin = enchantedCoin.getItemMeta();
                            editorCoin.setDisplayName("Boss Epic Coin");
                            ArrayList<String> LoreCoin = new ArrayList<>();
                            LoreCoin.add("An epic coin from epic boss");
                            // LoreCoin.add("Owner: " + event.getView().getPlayer().getName());
                            editorCoin.setLore(LoreCoin);
                            enchantedCoin.setItemMeta(editorCoin);
                            event.getView().getPlayer().getInventory().addItem(enchantedCoin);

                        } else {

                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noMoney));

                        }
                        break;
                    case "Poison Enchantment":
                        if (sg.getPoints(playerUUID) >= config.getInt("Config.Shop.PoisonBook.price")) {
                            StorageUtil.addPoints(playerUUID, -config.getInt("Config.Shop.PoisonBook.price"));
                            Lore.add("Poison");
                            editor.setLore(Lore);
                            enchantedBookPoison.setItemMeta(editor);
                            event.getView().getPlayer().getInventory().addItem(enchantedBookPoison);

                        } else {

                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noMoney));

                        }
                        break;
                    case "Fireball Enchantment":
                        if (sg.getPoints(playerUUID) >= config.getInt("Config.Shop.FireballBook.price")) {

                            StorageUtil.addPoints(playerUUID, -config.getInt("Config.Shop.FireballBook.price"));
                            Lore.add("Fireball");
                            editor.setLore(Lore);
                            enchantedBookPoison.setItemMeta(editor);
                            event.getView().getPlayer().getInventory().addItem(enchantedBookPoison);
                        } else {

                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noMoney));

                        }
                        break;
                    case "Smelting Enchantment":
                        if (sg.getPoints(playerUUID) >= config.getInt("Config.Shop.SmeltingBook.price")) {

                            StorageUtil.addPoints(playerUUID, -config.getInt("Config.Shop.SmeltingBook.price"));
                            Lore.add("Smelting");
                            editor.setLore(Lore);
                            enchantedBookPoison.setItemMeta(editor);
                            event.getView().getPlayer().getInventory().addItem(enchantedBookPoison);
                        } else {

                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noMoney));

                        }
                        break;
                    case "Vampirism Enchantment":
                        if (sg.getPoints(playerUUID) >= config.getInt("Config.Shop.VampirismBook.price")) {

                            StorageUtil.addPoints(playerUUID, -config.getInt("Config.Shop.VampirismBook.price"));
                            Lore.add("Vampirism");
                            editor.setLore(Lore);
                            enchantedBookPoison.setItemMeta(editor);
                            event.getView().getPlayer().getInventory().addItem(enchantedBookPoison);
                        } else {

                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noMoney));

                        }
                        break;
                    case "Fly Enchantment":
                        if (sg.getPoints(playerUUID) >= config.getInt("Config.Shop.FlyBook.price")) {

                            StorageUtil.addPoints(playerUUID, -config.getInt("Config.Shop.FlyBook.price"));
                            Lore.add("Fly");
                            editor.setLore(Lore);
                            enchantedBookPoison.setItemMeta(editor);
                            event.getView().getPlayer().getInventory().addItem(enchantedBookPoison);
                        } else {

                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noMoney));

                        }
                        break;
                case "Freeze Enchantment":
                    if (sg.getPoints(playerUUID) >= config.getInt("Config.Shop.FreezeBook.price")) {

                        StorageUtil.addPoints(playerUUID, -config.getInt("Config.Shop.FreezeBook.price"));
                        Lore.add("Freeze");
                        editor.setLore(Lore);
                        enchantedBookPoison.setItemMeta(editor);
                        event.getView().getPlayer().getInventory().addItem(enchantedBookPoison);

                    } else {

                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+noMoney));

                    }
                    break;

                }
            }
        }else {
        }
        }

}
