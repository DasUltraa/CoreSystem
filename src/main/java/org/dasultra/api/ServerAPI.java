package org.dasultra.api;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.dasultra.api.file.FileManager;
import org.dasultra.api.builder.ItemBuilder;
import org.dasultra.api.home.HomeSystem;
import org.dasultra.api.messages.Messages;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public class ServerAPI {

    protected static final LinkedList<String> warps = new LinkedList<>();
    private static final LinkedList<String> allow = new LinkedList<>();
    protected static final HashMap<Player, Player> tpa = new HashMap<>();
    protected static final HashMap<Player, Player> tpaHere = new HashMap<>();

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

    public HomeSystem getHomeSystem(UUID uuid){
        return new HomeSystem(uuid);
    }

    public static void acceptTpa(Player target) {
        if (tpa.containsKey(target)) {
            Player sender = tpa.get(target);
            sender.teleport(target);
            sender.sendMessage(getPrefix() + "§e" + target.getName() + " §7hat die Teleport Anfrage angemommen!");
            target.sendMessage(getPrefix() + "§aDu hast die Anfrage angenommen!");
            tpa.remove(target);
        } else if (tpaHere.containsKey(target)) {
            acceptTpaHere(target);
        } else {
            target.sendMessage(getPrefix() + "§cDu hast keine Anfragen!");
        }
    }

    public static void sendTpaHere(Player sender, Player target) {
        tpaHere.put(target, sender);
        sender.sendMessage(getPrefix() + "§7Die Anfrage wurde gesendet an §a" + target.getName() + "§7!");
        target.sendMessage(getPrefix() + "§7Du hast eine Anfrage erhalten von §c" + sender.getName() + "§7, das du dich zu ihm/ihr teleportierst!");
        sendClickableMessage(target, getPrefix() + "§7Nehme an mit ", "§a/tpaccept", "§7Nimm die Teleport Anfrage an!", "tpaccept");
        sendClickableMessage(target, getPrefix() + "§7Lehne ab mit ", "§c/tpdeny", "§7Lehne die Teleport Anfrage ab!", "tpdeny");
    }

    public static void sendTpa(Player sender, Player target) {
        tpa.put(target, sender);
        sender.sendMessage(getPrefix() + "§7Du hast eine Teleport Anfrage an §c" + target.getName() + " §7gesendet!");
        target.sendMessage(getPrefix() + "§7Du hast eine Teleport Anfrage erhalten von §c" + sender.getName() + "§7!");
        sendClickableMessage(target, getPrefix() + "§7Nehme an mit ", "§a/tpaccept", "§7Nimm die Teleport Anfrage an!", "tpaccept");
        sendClickableMessage(target, getPrefix() + "§7Lehne ab mit ", "§c/tpdeny", "§7Lehne die Teleport Anfrage ab!", "tpdeny");
    }

    private static void acceptTpaHere(Player target) {
        if (tpaHere.containsKey(target)) {
            Player sender = tpaHere.get(target);
            target.teleport(sender);
            sender.sendMessage(getPrefix() + "§e" + target.getName() + " §7hat die Teleport Anfrage angemommen!");
            target.sendMessage(getPrefix() + "§aDu hast die Anfrage angenommen!");
            tpaHere.remove(target);
        } else {
            target.sendMessage(getPrefix() + "§cDu hast keine Anfragen!");
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

    public void startAPI() {
        FileManager manager = new FileManager("plugins/CoreSystem/Settings.yml");
        manager.add("Prefix", "&aCore &8»");
        manager.add("ScoreboardPrefix", "&aCore");
        manager.add("NoPerms", " &cDazu hast du keine Rechte!");

        Messages.initMessages();
        initMethodes();
    }

    public void initMethodes() {
        FileManager manager = new FileManager("plugins/CoreSystem/Methodes.yml");
        manager.add("MessageAPI", true);
    }

    public static void sendClickableMessage(Player p, String message, String clickMessage, String hoverMessage, String command) {
        TextComponent raw = new TextComponent(message);
        TextComponent text = new TextComponent(clickMessage);
        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hoverMessage)));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));
        p.spigot().sendMessage(raw, text);
    }

}
