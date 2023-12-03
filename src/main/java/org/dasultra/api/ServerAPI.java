package org.dasultra.api;

import org.bukkit.Bukkit;
import org.dasultra.api.File.FileManager;
import org.dasultra.api.messages.Messages;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class ServerAPI {

    private static final LinkedList<String> allow = new LinkedList<>();

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

    public void initMethodes() {
        FileManager manager = new FileManager("plugins/CoreSystem/Methodes.yml");
        manager.add("MessageAPI", true);
    }
}
