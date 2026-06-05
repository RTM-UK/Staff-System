package me.RTM.staffsystem.commands;

import me.RTM.staffsystem.managers.StaffManager;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffChatCommand implements CommandExecutor {

    private final StaffManager manager;
    private final Set<UUID> staffChatEnabled = new HashSet<>();

    public StaffChatCommand(StaffManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        if (!manager.isStaff(p)) {
            p.sendMessage("§cYou are not in staff mode.");
            return true;
        }

        UUID uuid = p.getUniqueId();

        if (args.length == 1 &&
                (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off"))) {

            boolean enable = args[0].equalsIgnoreCase("on");

            if (enable) {

                staffChatEnabled.add(uuid);
                p.sendMessage("§aYou are now in §dStaff Chat§a mode.");
                p.sendMessage("§7All your messages will go to staff chat.");

            } else {

                staffChatEnabled.remove(uuid);
                p.sendMessage("§cYou left §dStaff Chat§c mode.");
            }

            return true;
        }

        if (staffChatEnabled.contains(uuid)) {

            String msg = String.join(" ", args);

            if (msg.isEmpty()) {
                p.sendMessage("§cUsage: /sc <message> or /sc off");
                return true;
            }

            sendStaffMessage(p, msg);
            return true;
        }

        if (args.length == 0) {
            p.sendMessage("§cUsage: /sc <message> or /sc on|off");
            return true;
        }

        String msg = String.join(" ", args);
        sendStaffMessage(p, msg);

        return true;
    }

    private void sendStaffMessage(Player sender, String msg) {

        String format =
                "§8[§5SC§8] §d" +
                        sender.getName() +
                        " §7» §f" +
                        msg;

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("staff.chat")) {
                p.sendMessage(format);
            }
        }
    }
}
