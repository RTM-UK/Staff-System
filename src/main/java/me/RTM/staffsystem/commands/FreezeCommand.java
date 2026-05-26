package me.yourname.staffsystem.commands;

import me.yourname.staffsystem.managers.StaffManager;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;

public class FreezeCommand implements CommandExecutor {

    private final StaffManager manager;
    private final JavaPlugin plugin;

    public FreezeCommand(StaffManager manager, JavaPlugin plugin) {
        this.manager = manager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player staff)) return true;

        if (args.length == 0) {
            staff.sendMessage("§cUsage: /freeze <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            staff.sendMessage("§cPlayer not found.");
            return true;
        }

        boolean nowFrozen = !manager.isFrozen(target);
        manager.toggleFreeze(target);

        if (nowFrozen) {

            // FREEZE MESSAGE TO STAFF
            staff.sendMessage("§b[Staff] §fYou have frozen §e" + target.getName() + "§f.");

            // WARNING TITLE TO TARGET
            target.sendTitle(
                    "§cYOU ARE FROZEN",
                    "§7Do not log out",
                    10,
                    999999,
                    10
            );

            target.sendMessage(" ");
            target.sendMessage("§c⚠ You have been frozen by staff!");
            target.sendMessage("§c⚠ Do not log out or you may be punished!");
            target.sendMessage(" ");

        } else {

            staff.sendMessage("§a[Staff] §fYou have unfrozen §e" + target.getName() + "§f.");

            target.sendTitle(
                    "§aYOU ARE UNFROZEN",
                    "§7You may continue playing",
                    10,
                    60,
                    10
            );

            target.sendMessage("§aYou have been unfrozen by staff.");
        }

        return true;
    }
}
