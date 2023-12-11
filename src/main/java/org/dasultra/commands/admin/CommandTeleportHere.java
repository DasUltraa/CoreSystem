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

public class CommandTeleportHere implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("core.teleport" )) {
                if (args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);

                    t.teleport(p);
                    p.sendMessage(getMessage("Commands.Teleport.Here").replaceAll("%player%", t.getName()));
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
