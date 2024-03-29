package org.dasultra.api.messages;

import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.file.FileManager;

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
        addMessage("ChatBlocker", "%prefix% &cDo not curse!");
        addMessage("Commands.Gamemode.Survival", "%prefix% &aYou have set your Gamemode to &eSurvival&8.");
        addMessage("Commands.Gamemode.Creative", "%prefix% &aYou have set your Gamemode to &eCreative&8.");
        addMessage("Commands.Gamemode.Spectator", "%prefix% &aYou have set your Gamemode to &eSpectator&8.");
        addMessage("Commands.Gamemode.Adventure", "%prefix% &aYou have set your Gamemode to &eSurvival&8.");
        addMessage("Commands.Gamemode.Usage", "%prefix% &cUse /gamemode 0/1/2/3!");
        addMessage("Commands.Feed.Self", "%prefix% &aYou got feeded.");
        addMessage("Commands.Feed.Other", "%prefix% &aYou feeded &e%player%");
        addMessage("Commands.Heal.Self", "%prefix% &aYou got healed");
        addMessage("Commands.Heal.Other", "%prefix% &aYou healed &e%player%");
        addMessage("Commands.Fly", "%prefix% &7Your flight mode is now");
        addMessage("Commands.Vanish.On", "%prefix% &aYour vanish is now &eenabled&a.");
        addMessage("Commands.Vanish.Off", "%prefix% &cYour vanish is now &edisabled&c.");
        addMessage("Commands.Vanish.Usage", "%prefix% &cUse /vanish <player>!");
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
        addMessage("Commands.Rename.Success", "%prefix% &aYou renamed your item to &e%name%&a!");
        addMessage("Commands.Rename.Usage", "%prefix% &cUse /rename <name>");
        addMessage("Commands.Craft", "%prefix% &aYou opened your workbench");
        addMessage("Commands.Teleport.Self", "%prefix% &aYou got teleported to &e%player%&a.");
        addMessage("Commands.Teleport.Other", "%prefix% &aYou teleported &e%player1%&a to &e%player2%&a.");
        addMessage("Commands.Teleport.Here", "%prefix% &e%player% &agot teleported to you.");
        addMessage("Commands.Sign", "%prefix% &aYou have signed a item");
        addMessage("Commands.Clearchat", "%prefix% &c The chat got cleared by &e%player%&c.");
        addMessage("Commands.Ping.Self", "%prefix% &aYour ping is &e%ping%ms&a.");
        addMessage("Commands.Ping.Other", "%prefix% &e%player%'s &aPing is &e%ping%ms&a.");
        addMessage("Commands.Glow.NotFound", "%prefix% &cThe target has not been found!");
        addMessage("Commands.Sethome.Max", "%prefix% &cYou have set the maximum amount of Homes!");
        addMessage("Commands.Sethome.Set", "%prefix% &aThe Home &e%home% &ahas been succesfully set.");
        addMessage("Commands.Sethome.Usage", "%prefix% &cUse /sethome <name>!");
        addMessage("Commands.Sethome.NoUsername", "%prefix% &cYou are not allowed to use a username as a homename!");
        addMessage("Commands.Delhome.DoesNotExist", "%prefix% &cThe home &e%home% &cdoes not exist!");
        addMessage("Commands.Delhome.Success", "%prefix% &aYou succesfully deleted the home &e%home%&a.");
        addMessage("Commands.Home.Teleport", "%prefix% &aYou have been teleported to the home &e%home%&a.");
        addMessage("Commands.Home.Homes", "%prefix% &aYour available homes: &e%homes%&a.");
        addMessage("Commands.Tpa.NotPending", "%prefix% &cYou do not have a outgoing TPA request!");
        addMessage("Commands.Tpa.Usage", "%prefix% &cUse /tpa <player>!");
        addMessage("Commands.Tpa.Sent.Target", "%prefix% &e%player% &awants to teleport to you!");
        addMessage("Commands.Tpa.Sent.Player", "%prefix% &aTeleport request sent!");
        addMessage("Commands.Tpa.Accept.Sender", "%prefix% &e%player% &ahas accepted your TPA request.");
        addMessage("Commands.Tpa.Accept.Player", "%prefix% &aYou have accepted the TPA request.");
        addMessage("Commands.Tpa.Deny.Sender", "%prefix% &e%player% &chas denied your TPA request!");
        addMessage("Commands.Tpa.Deny.Player", "%prefix% &cYou have denied the TPA request.");
        addMessage("Commands.Tpahere.Usage", "%prefix% &cUse /tpahere <player>!");
        addMessage("Commands.Tpahere.Sent.Target", "%prefix% &e%player% &awants you to teleport to him!");
        addMessage("Commands.Tpahere.Sent.Player", "%prefix% &aTeleport request sent!");
        addMessage("Commands.Eco.UpdateMoney", "%prefix% &e%player% &7got now §c%money%$");
        addMessage("Commands.Eco.Usage", "%prefix% &cUse: /eco (Player) (set/add/remove) (Money)");
        addMessage("Commands.Money.Player", "%prefix% &aYou got &c%money%€&7.");
        addMessage("Commands.Money.Target", "%prefix% &7The Player &e%player% &7got &c%money%$&7.");
        addMessage("Commands.Pay.SuccessPlayer", "%prefix% &7You gave &e%player% &a%money%$ &7.");
        addMessage("Commands.Pay.SuccessTarget", "%prefix% &7You recieved &a%money%€ &7from &e%player% &7.");
        addMessage("Commands.Pay.Usage", "%prefix% &cUse: /pay (Player) (Money)");
        addMessage("Commands.Pay.NotEnoughMoney", "%prefix% &cYou do not have enough money!");
        addMessage("Commands.Pay.SelfPay", "%prefix% &cYou cannot pay yourself!");
        addMessage("Commands.Sun", "%prefix% &aThe &esun &ais shining!");
        addMessage("Commands.Rain", "%prefix% &aIt is now &eraining&a!");
        addMessage("Commands.Thunder", "%prefix% &aThe &ethunder &ais coming up!");
        addMessage("Commands.Restart", "%prefix% &cThe Server is restarting in %time% seconds!");
        addMessage("Commands.Broadcast.Usage", "%prefix% &cUse /broadcast <message>");
        addMessage("Commands.Message.Player", "%prefix% &8[&aYou &7-> &a%target%&8] &7%message%");
        addMessage("Commands.Message.Target", "%prefix% &8[&a%player% &7-> &aYou&8] &7%message%");
        addMessage("Commands.Message.Usage", "%prefix% &cUse /msg <player> <message>");
        addMessage("Commands.Message.CannotMessageSelf", "%prefix% &cYou cant send yourself a message");
        addMessage("Commands.Repair.Repaired", "%prefix% &aYou've repaired your item!");
        addMessage("Commands.Repair.Other", "%prefix% &aYou've repaired &e%player%'s &aitem!");
        addMessage("Commands.Repair.Air", "%prefix% &cYou can't repair air!");
        addMessage("Commands.Kick.Usage", "%prefix% &cUse /kick <Player> <Reason>");
        addMessage("Commands.Kick.PlayerNotFound", "%prefix% &cThe target has not been found!");
        addMessage("Commands.Kick.Success", "%prefix% &aYou have successfully kicked &e%player% &cdue to &e%reason%&c.");
        addMessage("Commands.Kick.Target", "%prefix% &cYou have been kicked from &e%server% &cdue to");
        addMessage("Commands.Ban.Success", "%prefix% &aYou have successfully banned &e%player% &cdue to &e%reason%&c.");
        addMessage("Commands.Lore.Success", "%prefix% &aYou have successfully set the lore to &e%lore%&a.");
        addMessage("Commands.Lore.Usage", "%prefix% &cUse /lore <lore>");
        addMessage("Commands.Day", "%prefix% &aYou have set the time to &eDay&8.");
        addMessage("Commands.Night", "%prefix% &aYou have set the time to &eNight&8.");


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
