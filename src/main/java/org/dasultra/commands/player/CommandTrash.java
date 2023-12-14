package org.dasultra.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dasultra.api.builder.InventoryBuilder;

public class CommandTrash implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            p.openInventory(new InventoryBuilder(9 * 6, "§aTrash").build());
        }
        return false;
    }
}
