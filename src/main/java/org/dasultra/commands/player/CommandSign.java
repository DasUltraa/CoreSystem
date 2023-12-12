package org.dasultra.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.dasultra.api.ServerAPI;

import java.util.ArrayList;
import java.util.List;

public class CommandSign implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("core.sign")) {
                StringBuilder builder = new StringBuilder();
                if (p.hasPermission("core.sign.ignore")) {
                    if (args[0].equalsIgnoreCase("ignore")) {
                        if (args.length > 1) {
                            StringBuilder msg = new StringBuilder();
                            for (String arg : args) {
                                msg.append(arg).append(" ");
                            }
                            builder.append(msg.toString().replace("ignore ".toLowerCase(), ""));
                            String replace = builder.toString().replace("ignore ".toLowerCase(), "");
                            p.sendMessage("" + replace);

                            ItemStack hand = p.getInventory().getItemInMainHand();
                            ItemMeta meta = hand.getItemMeta();

                            List<String> data = new ArrayList<>();
                            data.add("§a§l" + replace);
                            data.add("");
                            data.add("§6Signed on §4§l" + ServerAPI.getDate() + "§6!");
                            data.add("");

                            assert meta != null;
                            meta.setLore(data);

                            hand.setItemMeta(meta);
                            data.clear();
                        }
                    }
                } else {
                    p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
                }

                if (args.length > 0) {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }
                    builder.append(msg);
                    p.sendMessage("" + builder);

                    ItemStack hand = p.getInventory().getItemInMainHand();
                    ItemMeta meta = hand.getItemMeta();

                    List<String> data = new ArrayList<>();
                    data.add("§7Signed from §b§l" + p.getName() + "§7");
                    data.add("");
                    data.add("§a§l" + builder);
                    data.add("");
                    data.add("§6Signed on §4§l" + ServerAPI.getDate() + "§6!");
                    data.add("");

                    assert meta != null;
                    meta.setLore(data);

                    hand.setItemMeta(meta);
                    data.clear();
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }

        return false;
    }
}
