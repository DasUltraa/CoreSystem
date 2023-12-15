package org.dasultra.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.home.HomeSystem;

import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandDeletehome implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                var user = new HomeSystem(p.getUniqueId());
                var data = args[0];

                if (!user.existHome(data)) {
                    p.sendMessage(getMessage("Commands.Delhome.DoesNotExist").replaceAll("%home%", args[0]));
                } else {
                    user.removeLocation(data);
                    user.removeHome(data);
                    p.sendMessage(getMessage("Commands.Delhome.Success").replaceAll("%home%", args[0]));
                }


            } else {
                var user = new HomeSystem(p.getUniqueId());
                var homes = user.getHomes();
                p.sendMessage(getMessage("Commands.Home.Homes").replaceAll("%homes%", homes.toString()));
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
