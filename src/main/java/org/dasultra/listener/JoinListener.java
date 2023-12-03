package org.dasultra.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.dasultra.api.messages.Messages.getMessage;

public class  JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        e.setJoinMessage(getMessage("JoinMessage").replaceAll("%player%", String.valueOf(p.getName())));
    }
}
