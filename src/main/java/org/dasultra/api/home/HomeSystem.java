package org.dasultra.api.home;

/*

Lukas - 21:32
14.12.2023
https://github.com/NightDev701

© SunLightScorpion 2020 - 2023

*/

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class HomeSystem {

    UUID uuid;

    public HomeSystem(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean hasHomes() {
        return !getHomes().isEmpty();
    }

    public int getMaxHomes() {
        return 7;
    }

    public List<String> getHomes() {
        File file = new File("plugins/CoreSystem/playerdata/" + uuid.toString() + ".yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        return data.getStringList("homes");
    }

    public Location getHome(String name) {
        File file = new File("plugins/CoreSystem/playerdata/" + uuid.toString() + ".yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        return data.getLocation("home-" + name);
    }

    public void addHome(String home) {
        File file = new File("plugins/CoreSystem/playerdata/" + uuid.toString() + ".yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        List<String> list = getHomes();
        if (!list.contains(home)) {
            list.add(home);
        }
        data.set("homes", list);
        try {
            data.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeHome(String home) {
        File file = new File("plugins/CoreSystem/playerdata/" + uuid.toString() + ".yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        List<String> list = getHomes();
        list.remove(home);
        data.set("homes", list);
        try {
            data.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveLocation(String name, Location location) {
        File file = new File("plugins/CoreSystem/playerdata/" + uuid.toString() + ".yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set("home-" + name, location);
        try {
            data.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeLocation(String name) {
        File file = new File("plugins/CoreSystem/playerdata/" + uuid.toString() + ".yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set("home-" + name, null);
        try {
            data.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean existHome(String name) {
        return getHomes().contains(name);
    }

}