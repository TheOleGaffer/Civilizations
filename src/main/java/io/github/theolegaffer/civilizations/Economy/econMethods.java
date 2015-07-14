package io.github.theolegaffer.civilizations.Economy;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Sam on 7/13/2015.
 */

public class econMethods extends JavaPlugin {
    String name;
    File pData;
    FileConfiguration pDataConfig;

    public econMethods(String name){
        this.name = name;

        pData = new File(getDataFolder() +"/Economy/" + name + ".yml");
        pDataConfig = YamlConfiguration.loadConfiguration(pData);
    }

    public void createPlayerConfig() {
        try {
            pData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPlayerDefaults() {
        if (pData.length() <= 0) { // Checking if there isn't any data in the file.
            pDataConfig.set("Money", 50);
        }
    }

    public FileConfiguration getPlayerConfig() {
        return pDataConfig;
    }

    public void savePlayerConfig() {
        try {
            getPlayerConfig().save(pData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMoney() {
        return pDataConfig.getInt("Money");
    }

    public void setMoney(int amount) {
        pDataConfig.set("Money", amount);
    }

    public void takeMoney(int amount) {
        pDataConfig.set("Money", getMoney() - amount);
    }

    public void giveMoney(int amount) {
        pDataConfig.set("Money", getMoney() + amount);
    }
}
