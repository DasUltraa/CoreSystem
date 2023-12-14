package org.dasultra.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.dasultra.api.file.FileManager;

import java.util.List;

public class ChatBlocker implements Listener {


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        FileManager manager = new FileManager("plugins/CoreSystem/ChatBlocker.yml");
        manager.add("blocked-words", 0);
        List<String> blockedWords = manager.getStringList("blocked-words");

        for (String blockedWord : blockedWords) {
            if (message.toLowerCase().contains(blockedWord.toLowerCase())) {
                event.setMessage(null);
            }
        }
    }
}
