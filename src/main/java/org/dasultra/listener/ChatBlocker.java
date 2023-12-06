package org.dasultra.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.dasultra.api.File.FileManager;
import org.dasultra.api.ServerAPI;

import java.util.List;

public class ChatBlocker implements Listener {


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        FileManager manager = new FileManager("plugins/CoreSystem/ChatBlocker.yml");
        manager.add("blocker-words", 1);
        List<String> blockedWords = manager.getStringList("blocked-words");

        for (String blockedWord : blockedWords) {
            if (message.toLowerCase().contains(blockedWord.toLowerCase())) {
                event.setMessage(null);
            }
        }
    }
}