package me.RTM.staffsystem.commands;

import me.RTM.staffsystem.managers.StaffManager;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private final StaffManager manager;

    public VanishCommand(StaffManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        manager.setVanish(p, !manager.isVanished(p));

        p.sendMessage(manager.isVanished(p) ? "§eVanish enabled" : "§cVanish disabled");

        return true;
    }
}
