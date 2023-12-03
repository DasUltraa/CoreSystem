package org.dasultra.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.dasultra.api.ServerAPI;
import org.dasultra.commands.admin.CommandGamemode;
import org.dasultra.commands.player.CommandFeed;
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

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.print("CoreSystem is shutting down");
    }
}
