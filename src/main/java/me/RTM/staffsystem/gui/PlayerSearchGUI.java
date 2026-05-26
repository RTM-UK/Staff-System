package me.yourname.staffsystem.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerSearchGUI {

    public static Inventory create() {

        Inventory inv = Bukkit.createInventory(null, 54, "§dPlayer Selector");

        int slot = 0;

        for (Player p : Bukkit.getOnlinePlayers()) {

            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            ItemMeta meta = item.getItemMeta();

            if (meta == null) continue;

            meta.setDisplayName("§e" + p.getName());
            item.setItemMeta(meta);

            inv.setItem(slot++, item);

            if (slot >= 54) break;
        }

        return inv;
    }
}