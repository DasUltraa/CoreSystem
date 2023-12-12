package org.dasultra.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.dasultra.api.File.FileManager;
import org.dasultra.api.builder.ItemBuilder;
import org.dasultra.api.messages.Messages;

import java.io.File;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ServerAPI {

    private static final LinkedList<String> allow = new LinkedList<>();
    protected static final LinkedList<String> warps = new LinkedList<>();

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


    public void startAPI() {
        FileManager manager = new FileManager("plugins/CoreSystem/Settings.yml");
        manager.add("Prefix", "&aCore &8»");
        manager.add("ScoreboardPrefix", "&aCore");
        manager.add("NoPerms", " &cDazu hast du keine Rechte!");

        Messages.initMessages();
        initMethodes();
    }

    public static String getDate() {
        Date currentDate = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
        return s.format(currentDate);
    }


    public static ItemStack getSkull(String name) {
        return new ItemBuilder(Material.PLAYER_HEAD).setDisplayName("§7Head of §b" + name).setOwningSkull(name).build();
    }

    public void initMethodes() {
        FileManager manager = new FileManager("plugins/CoreSystem/Methodes.yml");
        manager.add("MessageAPI", true);
    }

    public static void reloadWarps() {
        warps.clear();
        initWarp();
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
}
