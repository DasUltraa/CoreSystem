package org.dasultra.commands.player;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.dasultra.api.ServerAPI;

import javax.annotation.Nonnull;
import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandHead implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (args.length == 1) {
                String name = args[0];
                if (p.hasPermission("core.head.other")) {
                    ItemStack skull = ServerAPI.getSkull(name);
                    p.getInventory().addItem(new ItemStack(skull));
                    p.sendMessage(getMessage("Commands.Skull.Skull").replaceAll("%player%", name + "'s"));
                } else {
                    p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
                }
            } else if (args.length == 0) {
                if (p.hasPermission("core.head")) {
                    ItemStack skull = ServerAPI.getSkull(p.getName());
                    p.getInventory().addItem(new ItemStack(skull));
                    p.sendMessage(getMessage("Commands.Skull.Skull").replaceAll("%player%", "your"));
                } else {
                    p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
                }
            } else {
                p.sendMessage(getMessage("Commands.Skull.Usage"));
            }

        }
        return false;
    }

    public List<String> getCompleteList() {
        return null;
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] strings) {
        return null;
    }
}
