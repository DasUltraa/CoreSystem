package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;

import java.util.ArrayList;
import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandTPA implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                Player t = Bukkit.getPlayer(args[0]);
                if (t != null) {
                    ServerAPI.sendTpa(p, t);
                } else {
                    p.sendMessage(getMessage("Commands.Tpa.PlayerNotFound"));
                }
            } else {
                p.sendMessage(getMessage("Commands.Tpa.Usage"));
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
