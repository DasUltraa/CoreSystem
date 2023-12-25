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
            money.setSuffix("§8» " + StringHelper.getColorCode("#ffa600") + ServerAPI.renderValueForSave(EconomyAPI.getMoney(p.getUniqueId())) + "$");
            money.addEntry(ChatColor.RED.toString());

            Team online = board.registerNewTeam("online");
            online.setPrefix("§a");
            online.setSuffix("§8» " + ChatColor.GOLD + String.valueOf(Bukkit.getOnlinePlayers().size()) + "/" + Bukkit.getMaxPlayers());
            online.addEntry(ChatColor.GOLD.toString());

            obj.getScore("§cOnline").setScore(8);
            obj.getScore(ChatColor.GOLD.toString()).setScore(7);
            obj.getScore("§r§1").setScore(6);
            obj.getScore("§cName").setScore(5);
            obj.getScore("§8»" + ChatColor.GOLD + p.getName()).setScore(4);
            obj.getScore("§r§2").setScore(3);
            obj.getScore("§cMoney").setScore(2);
            obj.getScore(ChatColor.RED.toString()).setScore(1);
            obj.getScore("§r§0").setScore(0);
        }

        p.setScoreboard(board);
        playerScore.put(p.getUniqueId(), board);
    }

    public static void updateScoreBoard() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(getCorePlugin(), () -> playerScore.keySet().forEach(p -> {
            final Scoreboard board = playerScore.get(p);


            if (board.getTeam("money") != null) {
                Objects.requireNonNull(board.getTeam("money")).setSuffix("§8» " + StringHelper.getColorCode("#ffa600") + ServerAPI.renderValueForSave(EconomyAPI.getMoney(p)) + "$");
            }

            if (board.getTeam("online") != null) {
                Objects.requireNonNull(board.getTeam("online")).setSuffix("§8» " + ChatColor.GOLD + String.valueOf(Bukkit.getOnlinePlayers().size()) + "/" + Bukkit.getMaxPlayers());
            }

        }), 20 * TIME, 20 * TIME);
    }

}
