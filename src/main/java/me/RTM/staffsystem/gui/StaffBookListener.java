package me.RTM.staffsystem.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StaffBookListener implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent e) {

        ItemStack item = e.getItem();
        if (item == null) return;

        if (item.getType() != Material.BOOK) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || meta.getDisplayName() == null) return;

        if (!meta.getDisplayName().equals("§bStaff Menu")) return;

        e.setCancelled(true);

        Player p = e.getPlayer();

        p.openInventory(PlayerSearchGUI.create());
    }
}
