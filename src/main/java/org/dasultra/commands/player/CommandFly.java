package org.dasultra.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandFly implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (p.hasPermission("core.fly")) {
                toggleFlight(p);
            }
        }
        return false;
    }

    private void toggleFlight(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(getMessage("Commands.Fly") + "§cdisabled.");
        } else {
            player.setAllowFlight(true);
            player.sendMessage(getMessage("Commands.Fly") + "§aenabled.");
        }
    }
}
