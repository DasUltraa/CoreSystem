package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;

import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandGlow implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("core.glow")) {
                if (args.length == 0) {
                    p.setGlowing(!p.isGlowing());
                } else if (args.length == 1) {
                    if (p.hasPermission("api.glow.target")) {
                        Player t = Bukkit.getPlayer(args[0]);
                        if (t == null) {
                            p.sendMessage(getMessage("Commands.Glow.NotFound"));
                        }
                        t.setGlowing(!t.isGlowing());
                    }
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
