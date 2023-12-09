package org.dasultra.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandCraft implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            p.openWorkbench(null, true);
            p.sendMessage(getMessage("Commands.Craft"));
        }
        return false;
    }
}
