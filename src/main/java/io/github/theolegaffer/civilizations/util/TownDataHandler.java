package io.github.theolegaffer.civilizations.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.*;

/**
 * Created by Sam on 7/14/2015.
 */
public class TownDataHandler extends JavaPlugin {
    String name;
    File tDataFile;
    FileConfiguration tDataConfig;
    public ListStore townPlayers;

    public TownDataHandler(String name){
        this.name = name;

        tDataFile = new File("plugins/Civilizations/TownData/" + name + ".yml");
//        this.townPlayers = new ListStore(new File("plugins/Civilizations/TownData/" + name + "-players.txt"));
//        this.townPlayers.load();
        tDataConfig = YamlConfiguration.loadConfiguration(tDataFile);
    }

    public void createTownConfig() {
        try {
            tDataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTownDefaults() {
        List<String> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        if (tDataFile.length() <= 0) { // Checking if there isn't any data in the file.
            tDataConfig.set("Owner", "default");
            tDataConfig.set("Money", 0);
            tDataConfig.set("playerlist", list);
            tDataConfig.set("TownSpawn", "");
            tDataConfig.set("townlimits", "");
            tDataConfig.set("towncenter", 0);
            tDataConfig.set("baracks", 0);
            tDataConfig.set("archery", 0);
            tDataConfig.set("walls", 0);
            tDataConfig.set("gates", 0);
            tDataConfig.set("farm", 0);
            tDataConfig.set("apothecary", 0);
            tDataConfig.set("barn", 0);
            tDataConfig.set("forge", 0);
            tDataConfig.set("generalstore", 0);
            tDataConfig.set("bank", 0);
            tDataConfig.set("temple", 0);
            tDataConfig.set("teleporter", 0);
        }
    }

    public void deleteTownConfig(){
            tDataFile.delete();
    }

    public FileConfiguration getTownConfig() {
        return tDataConfig;
    }

    public void saveTownConfig() {
        try {
            getTownConfig().save(tDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPlayers(String playername){
        List<String> list = tDataConfig.getStringList("playerlist");
        List<String> newList = new ArrayList<>();
        newList.add(playername);
        list.addAll(newList);
        tDataConfig.set("playerlist",list);
    }

    public void removePlayers(String playername){
        List<String> list = tDataConfig.getStringList("playerlist");
        List<String> newList = new ArrayList<>();
        newList.add(playername);
        list.removeAll(newList);
        tDataConfig.set("playerlist",list);
    }


    public void addBuild(String buildingName) {
        tDataConfig.set(buildingName, (getBuild(buildingName) + 1));
    }

    public int getBuild(String buildingName) {
        return tDataConfig.getInt(buildingName);
    }

    public void setOwner(String ownerName){
        tDataConfig.set("Owner",ownerName);
    }
    public String getOwner(){
        return tDataConfig.getString("Owner");
    }

    public void setSpawn(String location){
        tDataConfig.set("TownSpawn", location);
    }

    public String getSpawn(){
        return tDataConfig.getString("TownSpawn");
    }


    public void setTownLimits(String location){
        tDataConfig.set("townlimits", location);
    }

    public String getTownLimits(){
        return tDataConfig.getString("townlimits");
    }


    public List<String> getPlayers(){
        return tDataConfig.getStringList("playerlist");
    }
}
