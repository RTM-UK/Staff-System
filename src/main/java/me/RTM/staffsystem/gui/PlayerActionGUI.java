package me.RTM.staffsystem.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerActionGUI {

    public static Inventory create(Player target) {

        Inventory inv = Bukkit.createInventory(null, 27,
                "§dInspect: " + target.getName());

        ItemStack invItem = new ItemStack(Material.CHEST);
        ItemMeta invMeta = invItem.getItemMeta();
        invMeta.setDisplayName("§aView Inventory");
        invItem.setItemMeta(invMeta);

        ItemStack ecItem = new ItemStack(Material.ENDER_CHEST);
        ItemMeta ecMeta = ecItem.getItemMeta();
        ecMeta.setDisplayName("§5View Ender Chest");
        ecItem.setItemMeta(ecMeta);

        inv.setItem(11, invItem);
        inv.setItem(15, ecItem);

        return inv;
    }
}
