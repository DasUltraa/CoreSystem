package org.dasultra.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.home.HomeSystem;

import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandSethome implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {

            if (args.length == 1) {
                var user = new HomeSystem (p.getUniqueId());
                var data = args[0];

                if (data.equalsIgnoreCase(p.getName())) {
                    p.sendMessage(getMessage("Commands.Sethome.NoUsername"));
                }

                if (user.getHomes().size() >= user.getMaxHomes()) {
                    if (user.getHomes().contains(data)) {
                        user.saveLocation(data, p.getLocation());

                    }
                    p.sendMessage(getMessage("Commands.Sethome.Max"));
                }
                user.addHome(data);
                user.saveLocation(data, p.getLocation());
                p.sendMessage(getMessage("Commands.Sethome.Set").replaceAll("%home%", args[0]));

            } else {
                p.sendMessage(getMessage("Commands.Sethome.Usage"));
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
