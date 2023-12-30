package org.dasultra.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.messages.Messages;
import org.dasultra.main.Main;

import static org.dasultra.api.messages.Messages.getMessage;

public class RestartListener implements Listener {

    int restartTime = 5;

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/restart") || event
                .getMessage().equalsIgnoreCase("/stop") || event
                .getMessage().equalsIgnoreCase("/reload") || event
                .getMessage().equalsIgnoreCase("minecraft:restart") || event
                .getMessage().equalsIgnoreCase("minecraft:stop") || event
                .getMessage().equalsIgnoreCase("minecraft:reload") || event
                .getMessage().equalsIgnoreCase("minecraft:rl") || event
                .getMessage().equalsIgnoreCase("rl")) {
            if (event.getPlayer().hasPermission("core.restart")) {
                event.setCancelled(true);
                Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage(getMessage("Commands.Restart").replaceAll("%time%", String.valueOf(restartTime)));
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            onlinePlayer.sendTitle(getMessage("Commands.Restart").replaceAll("%time%", String.valueOf(restartTime)), "");
                        }

                        restartTime--;

                        if (restartTime < 0) {
                            Bukkit.shutdown();
                        }
                    }
                }, 0, 20L);
            } else {
                event.getPlayer().sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }

    }

}
