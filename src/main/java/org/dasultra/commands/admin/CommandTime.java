package org.dasultra.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (p.hasPermission("core.time")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("day")) {
                        p.getWorld().setTime(1000);
                        p.sendMessage(getMessage("Commands.Time.Time").replaceAll("%time%", args[0]));
                    } else if (args[0].equalsIgnoreCase("night")) {
                        p.getWorld().setTime(18000);
                        p.sendMessage(getMessage("Commands.Time.Time").replaceAll("%time%", args[0]));
                    }
                } else {
                    p.sendMessage(getMessage("Commands.Time.Usage"));
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }
        return false;
    }
}
