package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandEnderchest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (p.hasPermission("core.ec")) {
                if (args.length == 0) {
                    p.openInventory(p.getEnderChest());
                    p.sendMessage(getMessage("Commands.Enderchest.Self"));
                } else if (args.length == 1) {
                    if (p.hasPermission("core.ec.other")) {
                        Player t = Bukkit.getPlayer(args[0]);

                        p.openInventory(t.getEnderChest());
                        p.sendMessage(getMessage("Commands.Enderchest.Other").replaceAll("%player%", t.getName()));
                    } else {
                        p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
                    }
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }
        return false;
    }
}
