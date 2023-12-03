package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.team.Warp;
import org.dasultra.api.messages.Messages;

import java.util.ArrayList;
import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandWarp implements CommandExecutor, TabCompleter {

    private static final Inventory inv = Bukkit.createInventory(null, 9 * 6, "§aWarps");
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
            if (args.length == 1) {
                String data = args[0];
                Warp warp = new Warp(data);
                if (warp.existWarp()) {
                    if (warp.getLocation().getWorld() == null) {
                        p.sendMessage("test");
                    }
                    warp.teleport(p);
                    p.sendMessage(getMessage("Commands.Warp.Teleported"));
                } else {
                    p.sendMessage(getMessage("Commands.Warp.DoesNotExist"));
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }

        }  return false;
    }
}