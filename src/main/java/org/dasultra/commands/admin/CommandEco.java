package org.dasultra.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.economy.EconomyAPI;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandEco implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            if (p.hasPermission("eco.admin")) {
                if (strings.length == 3) {
                    OfflinePlayer t = Bukkit.getOfflinePlayer(strings[0]);
                    Double money = Double.parseDouble(strings[2]);

                    if (strings[1].equalsIgnoreCase("add")) {
                        EconomyAPI.addMoney(t.getUniqueId(), money);
                        p.sendMessage(getMessage("Commands.Eco.UpdateMoney").replaceAll("%player%", t.getName()).replaceAll("%money%", String.valueOf(ServerAPI.renderValueForSave(EconomyAPI.getMoney(p.getUniqueId())))));
                    } else if (strings[1].equalsIgnoreCase("remove")) {
                        EconomyAPI.removeMoney(t.getUniqueId(), money);
                        p.sendMessage(getMessage("Commands.Eco.UpdateMoney").replaceAll("%player%", t.getName()).replaceAll("%money%", String.valueOf(ServerAPI.renderValueForSave(EconomyAPI.getMoney(p.getUniqueId())))));
                    } else if (strings[1].equalsIgnoreCase("set")) {
                        EconomyAPI.setMoney(t.getUniqueId(), money);
                        p.sendMessage(getMessage("Commands.Eco.UpdateMoney").replaceAll("%player%", t.getName()).replaceAll("%money%", String.valueOf(ServerAPI.renderValueForSave(EconomyAPI.getMoney(p.getUniqueId())))));
                    }
                } else {
                    p.sendMessage(getMessage("Commands.Eco.Usage"));
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }
        return false;
    }
}
