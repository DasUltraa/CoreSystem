package org.dasultra.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.dasultra.api.ServerAPI;
import org.dasultra.commands.admin.*;
import org.dasultra.commands.admin.CommandSpawn;
import org.dasultra.commands.player.*;
import org.dasultra.listener.ChatBlocker;
import org.dasultra.listener.JoinListener;
import org.dasultra.listener.QuitListener;

public final class Main extends JavaPlugin {

    public static Main plugin;

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
        getCommand("delwarp").setExecutor(new CommandDelwarp());
        getCommand("head").setExecutor(new CommandHead());
        getCommand("clear").setExecutor(new CommandClear());
        getCommand("craft").setExecutor(new CommandCraft());
        getCommand("rename").setExecutor(new CommandRename());
        getCommand("teleport").setExecutor(new CommandTeleport());
        getCommand("teleporthere").setExecutor(new CommandTeleportHere());
        getCommand("sign").setExecutor(new CommandSign());
        getCommand("clearchat").setExecutor(new CommandClearChat());
        getCommand("trash").setExecutor(new CommandTrash());
        getCommand("glow").setExecutor(new CommandGlow());
        getCommand("ping").setExecutor(new CommandPing());
        getCommand("sethome").setExecutor(new CommandSethome());
        getCommand("home").setExecutor(new CommandHome());
        getCommand("delhome").setExecutor(new CommandDeletehome());
        getCommand("tpa").setExecutor(new CommandTPA());
        getCommand("tpaccept").setExecutor(new CommandTPAccept());
        getCommand("tpdeny").setExecutor(new CommandTPDeny());
        getCommand("tpahere").setExecutor(new CommandTPAHere());

        getCommand("warp").setTabCompleter(new CommandWarp());
        getCommand("setwarp").setTabCompleter(new CommandSetwarp());
        getCommand("delwarp").setTabCompleter(new CommandDelwarp());
        getCommand("head").setTabCompleter(new CommandHead());
        getCommand("clear").setTabCompleter(new CommandClear());
        getCommand("inventory").setTabCompleter(new CommandInventory());
        getCommand("teleport").setTabCompleter(new CommandTeleport());
        getCommand("teleporthere").setTabCompleter(new CommandTeleportHere());
        getCommand("glow").setTabCompleter(new CommandGlow());
        getCommand("ping").setTabCompleter(new CommandPing());
        getCommand("sethome").setTabCompleter((new CommandSethome()));
        getCommand("home").setTabCompleter((new CommandHome()));
        getCommand("delhome").setTabCompleter((new CommandDeletehome()));
        getCommand("tpa").setTabCompleter(new CommandTPA());
        getCommand("tpaccept").setTabCompleter(new CommandTPAccept());
        getCommand("tpdeny").setTabCompleter(new CommandTPDeny());
        getCommand("tpahere").setTabCompleter(new CommandTPAHere());

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new ChatBlocker(), this);

        ServerAPI.initWarp();
    }

    @Override
    public void onDisable() {
        System.out.print("CoreSystem is shutting down");
    }

}