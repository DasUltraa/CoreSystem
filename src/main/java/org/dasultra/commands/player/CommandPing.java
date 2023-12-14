package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandPing implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p){
            if (args.length == 0) {
                int ping = getPing(p);
                p.sendMessage(getMessage("Commands.Ping.Self").replaceAll("%ping%", String.valueOf(ping)));
            } else if (args.length == 1) {
                Player t = Bukkit.getPlayer(args[0]);
                int ping = getPing(t);
                p.sendMessage(getMessage("Commands.Ping.Other").replaceAll("%ping%", String.valueOf(ping)).replaceAll("%player%", t.getName()));
            }
        }
        return false;
    }

    private int getPing(Player p) {
        // Get the player's ping using the getLatency() method
        return p.getPing();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
