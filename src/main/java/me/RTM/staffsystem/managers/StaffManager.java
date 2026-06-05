package me.RTM.staffsystem.managers;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class StaffManager implements Listener {

    private final Set<UUID> staff = new HashSet<>();
    private final Set<UUID> vanished = new HashSet<>();
    private final Set<UUID> frozen = new HashSet<>();

    private final JavaPlugin plugin;

    public StaffManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    public void toggleStaff(Player p) {

        if (staff.contains(p.getUniqueId())) {

            staff.remove(p.getUniqueId());

            setVanish(p, false);

            p.setAllowFlight(false);
            p.setFlying(false);

            p.getInventory().clear();

            p.sendMessage("§cStaff mode OFF");

        } else {

            staff.add(p.getUniqueId());

            setVanish(p, true);

            p.setAllowFlight(true);
            p.setFlying(true);

            giveStaffBook(p);
            giveVanishToggleItem(p);

            p.sendMessage("§aStaff mode ON");
        }
    }

    public boolean isStaff(Player p) {
        return staff.contains(p.getUniqueId());
    }


    public void setVanish(Player p, boolean v) {

        if (v) {
            vanished.add(p.getUniqueId());

            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.hasPermission("staff.see")) {
                    online.hidePlayer(plugin, p);
                }
            }

        } else {
            vanished.remove(p.getUniqueId());

            for (Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(plugin, p);
            }
        }
    }

    public boolean isVanished(Player p) {
        return vanished.contains(p.getUniqueId());
    }


    public void toggleFreeze(Player p) {

        if (frozen.contains(p.getUniqueId())) {
            frozen.remove(p.getUniqueId());
            p.sendMessage("§aUnfrozen");
        } else {
            frozen.add(p.getUniqueId());
            p.sendMessage("§cFrozen");
        }
    }

    public boolean isFrozen(Player p) {
        return frozen.contains(p.getUniqueId());
    }


    public void giveStaffBook(Player p) {

        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§bStaff Menu");
        item.setItemMeta(meta);

        p.getInventory().addItem(item);
    }

    public void giveVanishToggleItem(Player p) {

        ItemStack item = new ItemStack(Material.LIME_DYE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§aToggle Vanish");

        item.setItemMeta(meta);

        p.getInventory().addItem(item);
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (isFrozen(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    public boolean canSeeStaffChat(Player p) {
        return p.hasPermission("staff.chat");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        if (isStaff(p)) {

            e.setCancelled(true);

            String msg = "§5[STAFF] " + p.getName() + ": " + e.getMessage();

            for (Player online : Bukkit.getOnlinePlayers()) {
                if (online.hasPermission("staff.chat")) {
                    online.sendMessage(msg);
                }
            }
        }
    }
}
