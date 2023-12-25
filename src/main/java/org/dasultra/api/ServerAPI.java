package org.dasultra.api;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.dasultra.api.builder.ItemBuilder;
import org.dasultra.api.economy.EconomyAPI;
import org.dasultra.api.file.FileManager;
import org.dasultra.api.home.HomeSystem;
import org.dasultra.api.messages.Messages;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

import static org.dasultra.api.messages.Messages.getMessage;

public class ServerAPI {

    protected static final LinkedList<String> warps = new LinkedList<>();
    protected static final HashMap<Player, Player> tpa = new HashMap<>();
    protected static final HashMap<Player, Player> tpaHere = new HashMap<>();

    private static final LinkedList<String> allow = new LinkedList<>();

    public static String getHost() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Host");
    }

    public static String getPort() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Port");
    }

    public static String getDatabase() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Database");
    }

    public static String getUsername() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Username");
    }

    public static String getPassword() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");
        return mysql.getString("Password");
    }

    public static boolean getMethodes(String meth) {
        FileManager manager = new FileManager("plugins/CoreSystem/Methodes.yml");
        return manager.getBoolean(meth);
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    public static String getPrefix() {
        FileManager manager = new FileManager("plugins/CoreSystem/Settings.yml");
        return manager.getString("Prefix").replaceAll("&", "§");
    }

    public static String getNoPerms() {
        FileManager manager = new FileManager("plugins/CoreSystem/Settings.yml");
        return manager.getString("NoPerms").replaceAll("&", "§");
    }

    public static String getServerName() {
        FileManager manager = new FileManager("plugins/CoreSystem/Settings.yml");
        return manager.getString("ServerName").replaceAll("&", "§");
    }

    public static String renderValueForSave(double v) {
        DecimalFormat format = new DecimalFormat("####.##");
        return format.format(v);
    }

    public static String getDate() {
        Date currentDate = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
        return s.format(currentDate);
    }

    public static ItemStack getSkull(String name) {
        return new ItemBuilder(Material.PLAYER_HEAD).setDisplayName("§7Head of §b" + name).setOwningSkull(name).build();
    }

    public static void reloadWarps() {
        warps.clear();
        initWarp();
    }

    public static void acceptTpa(Player target) {
        if (tpa.containsKey(target)) {
            Player sender = tpa.get(target);
            sender.teleport(target);
            sender.sendMessage(getMessage("Commands.Tpa.Accept.Sender").replaceAll("%player%", sender.getName()));
            target.sendMessage(getMessage("Commands.Tpa.Accept.Player"));
            tpa.remove(target);
        } else if (tpaHere.containsKey(target)) {
            acceptTpaHere(target);
        } else {
            target.sendMessage(getMessage("Commands.Tpa.NotPending"));
        }
    }

    public static void sendTpaHere(Player sender, Player target) {
        tpaHere.put(target, sender);
        sender.sendMessage(getMessage("Commands.Tpahere.Sent.Player"));
        target.sendMessage(getMessage("Commands.Tpahere.Sent.Target").replaceAll("%player%", sender.getName()));
        sendClickableMessage(target, getPrefix() + "§aAccept: ", "§a/tpaccept", "§7Accept!", "tpaccept");
        sendClickableMessage(target, getPrefix() + "§cDeny: ", "§c/tpdeny", "§7Deny!", "tpdeny");
    }

    public static void sendTpa(Player sender, Player target) {
        tpa.put(target, sender);
        sender.sendMessage(getMessage("Commands.Tpa.Sent.Player"));
        target.sendMessage(getMessage("Commands.Tpa.Sent.Target").replaceAll("%player%", sender.getName()));
        sendClickableMessage(target, getPrefix() + "§aAccept: ", "§a/tpaccept", "§7Accept!", "tpaccept");
        sendClickableMessage(target, getPrefix() + "§cDeny: ", "§c/tpdeny", "§7Deny!", "tpdeny");
    }

    private static void acceptTpaHere(Player target) {
        if (tpaHere.containsKey(target)) {
            Player sender = tpaHere.get(target);
            target.teleport(sender);
            sender.sendMessage(getMessage("Commands.Tpahere.Accept.Sender").replaceAll("%player%", sender.getName()));
            target.sendMessage(getMessage("Commands.Tpahere.Accept.Player"));
            tpaHere.remove(target);
        } else {
            target.sendMessage(getMessage("Commands.Tpa.NotPending"));
        }
    }

    public static void denyTpa(Player target) {
        if (tpa.containsKey(target)) {
            Player sender = tpa.get(target);
            sender.sendMessage(getMessage("Commands.Tpa.Deny"));
            tpa.remove(target);
        } else if (tpaHere.containsKey(target)) {
            Player sender = tpaHere.get(target);
            target.sendMessage(getMessage("Commands.Tpa.Deny"));
            tpaHere.remove(target);
        } else {
            target.sendMessage(getMessage("Commands.Tpa.NotPending"));
        }
    }

    public static LinkedList<String> getWarps() {
        return warps;
    }

    public static void initWarp() {
        File file = new File("plugins/CoreSystem/warp/");
        File[] files = file.listFiles();
        if (files != null) {
            for (File object : files) {
                warps.add(object.getName().replace(".yml", ""));
            }
        }
    }

    public static void sendClickableMessage(Player p, String message, String clickMessage, String hoverMessage, String command) {
        TextComponent raw = new TextComponent(message);
        TextComponent text = new TextComponent(clickMessage);
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hoverMessage)));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));
        p.spigot().sendMessage(raw, text);
    }

    public HomeSystem getHomeSystem(UUID uuid) {
        return new HomeSystem(uuid);
    }

    public void startAPI() {
        FileManager manager = new FileManager("plugins/CoreSystem/Settings.yml");
        manager.add("ServerName", "&aDeinServer.de");
        manager.add("Prefix", "&aCore &8»");
        manager.add("ScoreboardPrefix", "&aCore");
        manager.add("NoPerms", " &cYou do not have the perms to use this command!");

        Messages.initMessages();
        initMethodes();

        databaseConfig();
    }

    private void databaseConfig() {
        FileManager mysql = new FileManager("plugins/CoreSystem/MySQL.yml");

        mysql.add("Host", "127.0.0.1");
        mysql.add("Port", "3306");
        mysql.add("Username", "user");
        mysql.add("Password", "password");
        mysql.add("Database", "database");

        mysql.save();
    }

    public void initMethodes() {
        FileManager manager = new FileManager("plugins/CoreSystem/Methodes.yml");
        manager.add("MessageAPI", true);
    }

}
