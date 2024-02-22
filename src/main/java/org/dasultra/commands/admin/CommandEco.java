package org.dasultra.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.economy.EconomyAPI;

import java.util.ArrayList;
import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;
import static org.dasultra.listener.ScoreboardListener.updateScoreBoard;

public class CommandEco implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            if (p.hasPermission("core.eco")) {
                if (strings.length == 3) {
                    OfflinePlayer t = Bukkit.getOfflinePlayer(strings[0]);
                    Double money = Double.parseDouble(strings[2]);

                    if (strings[1].equalsIgnoreCase("add")) {
                        updateScoreBoard();
                        EconomyAPI.addMoney(t.getUniqueId(), money);
                        p.sendMessage(getMessage("Commands.Eco.UpdateMoney").replaceAll("%player%", t.getName()).replaceAll("%money%", String.valueOf(ServerAPI.renderValueForSave(EconomyAPI.getMoney(p.getUniqueId())))));
                    } else if (strings[1].equalsIgnoreCase("remove")) {
                        updateScoreBoard();
                        EconomyAPI.removeMoney(t.getUniqueId(), money);
                        p.sendMessage(getMessage("Commands.Eco.UpdateMoney").replaceAll("%player%", t.getName()).replaceAll("%money%", String.valueOf(ServerAPI.renderValueForSave(EconomyAPI.getMoney(p.getUniqueId())))));
                    } else if (strings[1].equalsIgnoreCase("set")) {
                        updateScoreBoard();
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

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List <String> list = new ArrayList<>();
        if (strings.length == 1) {
            list.add("add");
            list.add("remove");
            list.add("set");
        } else if (strings.length == 2) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        } else if (strings.length == 3) {
            list.add("0");
            list.add("100");
            list.add("1000");
            list.add("10000");
            list.add("100000");
            list.add("1000000");
        }
        return list;
    }
}
