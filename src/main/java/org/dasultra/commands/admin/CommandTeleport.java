package org.dasultra.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;

import javax.annotation.Nonnull;
import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandTeleport implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("core.teleport" )) {
                if (args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);

                    p.teleport(t);
                    p.sendMessage(getMessage("Commands.Teleport.Self").replaceAll("%player%", t.getName()));
                } else if (args.length == 2) {
                    if (p.hasPermission("core.teleport.other")) {
                        Player t1 = Bukkit.getPlayer(args[0]);
                        Player t2 = Bukkit.getPlayer(args[1]);

                        t1.teleport(t2);
                        p.sendMessage(getMessage("Commands.Teleport.Other").replaceAll("%player1", t1.getName()).replaceAll("%player2", t2.getName()));
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

    public List<String> getCompleteList() {
        return null;
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] strings) {
        return null;
    }
}
