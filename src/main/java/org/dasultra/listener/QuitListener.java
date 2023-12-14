package org.dasultra.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.dasultra.api.messages.Messages.getMessage;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        e.setQuitMessage(getMessage("QuitMessage").replaceAll("%player%", p.getName()));
    }
}