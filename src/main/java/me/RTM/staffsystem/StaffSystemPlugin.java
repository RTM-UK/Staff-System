package me.yourname.staffsystem;

import me.yourname.staffsystem.commands.*;
import me.yourname.staffsystem.gui.*;
import me.yourname.staffsystem.managers.StaffManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class StaffSystemPlugin extends JavaPlugin {

    private StaffManager staffManager;

    @Override
    public void onEnable() {

        staffManager = new StaffManager(this);

        // EVENTS
        Bukkit.getPluginManager().registerEvents(staffManager, this);
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new StaffBookListener(), this);
        Bukkit.getPluginManager().registerEvents(new VanishToggleListener(staffManager), this);

        // COMMANDS
        getCommand("staff").setExecutor(new StaffCommand(staffManager));
        getCommand("vanish").setExecutor(new VanishCommand(staffManager));
        getCommand("freeze").setExecutor(new FreezeCommand(staffManager, this)); // IMPORTANT FIX
        getCommand("sc").setExecutor(new StaffChatCommand(staffManager));

        getLogger().info("StaffSystem enabled!");
    }
}
