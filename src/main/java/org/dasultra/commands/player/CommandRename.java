package org.dasultra.commands.player;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.builder.ItemBuilder;
import org.dasultra.api.value.StringHelper;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandRename implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (args.length > 0) {
                if (p.hasPermission("core.rename")) {
                    ItemStack hand = p.getInventory().getItemInMainHand();
                    if (hand.getType() != Material.AIR) {
                        StringBuilder msg = new StringBuilder();
                        for (String arg : args) {
                            msg.append(arg).append(" ");
                        }
                        msg.setLength(msg.length() - 1);
                        hand = new ItemBuilder(hand).setDisplayName(StringHelper.getColorCode(String.valueOf(msg))).build();
                        p.getInventory().setItemInMainHand(new ItemStack(hand));
                        p.sendMessage(getMessage("Commands.Rename.Success").replaceAll("%name%", args[0]));
                    }
                } else {
                    p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + getMessage("Commands.Rename.Usage"));
            }
        }
        return false;
    }
}
