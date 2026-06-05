package me.RTM.staffsystem.commands;

import me.RTM.staffsystem.managers.StaffManager;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class StaffCommand implements CommandExecutor {

    private final StaffManager manager;

    public StaffCommand(StaffManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        manager.toggleStaff(p);

        return true;
    }
}
