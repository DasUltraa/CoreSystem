package org.dasultra.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dasultra.api.File.FileManager;
import org.dasultra.api.ServerAPI;

import java.util.Objects;

import static org.dasultra.api.messages.Messages.getMessage;

public class CommandSetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (p.hasPermission("core.setspawn")) {
                    FileManager data = new FileManager("plugins/CoreSystem/Spawn.yml");

                    Location location = p.getLocation();

                    double x = location.getX();
                    double y = location.getY();
                    double z = location.getZ();
                    double yaw = location.getYaw();
                    double pitch = location.getPitch();
                    String world = Objects.requireNonNull(location.getWorld()).getName();
                    data.set("x", x);
                    data.set("y", y);
                    data.set("z", z);
                    data.set("yaw", yaw);
                    data.set("pitch", pitch);
                    data.set("world", world);
                    data.save();
                    p.sendMessage(getMessage("Commands.Setspawn"));
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }
        return false;
    }
}
