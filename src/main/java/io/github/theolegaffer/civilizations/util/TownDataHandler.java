package io.github.theolegaffer.civilizations.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        if (tDataFile.length() <= 0) { // Checking if there isn't any data in the file.
            tDataConfig.set("Owner", "default");
            tDataConfig.set("Money", 0);
            tDataConfig.set("playerlist", list);
            tDataConfig.set("Towncenter", false);
            tDataConfig.set("Baracks", false);
            tDataConfig.set("Archery", false);
            tDataConfig.set("Walls", false);
            tDataConfig.set("Gates", false);
            tDataConfig.set("Farm", false);
            tDataConfig.set("Apothecary", false);
            tDataConfig.set("Barn", false);
            tDataConfig.set("Forge", false);
            tDataConfig.set("GeneralStore", false);
            tDataConfig.set("Bank", false);
            tDataConfig.set("Temple", false);
            tDataConfig.set("Teleporter", false);
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
        tDataConfig.set("playerlist", tDataConfig.getStringList("playerlist").remove(playername));
    }

    public void setBuild(String buildingName, boolean express) {
        tDataConfig.set(buildingName, express);
    }

    public void setOwner(String ownerName){
        tDataConfig.set("Owner",ownerName);
    }

    public boolean getBuild(String buildingName) {
        return tDataConfig.getBoolean(buildingName);
    }

    public String getOwner(){
        return tDataConfig.getString("Owner");
    }

    public List<String> getPlayers(){
        return tDataConfig.getStringList("playerlist");
    }
}
