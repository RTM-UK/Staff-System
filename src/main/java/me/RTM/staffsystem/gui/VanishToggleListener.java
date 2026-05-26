package me.yourname.staffsystem.gui;

import me.yourname.staffsystem.managers.StaffManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class VanishToggleListener implements Listener {

    private final StaffManager manager;

    public VanishToggleListener(StaffManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onUse(PlayerInteractEvent e) {

        Action action = e.getAction();

        // only real right/left clicks
        if (action != Action.RIGHT_CLICK_AIR &&
                action != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack item = e.getItem();
        if (item == null || item.getType() != Material.LIME_DYE) return;

        if (!item.hasItemMeta()) return;

        if (!item.getItemMeta().hasDisplayName()) return;

        if (!item.getItemMeta().getDisplayName().equals("§aToggle Vanish")) return;

        e.setCancelled(true);

        Player p = e.getPlayer();

        manager.setVanish(p, !manager.isVanished(p));

        p.sendMessage(manager.isVanished(p)
                ? "§eYou are now vanished"
                : "§cYou are now visible");
    }
}