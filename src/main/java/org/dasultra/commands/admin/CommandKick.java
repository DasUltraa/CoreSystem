package org.dasultra.commands.admin;

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

public class CommandKick implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("core.kick")) {
                if (args.length >= 2) {
                    Player t = Bukkit.getPlayer(args[0]);

                    if (!t.isOnline()) {
                        p.sendMessage("Commands.Kick.PlayerNotFound");
                    } else {
                        String msg = "";
                        for (int i = 1; i < args.length; i++)
                            msg = msg + args[i] + " ";
                        t.kickPlayer(getMessage("Commands.Kick.Target").replaceAll("%server%", ServerAPI.getServerName()) + "\n" + "§e" + msg);
                        p.sendMessage(getMessage("Commands.Kick.Success").replaceAll("%reason%", msg).replaceAll("%player%", t.getName()));
                    }
                } else {
                    p.sendMessage(getMessage("Commands.Kick.Usage"));
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List <String> list = new ArrayList<>();
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        } else if (args.length == 2) {
            list.add("Reason");
        }
        return list;
    }
}
