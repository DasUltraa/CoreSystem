package org.dasultra.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AntiDasUltraKill implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/kill DasUltra") || event.getMessage().equalsIgnoreCase("/minecraft:kill DasUltra") || event.getMessage().equalsIgnoreCase("/minecraft:kick DasUltra") || event.getMessage().equalsIgnoreCase("/minecraft:ban DasUltra") || event.getMessage().equalsIgnoreCase("/minecraft:minecraft:ban DasUltra")) {
            event.setCancelled(true);
            event.getPlayer().kickPlayer("Don't mess with me");
            Bukkit.broadcastMessage("§c" + event.getPlayer().getName() + " §cjust tried to troll DasUltra WHAT A FOOL");
        }

    }
}
