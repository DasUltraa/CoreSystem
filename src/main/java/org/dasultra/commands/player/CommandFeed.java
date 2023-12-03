package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandFeed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (args.length == 0) {
                if (p.hasPermission("core.feed")) {
                    p.setFoodLevel(20);
                    p.sendMessage(getMessage("Commands.Feed.Self"));
                } else {
                    p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
                }
            } else if (args.length == 1) {

                Player t = Bukkit.getPlayer(args[0]);

                if (p.hasPermission("core.feed.other")) {
                    if (args[0].equalsIgnoreCase("all")) {
                         Player a = (Player) Bukkit.getOnlinePlayers();

                         a.setFoodLevel(20);
                         a.sendMessage(getMessage("Commands.Feed.Self"));
                    } else {
                        t.setFoodLevel(20);
                        t.sendMessage(getMessage("Commands.Feed.Self"));
                        p.sendMessage(getMessage("Commands.Feed.Other").replaceAll("%player%", t.getName()));
                    }
                }
            }
        }
        return false;
    }
}