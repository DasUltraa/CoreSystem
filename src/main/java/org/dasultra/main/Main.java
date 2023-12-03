package org.dasultra.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;
import org.dasultra.api.ServerAPI;
import org.dasultra.commands.admin.*;
import org.dasultra.commands.admin.CommandSpawn;
import org.dasultra.commands.player.*;
import org.dasultra.listener.JoinListener;
import org.dasultra.listener.QuitListener;

public final class Main extends JavaPlugin {


    public  static Main plugin;
    @Override
    public void onEnable() {

        plugin = this;

        new ServerAPI().startAPI();

        System.out.print("CoreSystem is running");

        getCommand("gamemode").setExecutor(new CommandGamemode());
        getCommand("feed").setExecutor(new CommandFeed());
        getCommand("heal").setExecutor(new CommandHeal());
        getCommand("fly").setExecutor(new CommandFly());
        getCommand("invsee").setExecutor(new CommandInventory());
        getCommand("enderchest").setExecutor(new CommandEnderchest());
        getCommand("time").setExecutor(new CommandTime());
        getCommand("spawn").setExecutor(new CommandSpawn());
        getCommand("setspawn").setExecutor(new CommandSetSpawn());
        getCommand("warp").setExecutor(new CommandWarp());
        getCommand("setwarp").setExecutor(new CommandSetwarp());


        getCommand("warp").setTabCompleter(new CommandWarp());
        getCommand("setwarp").setTabCompleter(new CommandSetwarp());

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.print("CoreSystem is shutting down");
    }
}
