package org.dasultra.api.economy;

import org.dasultra.api.Database.DataBaseManager;

import java.util.UUID;

public class EconomyAPI {

    public static UUID getEconomy(UUID uuid) {
        return uuid;
    }

    public static Double getMoney(UUID uuid) {
        return DataBaseManager.getMoney(uuid);
    }

    public static void setMoney(UUID uuid, Double d) {
        DataBaseManager.updateCoins(uuid, d);
    }

    public static void addMoney(UUID uuid, Double d) {
        DataBaseManager.updateCoins(uuid, getMoney(uuid) + d);
    }

    public static void removeMoney(UUID uuid, Double d) {
        var v = getMoney(uuid) - d;
        DataBaseManager.updateCoins(uuid, v);
    }
}