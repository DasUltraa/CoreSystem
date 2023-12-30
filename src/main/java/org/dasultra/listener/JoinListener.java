package org.dasultra.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.dasultra.api.Database.DataBaseManager;
import org.dasultra.api.file.FileManager;

import java.util.Objects;

import static org.dasultra.api.messages.Messages.getMessage;
import static org.dasultra.listener.ScoreboardListener.updateScoreBoard;

public class JoinListener implements Listener {

    @EventHandler
    public boolean onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        e.setJoinMessage(getMessage("JoinMessage").replaceAll("%player%", p.getName()));
        ScoreboardListener.setScoreboard(p);
        DataBaseManager.createMoneyPlayer(p.getUniqueId());

        updateScoreBoard();

        FileManager data = new FileManager("plugins/CoreSystem/Spawn.yml");

        if (!data.exist()) {
            return false;
        } else {
            double x = data.getDouble("x");
            double y = data.getDouble("y");
            double z = data.getDouble("z");
            double yaw = data.getDouble("yaw");
            double pitch = data.getDouble("pitch");
            World world = Bukkit.getWorld(Objects.requireNonNull(data.getString("world")));
            Location spawn = new Location(world, x, y, z, (float) yaw, (float) pitch);

            p.teleport(spawn);
        }


        return true;
    }
}
