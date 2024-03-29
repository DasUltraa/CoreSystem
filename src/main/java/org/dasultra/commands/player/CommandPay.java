package org.dasultra.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.api.economy.EconomyAPI;

import java.util.List;

import static org.dasultra.api.messages.Messages.getMessage;
import static org.dasultra.listener.ScoreboardListener.updateScoreBoard;

public class CommandPay implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            if (strings.length == 2) {
                Player t = Bukkit.getPlayer(strings[0]);
                double money = Double.parseDouble(strings[1]);

                if (!strings[0].equalsIgnoreCase(p.getName())) {
                    if (EconomyAPI.getMoney(p.getUniqueId()) >= money) {
                        updateScoreBoard();
                        EconomyAPI.removeMoney(p.getUniqueId(), money);
                        EconomyAPI.addMoney(t.getUniqueId(), money);
                        p.sendMessage(getMessage("Commands.Pay.SuccessPlayer").replaceAll("%player%", t.getName()).replaceAll("%money%", String.valueOf(strings[1])));
                        t.sendMessage(getMessage("Commands.Pay.SuccessTarget").replaceAll("%player%", p.getName()).replaceAll("%money%", String.valueOf(strings[1])));
                    } else {
                        p.sendMessage(getMessage("Commands.Pay.NotEnoughMoney"));
                    }
                } else {
                    p.sendMessage(getMessage("Commands.Pay.SelfPay"));
                }

            } else {
                p.sendMessage(getMessage("Commands.Pay.Usage"));
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
