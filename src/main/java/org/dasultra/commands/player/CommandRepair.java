package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;

import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandRepair implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("core.repair")) {
                if (args.length == 0) {
                    if (!(p.getItemInHand().getType() == Material.AIR)) {
                        p.getItemInHand().setDurability((short) 0);
                        p.sendMessage(getMessage("Commands.Repair.Repaired"));
                    } else {
                        p.sendMessage(getMessage("Commands.Repair.Air"));
                    }

                } else if (args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);

                    if (t.isOnline()) {
                        if (!(t.getItemInHand().getType() == Material.AIR)) {
                            t.getItemInHand().setDurability((short) 0);
                            t.sendMessage(getMessage("Commands.Repair.Repaired"));
                            p.sendMessage(getMessage("Commands.Repair.Other").replaceAll("%player%", t.getName()));
                        } else {
                            p.sendMessage(getMessage("Commands.Repair.Air"));
                        }
                    }

                } else {
                    p.sendMessage(getMessage("Commands.Repair.Usage"));
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
