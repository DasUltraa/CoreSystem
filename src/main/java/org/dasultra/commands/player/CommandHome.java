package org.dasultra.commands.player;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.home.HomeSystem;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandHome implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] strings) {
        return new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                var user = new HomeSystem(p.getUniqueId());
                String home = args[0];
                if (user.hasHomes()) {
                    user.getHomes().forEach(list -> {
                        if (list.equalsIgnoreCase(home)) {
                            Location homeLocation = user.getHome(list);
                            if (homeLocation.getWorld() == null) {
                                return;
                            }
                            p.teleport(homeLocation);
                            p.sendMessage(getMessage("Commands.Home.Teleport").replaceAll("%home%", home));
                        }
                    });
                }
            } else {
                var user = new HomeSystem(p.getUniqueId());
                var homes = user.getHomes();
                p.sendMessage(getMessage("Commands.Home.Homes").replaceAll("%homes%", homes.toString()));
            }
        }
        return false;
    }
}