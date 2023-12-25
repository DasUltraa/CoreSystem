package org.dasultra.api.user;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface User {

    UUID getUUID();

    String getName();

    OfflinePlayer getOfflinePlayer();

    boolean exist();

    boolean tpOnJoin();

    void init();

    void clear();

    ItemStack getProfileStats();

    int getKills();

    int getDeaths();

    double getKillsServerSide();

    double getDeathsServerSide();


    double getKD();

    void saveBackActionTime();

    boolean canBack();

    void saveLastLoginDate();

    String getLastLoginDate();

    LocalDate getLastLoginTime();

    void profileLoadedNotification(UUID uuid);

    boolean hasHomes();

    int getMaxHomes();

    List<String> getHomes();

    Location getHome(String name);

    void addHome(String home);

    void removeHome(String home);

    void saveLocation(String name, Location location);

    void removeLocation(String name);

    Location getBedHomeLocation();

    boolean existHome(String name);

    Location getLogoutLocation();

    void saveLogoutLocation(Location loc);


    String getLastJoin();

    String renderMoney();

    void connect(String server);

    void setAttackSpeed(double i);

    void teleportPlayerToSpawn();

    void unban(boolean reset, String sender);

    String getReason();

    long getTime();

    String getDate();

    boolean isBanned();

    boolean alreadyBanned();

    boolean isPermanentBanned();

    Location getDeathPoint();

    void setDeathPoint(Location location);

    void registerGlobal();

    boolean isGlobalRegistered();

    void countHour();

    void countMinute();

    void resetMinute();

    int getPing();

    void takeDailyReward();

    boolean canTakeDailyReward();

    String[] getNextTakeTimeReward();

    void giveItemReward();

    String[] getTeamRanks();

    String[] getAdminRanks();

    boolean isAdmin();

    void setAdmin(boolean staff);

    boolean isStaff();

    void setStaff(boolean admin);

    void sendMessage(String msg);


    void sendMessagePrefix(String msg);


    boolean loadScoreboard();

    void toggleScoreboard();


    void addServerBlacklist();

    void removeServerBlacklist();

    boolean isBlacklistedOnServer();

    boolean inGroup(String group);

    boolean allowDailyReward();

    boolean allowWeeklyReward();

    boolean allowMonthlyReward();

    void setDailyReward(long l);

    void setWeeklyReward(long l);

    void setMonthlyReward(long l);

    String getDailyRewardTime();

    String getWeeklyRewardTime();

    String getMonthlyRewardTime();
}