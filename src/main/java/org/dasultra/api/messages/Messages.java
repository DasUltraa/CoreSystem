package org.dasultra.api.messages;

import org.bukkit.entity.Player;
import org.dasultra.api.File.FileManager;
import org.dasultra.api.ServerAPI;

import java.util.ArrayList;

public class Messages {

    public static void initMessages() {
        Messages();
    }

    public static void addMessage(String path, Object o) {
        FileManager manager = new FileManager("plugins/CoreSystem/Messages.yml");
        manager.add(path, o);
    }

    public static void Messages() {

        addMessage("JoinMessage", "&8[&a+&8] &7%player%");
        addMessage("QuitMessage", "&8[&c-&8] &7%player%");
        addMessage("Commands.Gamemode.Survival", "%prefix% &aYou have set your Gamemode to &eSurvival&8.");
        addMessage("Commands.Gamemode.Creative", "%prefix% &aYou have set your Gamemode to &eCreative&8.");
        addMessage("Commands.Gamemode.Spectator", "%prefix% &aYou have set your Gamemode to &eSpectator&8.");
        addMessage("Commands.Gamemode.Adventure", "%prefix% &aYou have set your Gamemode to &eSurvival&8.");
        addMessage("Commands.Gamemode.Usage", "%prefix% &cUse /gamemode 0/1/2/3!");
        addMessage("Commands.Feed.Self", "%prefix% &aYou got feeded.");
        addMessage("Commands.Feed.Other", "%prefix% &aYou feeded &e%player%");
        addMessage("Commands.Heal.Self", "%prefix% &aYou got healed");
        addMessage("Commands.Heal.Other", "%prefix% &aYou healed &e%player%");
        addMessage("Commands.Fly", "%prefix% &7Your flight mode is now ");
        addMessage("Commands.Invsee", "%prefix% &aYou opened &e%player%'s &ainventory");
        addMessage("Commands.Enderchest.Self", "%prefix% &aYou opened your enderchest");
        addMessage("Commands.Enderchest.Other", "%prefix% &aYou opened &e%player%'s &aenderchest");
        addMessage("Commands.Time.Time", "%prefix% &aYou have set the time to &e%time%&a.");
        addMessage("Commands.Time.Usage", "%prefix% &cUse /time day/night");
        addMessage("Commands.Spawn", "%prefix% &aYou have been teleported to the Spawn.");
        addMessage("Commands.Setspawn", "%prefix% &aYou have successfully set the Spawnlocation.");
        addMessage("Commands.Warp.Teleported", "%prefix% &aYou have been teleported to &e%loc%&a.");
        addMessage("Commands.Warp.Set", "%prefix% &aYou have successfully created the warp &e%loc%.");
        addMessage("Commands.Warp.DoesNotExist", "%prefix% &cThis warp does not exist!");
        addMessage("Commands.Warp.Delete", "%prefix% &aYou have successfully deleted the warp &e%loc%&a.");
        addMessage("Commands.Skull.Skull", "%prefix% &aYou got &e%player% &aHead.");
        addMessage("Commands.Skull.Usage", "%prefix% &cUse /head <player>!");
        addMessage("Commands.Clear.Clear", "%prefix% &aYou cleared &e%player%'s &ainventory");
        addMessage("Commands.Clear.Usage", "%prefix% &cUse /clear <player>!");
        addMessage("Commands.Rename", "%prefix% &aYou item got renamed to %name%!");
        addMessage("Commands.Workbench", "%prefix% &aYou opened your workbench");
    }

    public static String getMessage(String path) {
        FileManager manager = new FileManager("plugins/CoreSystem/Messages.yml");
        return manager.getString(path).replaceAll("%prefix%", ServerAPI.getPrefix()).replaceAll("&", "§");
    }

    public static void setMessage(Player p, String path, String message) {
        FileManager manager = new FileManager("plugins/CoreSystem/Messages.yml");
        if (manager.isSet(path)) {
            manager.set(path, message);
            manager.save();
            p.sendMessage(ServerAPI.getPrefix() + " §7Die Nachricht von §e" + path + " §7sieht jetzt so aus:");
            p.sendMessage("");
            p.sendMessage(getMessage(path));
        } else {
            p.sendMessage(ServerAPI.getPrefix() + " §cDieser Pfad ist nicht vorhanden!");
        }
    }

    public static ArrayList<String> getMessagePaths() {
        ArrayList<String> paths = new ArrayList<>();
        return paths;
    }
}
