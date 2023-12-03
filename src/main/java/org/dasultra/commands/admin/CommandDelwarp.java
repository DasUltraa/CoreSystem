package org.dasultra.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.team.Warp;
import org.dasultra.api.messages.Messages;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandDelwarp implements CommandExecutor, TabCompleter {


    List<String> complete = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> result = new ArrayList<>();
        if (complete.size() != ServerAPI.getWarps().size()) {
            complete.clear();
        }
        if (sender instanceof Player p) {
            complete.addAll(ServerAPI.getWarps());
            if (args.length == 1) {
                for (String a : complete) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        result.add(a);
                    }
                }
                return result;
            }
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("core.delwarp")) {
                if (args.length == 1) {
                    String data = args[0];
                    Warp warp = new Warp(data);
                    if (warp.existWarp()) {
                        p.sendMessage(getMessage("Commands.Warp.Delete").replaceAll("%loc%", args[0]));
                        warp.delWarp();
                    } else {
                        p.sendMessage(getMessage("Commands.Warp.DoesNotExist"));
                    }
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }
        return false;
    }
}