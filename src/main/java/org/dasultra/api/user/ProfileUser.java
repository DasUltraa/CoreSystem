package org.dasultra.api.user;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.dasultra.api.file.FileManager;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProfileUser(UUID uuid) implements User {

    private static final ItemStack[] daily = new ItemStack[]{
            new ItemStack(Material.DIAMOND, 4),
            new ItemStack(Material.COOKED_BEEF, 8),
    };

    public ProfileUser(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getName() {
        return getOfflinePlayer().getName();
    }

    @Override
    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(getUUID());
    }

    @Override
    public boolean exist() {
        return false;
    }

    @Override
    public boolean tpOnJoin() {
        return false;
    }

    @Override
    public void init() {

    }

    @Override
    public void clear() {

    }

    @Override
    public ItemStack getProfileStats() {
        return null;
    }

    @Override
    public int getKills() {
        return 0;
    }

    @Override
    public int getDeaths() {
        return 0;
    }

    @Override
    public double getKillsServerSide() {
        return 0;
    }

    @Override
    public double getDeathsServerSide() {
        return 0;
    }

    @Override
    public double getKD() {
        return 0;
    }

    @Override
    public void saveBackActionTime() {

    }

    @Override
    public boolean canBack() {
        return false;
    }

    @Override
    public void saveLastLoginDate() {

    }

    @Override
    public String getLastLoginDate() {
        return null;
    }

    @Override
    public LocalDate getLastLoginTime() {
        return null;
    }

    @Override
    public void profileLoadedNotification(UUID uuid) {

    }

    @Override
    public boolean hasHomes() {
        return false;
    }

    @Override
    public int getMaxHomes() {
        return 0;
    }

    @Override
    public List<String> getHomes() {
        return null;
    }

    @Override
    public Location getHome(String name) {
        return null;
    }

    @Override
    public void addHome(String home) {

    }

    @Override
    public void removeHome(String home) {

    }

    @Override
    public void saveLocation(String name, Location location) {

    }

    @Override
    public void removeLocation(String name) {

    }

    @Override
    public Location getBedHomeLocation() {
        return null;
    }

    @Override
    public boolean existHome(String name) {
        return false;
    }

    @Override
    public Location getLogoutLocation() {
        return null;
    }

    @Override
    public void saveLogoutLocation(Location loc) {

    }

    @Override
    public String getLastJoin() {
        return null;
    }

    @Override
    public String renderMoney() {
        return null;
    }

    @Override
    public void connect(String server) {

    }

    @Override
    public void setAttackSpeed(double i) {

    }

    @Override
    public void teleportPlayerToSpawn() {

    }

    @Override
    public void unban(boolean reset, String sender) {

    }

    @Override
    public String getReason() {
        return null;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public boolean isBanned() {
        return false;
    }

    @Override
    public boolean alreadyBanned() {
        return false;
    }

    @Override
    public boolean isPermanentBanned() {
        return false;
    }

    @Override
    public Location getDeathPoint() {
        return null;
    }

    @Override
    public void setDeathPoint(Location location) {

    }

    @Override
    public void registerGlobal() {

    }

    @Override
    public boolean isGlobalRegistered() {
        return false;
    }


    @Override
    public void countHour() {
    }

    @Override
    public void countMinute() {
    }

    @Override
    public void resetMinute() {
    }

    @Override
    public int getPing() {
        if (getOfflinePlayer().getPlayer() == null) {
            return 0;
        }
        return getOfflinePlayer().getPlayer().getPing();
    }

    @Override
    public void takeDailyReward() {

    }

    @Override
    public boolean canTakeDailyReward() {
        return false;
    }

    @Override
    public String[] getNextTakeTimeReward() {
        return new String[0];
    }

    @Override
    public void giveItemReward() {

    }

    @Override
    public String[] getTeamRanks() {
        return new String[0];
    }

    @Override
    public String[] getAdminRanks() {
        return new String[0];
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public void setAdmin(boolean staff) {

    }

    @Override
    public boolean isStaff() {
        return false;
    }

    @Override
    public void setStaff(boolean admin) {

    }


    @Override
    public void sendMessage(String msg) {
        if (getOfflinePlayer().getPlayer() != null) {
            getOfflinePlayer().getPlayer().sendMessage(msg);
        }
    }


    @Override
    public void sendMessagePrefix(String msg) {
    }


    @Override
    public boolean loadScoreboard() {
        final FileManager file = new FileManager("plugins/CoreSystem/playerdata/" + uuid.toString() + ".yml");
        return !file.getBoolean("scoreboard");
    }

    @Override
    public void toggleScoreboard() {
        final FileManager file = new FileManager("plugins/CoreSystem/playerdata/" + uuid.toString() + ".yml");
        if (loadScoreboard()) {
            file.set("scoreboard", true);
        } else {
            file.set("scoreboard", false);
        }
        file.save();
    }

    @Override
    public void addServerBlacklist() {

    }

    @Override
    public void removeServerBlacklist() {

    }

    @Override
    public boolean isBlacklistedOnServer() {
        return false;
    }

    @Override
    public boolean inGroup(String group) {
        return false;
    }

    @Override
    public boolean allowDailyReward() {
        return false;
    }

    @Override
    public boolean allowWeeklyReward() {
        return false;
    }

    @Override
    public boolean allowMonthlyReward() {
        return false;
    }

    @Override
    public void setDailyReward(long l) {

    }

    @Override
    public void setWeeklyReward(long l) {

    }

    @Override
    public void setMonthlyReward(long l) {

    }

    @Override
    public String getDailyRewardTime() {
        return null;
    }

    @Override
    public String getWeeklyRewardTime() {
        return null;
    }

    @Override
    public String getMonthlyRewardTime() {
        return null;
    }

}