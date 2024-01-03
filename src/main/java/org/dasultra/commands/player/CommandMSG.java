package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandMSG implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (args.length > 1) {
                Player t = Bukkit.getPlayer(args[0]);
                if (t != null) {
                    String msg = "";
                    for (int i = 1; i < args.length; i++)
                        msg = msg + args[i] + " ";
                    if (args[0] == p.getName()) {
                        p.sendMessage("Commands.Message.CannotMessageSelf");
                    } else {
                        p.sendMessage(getMessage("Commands.Message.Player").replaceAll("%target%", t.getName()).replaceAll("%message%", msg));
                        t.sendMessage(getMessage("Commands.Message.Target").replaceAll("%player%", p.getName()).replaceAll("%message%", msg));
                    }

                } else {
                    p.sendMessage(getMessage("Commands.Message.NotFound"));
                }
            } else {
                p.sendMessage(getMessage("Commands.Message.Usage"));
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
