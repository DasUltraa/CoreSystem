package org.dasultra.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.dasultra.api.ServerAPI;

import java.util.ArrayList;
import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandLore implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            StringBuilder builder = new StringBuilder();
            if (p.hasPermission("core.lore")) {
                if (args.length > 0) {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }
                    builder.append(msg.toString().replace("ignore ".toLowerCase(), ""));
                    String replace = builder.toString().replace("ignore ".toLowerCase(), "");

                    ItemStack hand = p.getInventory().getItemInMainHand();
                    ItemMeta meta = hand.getItemMeta();

                    List<String> data = new ArrayList<>();
                    data.add("§a" + replace);

                    assert meta != null;
                    meta.setLore(data);

                    hand.setItemMeta(meta);
                    data.clear();
                    p.sendMessage(ServerAPI.getPrefix() + getMessage("Commands.Lore.Success").replaceAll("%lore%", replace));
                } else {
                    p.sendMessage(ServerAPI.getPrefix() + getMessage("Commands.Lore.Usage"));
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
