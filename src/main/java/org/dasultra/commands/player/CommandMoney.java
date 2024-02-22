package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.economy.EconomyAPI;

import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandMoney implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            if (strings.length == 0) {
                p.sendMessage(getMessage("Commands.Money.Player").replaceAll("%money%", String.valueOf(ServerAPI.renderValueForSave(EconomyAPI.getMoney(p.getUniqueId())))));
            }

            if (strings.length == 1) {
                if (p.hasPermission("eco.admin.money.target")) {
                    OfflinePlayer t = Bukkit.getOfflinePlayer(strings[0]);
                    p.sendMessage(getMessage("Commands.Money.Target").replaceAll("%player%", t.getName()).replaceAll("%money%", String.valueOf(ServerAPI.renderValueForSave(EconomyAPI.getMoney(t.getUniqueId())))));
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
