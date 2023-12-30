package org.dasultra.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dasultra.api.ServerAPI;

public class CommandBroadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("core.broadcast")) {
                if (args.length > 0) {
                    String bc = "";
                    for (int i = 0; i < args.length; i++)
                        bc = String.valueOf(bc) + args[i] + " ";
                    bc = bc.replace("\\n", "\n");
                    Bukkit.broadcastMessage("§8------------------------");
                    Bukkit.broadcastMessage("§r§2");
                    Bukkit.broadcastMessage(ChatColor.GREEN + bc);
                    Bukkit.broadcastMessage("§r§1");
                    Bukkit.broadcastMessage("§8------------------------");
                    playSoundToAllPlayers(Sound.ENTITY_PLAYER_LEVELUP);
                } else {
                    p.sendMessage();
                }
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }

        }
        return false;
    }

    private void playSoundToAllPlayers (Sound sound){
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.playSound(onlinePlayer.getLocation(), sound, 1.0f, 1.0f);
        }
    }
}