package org.dasultra.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.user.ProfileUser;
import org.dasultra.api.user.User;
import org.dasultra.api.value.StringHelper;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ScoreboardListener implements Listener {

    public final static HashMap<UUID, Scoreboard> playerScore = new HashMap<>();

    public static User getUserProfile(UUID id) {
        return new ProfileUser(id);
    }

    public static void setScoreboard(Player p) {

        User user = getUserProfile(p.getUniqueId());

        Scoreboard board = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective obj = board.registerNewObjective("aaa", "bbb", "ccc");

        if (user.loadScoreboard()) {

            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName(ServerAPI.getServerName());

            Team money = board.registerNewTeam("money");
            money.setPrefix("§a");
            money.setSuffix(StringHelper.getColorCode("#ffa600") + getUserProfile(p.getUniqueId()));
            money.addEntry(ChatColor.RED.toString());

            obj.getScore("§r§0").setScore(15);
            obj.getScore("§8§l> §cMoney").setScore(14);
            obj.getScore(ChatColor.RED.toString()).setScore(13);
        }

        p.setScoreboard(board);
        playerScore.put(p.getUniqueId(), board);
    }
}
