package io.github.theolegaffer.civilizations.Economy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sam on 7/13/2015.
 */

public class EconomyMethods extends JavaPlugin {
    String name;
    File pData;
    FileConfiguration pDataConfig;

    public EconomyMethods(String name){
        this.name = name;

        pData = new File("plugins/Civilizations/PlayerData/" + name + ".yml");
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
            pDataConfig.set("Town","none");
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

    public boolean isInTown(){
        return pDataConfig.getString("Town") == "none";
    }
    public String getTown(){
        return pDataConfig.getString("Town");
    }

    public void setTown(String townname){
        pDataConfig.set("Town", townname);
    }

    public int getMoney() {
        return pDataConfig.getInt("Money");
    }

    public void setMoney(int amount) {
        pDataConfig.set("Money", amount);
    }

    public void takeMoney(int amount) {
        if (!(getMoney() - amount < 0)) {
            pDataConfig.set("Money", getMoney() - amount);
        }
        else{
            return;
        }
    }
    public boolean takeMoneyAllowed(int amount){
        return getMoney() - amount >= 0;
    }

    public void giveMoney(int amount) {
        pDataConfig.set("Money", getMoney() + amount);
    }
}
