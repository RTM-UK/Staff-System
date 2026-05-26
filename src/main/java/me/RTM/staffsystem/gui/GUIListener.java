package me.yourname.staffsystem.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.Event;

public class GUIListener implements Listener {

    private boolean isGUI(String title) {
        return title != null && (
                title.equals("§dPlayer Selector") ||
                        title.startsWith("§dInspect: ")
        );
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        String title = e.getView().getTitle();

        if (!isGUI(title)) return;

        e.setCancelled(true);
        e.setResult(Event.Result.DENY);

        if (!(e.getWhoClicked() instanceof Player staff)) return;

        if (title.equals("§dPlayer Selector")) {

            if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()) return;

            String name = e.getCurrentItem()
                    .getItemMeta()
                    .getDisplayName()
                    .replace("§e", "");

            Player target = Bukkit.getPlayer(name);
            if (target == null) return;

            staff.openInventory(PlayerActionGUI.create(target));
        }

        if (title.startsWith("§dInspect: ")) {

            if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()) return;

            String action = e.getCurrentItem().getItemMeta().getDisplayName();

            String targetName = title.replace("§dInspect: ", "");
            Player target = Bukkit.getPlayer(targetName);

            if (target == null) return;

            if (action.equals("§aView Inventory")) {
                staff.openInventory(PlayerViewGUI.inv(target));
            }

            if (action.equals("§5View Ender Chest")) {
                staff.openInventory(PlayerViewGUI.ec(target));
            }
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (isGUI(e.getView().getTitle())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCreative(InventoryCreativeEvent e) {
        if (isGUI(e.getView().getTitle())) {
            e.setCancelled(true);
        }
    }
}
