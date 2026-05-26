package me.yourname.staffsystem.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerViewGUI {

    public static Inventory inv(Player target) {

        Inventory inv = Bukkit.createInventory(null, 54,
                "§c" + target.getName() + " Inventory");

        inv.setContents(target.getInventory().getContents());

        return inv;
    }

    public static Inventory ec(Player target) {

        Inventory inv = Bukkit.createInventory(null, 27,
                "§5" + target.getName() + " Ender Chest");

        inv.setContents(target.getEnderChest().getContents());

        return inv;
    }
}