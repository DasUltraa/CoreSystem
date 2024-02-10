package org.dasultra.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.dasultra.api.Database.DataBaseManager;
import org.dasultra.api.ServerAPI;
import org.dasultra.commands.admin.*;
import org.dasultra.commands.player.*;
import org.dasultra.listener.*;

import java.sql.SQLException;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {

        plugin = this;

        new ServerAPI().startAPI();
        try {
            DataBaseManager.connect();
            Bukkit.getConsoleSender().sendMessage(ServerAPI.getPrefix() + " §aMySQL connected Successfully!");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ServerAPI.getPrefix() + " §cError, MySQL couldn't connect!");
            throw new RuntimeException(e);
        }

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
        getCommand("pay").setExecutor(new CommandPay());
        getCommand("money").setExecutor(new CommandMoney());
        getCommand("eco").setExecutor(new CommandEco());
        getCommand("sun").setExecutor(new CommandSun());
        getCommand("rain").setExecutor(new CommandRain());
        getCommand("thunder").setExecutor(new CommandThunder());
        getCommand("vanish").setExecutor(new CommandVanish());
        getCommand("broadcast").setExecutor(new CommandBroadcast());
        getCommand("message").setExecutor(new CommandMSG());
        getCommand("repair").setExecutor(new CommandRepair());
        getCommand("kick").setExecutor(new CommandKick());
        getCommand("lore").setExecutor(new CommandLore());
        getCommand("day").setExecutor(new CommandDay());
        getCommand("night").setExecutor(new CommandNight());

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
        getCommand("vanish").setTabCompleter(new CommandVanish());
        getCommand("message").setTabCompleter(new CommandMSG());
        getCommand("repair").setTabCompleter(new CommandRepair());
        getCommand("kick").setTabCompleter(new CommandKick());
        getCommand("lore").setTabCompleter(new CommandLore());


        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new ChatBlocker(), this);
        getServer().getPluginManager().registerEvents(new ScoreboardListener(), this);
        getServer().getPluginManager().registerEvents(new RestartListener(), this);
        getServer().getPluginManager().registerEvents(new AntiDasUltraKill(), this);

        ServerAPI.initWarp();
    }

    @Override
    public void onDisable() {
        plugin = null;
        System.out.print("CoreSystem is shutting down");

        try {
            DataBaseManager.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}