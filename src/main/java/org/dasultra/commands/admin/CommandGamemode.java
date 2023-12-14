package org.dasultra.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandGamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player p) {
            if (args.length == 1) {
                if (p.hasPermission("core.gamemode")) {
                    if (args[0].equalsIgnoreCase("0")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(getMessage("Commands.Gamemode.Survival"));
                    }
                    if (args[0].equalsIgnoreCase("survival")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(getMessage("Commands.Gamemode.Survival"));
                    }
                    if (args[0].equalsIgnoreCase("1")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(getMessage("Commands.Gamemode.Creative"));
                    }
                    if (args[0].equalsIgnoreCase("creative")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(getMessage("Commands.Gamemode.Creative"));
                    }
                    if (args[0].equalsIgnoreCase("2")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(getMessage("Commands.Gamemode.Adventure"));
                    }
                    if (args[0].equalsIgnoreCase("adventure")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(getMessage("Commands.Gamemode.Adventure"));
                    }
                    if (args[0].equalsIgnoreCase("3")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(getMessage("Commands.Gamemode.Spectator"));
                    }
                    if (args[0].equalsIgnoreCase("spectator")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(getMessage("Commands.Gamemode.Spectator"));
                    }
                } else
                    p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            } else if (args.length == 2) {

                Player t = Bukkit.getPlayer(args[0]);

                if (p.hasPermission("core.gamemode.other")) {
                    if (args[1].equalsIgnoreCase("0")) {
                        t.setGameMode(GameMode.SURVIVAL);
                        t.sendMessage(getMessage("Commands.Gamemode.Survival"));
                        p.sendMessage(getMessage("Commands.Gamemode.Other"));
                    }
                    if (args[1].equalsIgnoreCase("survival")) {
                        t.setGameMode(GameMode.SURVIVAL);
                        t.sendMessage(getMessage("Commands.Gamemode.Survival"));
                    }
                    if (args[1].equalsIgnoreCase("1")) {
                        t.setGameMode(GameMode.CREATIVE);
                        t.sendMessage(getMessage("Commands.Gamemode.Creative"));
                    }
                    if (args[1].equalsIgnoreCase("creative")) {
                        t.setGameMode(GameMode.CREATIVE);
                        t.sendMessage(getMessage("Commands.Gamemode.Creative"));
                    }
                    if (args[1].equalsIgnoreCase("2")) {
                        t.setGameMode(GameMode.ADVENTURE);
                        t.sendMessage(getMessage("Commands.Gamemode.Adventure"));
                    }
                    if (args[1].equalsIgnoreCase("adventure")) {
                        t.setGameMode(GameMode.ADVENTURE);
                        t.sendMessage(getMessage("Commands.Gamemode.Adventure"));
                    }
                    if (args[1].equalsIgnoreCase("3")) {
                        t.setGameMode(GameMode.SPECTATOR);
                        t.sendMessage(getMessage("Commands.Gamemode.Spectator"));
                    }
                    if (args[1].equalsIgnoreCase("spectator")) {
                        t.setGameMode(GameMode.SPECTATOR);
                        t.sendMessage(getMessage("Commands.Gamemode.Spectator"));
                        p.sendMessage(getMessage("Commands.Gamemode.Other").replaceAll("%player%", args[0]).replaceAll("%arg 2", args[1]));
                    }
                } else {
                    p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
                }
            } else {
                p.sendMessage(getMessage("Commands.Gamemode.Usage"));
            }
        }
        return false;
    }
}
