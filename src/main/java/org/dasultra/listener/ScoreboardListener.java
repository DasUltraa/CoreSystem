package org.dasultra.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.dasultra.api.ServerAPI;
import org.dasultra.api.economy.EconomyAPI;
import org.dasultra.api.user.ProfileUser;
import org.dasultra.api.user.User;
import org.dasultra.api.value.StringHelper;
import org.dasultra.main.Main;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static java.sql.Types.TIME;

public class ScoreboardListener implements Listener {

    public final static HashMap<UUID, Scoreboard> playerScore = new HashMap<>();
    private final static Plugin corePlugin = Main.getPlugin();

    public static Plugin getCorePlugin() {
        return corePlugin;
    }

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
            money.setSuffix(StringHelper.getColorCode("#ffa600") + ServerAPI.renderValueForSave(EconomyAPI.getMoney(p.getUniqueId())));
            money.addEntry(ChatColor.RED.toString());

            obj.getScore("§r§0").setScore(15);
            obj.getScore("§8§l> §cMoney").setScore(14);
            obj.getScore(ChatColor.RED.toString()).setScore(13);
        }

        p.setScoreboard(board);
        playerScore.put(p.getUniqueId(), board);
    }

    public static void updateScoreBoard() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(getCorePlugin(), () -> playerScore.keySet().forEach(p -> {
            final Scoreboard board = playerScore.get(p);

            if (board.getTeam("money") != null) {
                Objects.requireNonNull(board.getTeam("money")).setSuffix(StringHelper.getColorCode("#ffa600") + ServerAPI.renderValueForSave(EconomyAPI.getMoney(p)));
            }

        }), 20 * TIME, 20 * TIME);
    }

}
