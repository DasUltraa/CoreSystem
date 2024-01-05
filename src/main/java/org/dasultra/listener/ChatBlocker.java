package org.dasultra.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.dasultra.api.file.FileManager;

import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class ChatBlocker implements Listener {


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        FileManager file = new FileManager("plugins/CoreSystem/ChatBlocker.yml");
        if (!file.exist()) {
            List<String> blocked_words = file.getStringList("blockedWords");

            blocked_words.add("nigga");
            blocked_words.add("niga");
            blocked_words.add("fuck");
            blocked_words.add("bitch");
            blocked_words.add("hoe");

            file.set("blockedWords", blocked_words);
            file.save();
        }

        List<String> blockedWords = file.getStringList("blockedWords");

        for (String blockedWord : blockedWords) {
            if (message.toLowerCase().contains(blockedWord.toLowerCase())) {
                Player p = event.getPlayer();
                if (p.hasPermission("core.chatblocker.bypass")) {
                } else {
                    event.setCancelled(true);
                    p.sendMessage(getMessage("ChatBlocker"));
                }

            }
        }
    }
}
