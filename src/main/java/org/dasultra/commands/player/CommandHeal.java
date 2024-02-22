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

public class CommandHeal implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (args.length == 0) {
                if (p.hasPermission("core.heal")) {
                    p.setFoodLevel(20);
                    p.setHealth(20);
                    p.sendMessage(getMessage("Commands.Heal.Self"));
                } else {
                    p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
                }
            } else if (args.length == 1) {

                Player t = Bukkit.getPlayer(args[0]);

                if (p.hasPermission("core.heal.other")) {
                    if (args[0].equalsIgnoreCase("all")) {
                        Player a = (Player) Bukkit.getOnlinePlayers();

                        a.setFoodLevel(20);
                        a.setHealth(20);
                        a.sendMessage(getMessage("Commands.Heal.Self"));
                    } else {
                        t.setFoodLevel(20);
                        t.setHealth(20);
                        t.sendMessage(getMessage("Commands.Heal.Self"));
                        p.sendMessage(getMessage("Commands.Heal.Other").replaceAll("%player%", t.getName()));
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}