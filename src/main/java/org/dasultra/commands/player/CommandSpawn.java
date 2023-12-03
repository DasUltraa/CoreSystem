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

public class CommandSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            if (p.hasPermission("core.setspawn")) {
                FileManager data = new FileManager("plugins/CoreSystem/Spawn.yml");

                if (!data.exist()) {
                    return false;
                }

                double x = data.getDouble("x");
                double y = data.getDouble("y");
                double z = data.getDouble("z");
                double yaw = data.getDouble("yaw");
                double pitch = data.getDouble("pitch");
                World world = Bukkit.getWorld(Objects.requireNonNull(data.getString("world")));
                Location spawn = new Location(world, x, y, z, (float) yaw, (float) pitch);

                p.teleport(spawn);
                p.sendMessage(getMessage("Commands.Spawn"));
            } else {
                p.sendMessage(ServerAPI.getPrefix() + ServerAPI.getNoPerms());
            }
        }
        return false;
    }
}
