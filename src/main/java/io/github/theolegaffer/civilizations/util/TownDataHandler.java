package io.github.theolegaffer.civilizations.util;

import io.github.theolegaffer.civilizations.Towns.Building;
import io.github.theolegaffer.civilizations.Towns.Teleporter;
import io.github.theolegaffer.civilizations.Towns.Temple;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
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
    private String name;
    private File tDataFile;
    private FileConfiguration tDataConfig;
    private List<String> buildList = new ArrayList<>();
    private ListStore townPlayers;

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
        ArrayList<String> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        if (tDataFile.length() <= 0) { // Checking if there isn't any data in the file.
            tDataConfig.createSection("info");
            tDataConfig.set("info.owner", "default");
            tDataConfig.set("info.money", 0);
            tDataConfig.set("info.playerlist", list);
            tDataConfig.set("info.townspawn", "");
            tDataConfig.set("info.townlimits", "");
            tDataConfig.set("info.forgeperk", false);
            tDataConfig.set("info.baracksperk", false);
            tDataConfig.set("info.archeryperk", false);
            tDataConfig.set("info.bankperk", false);
            tDataConfig.set("info.templewater", false);
            tDataConfig.set("info.templefire", false);
            tDataConfig.set("info.templeair", false);
            tDataConfig.set("info.shrineair", false);
            tDataConfig.set("info.shrinefire", false);
            tDataConfig.set("info.shrinewater", false);
            tDataConfig.createSection("buildings");
            tDataConfig.createSection("building");
            tDataConfig.set("buildings.towncenter", 0);
            tDataConfig.set("buildings.baracks", 0);
            tDataConfig.set("buildings.archery", 0);
            tDataConfig.set("buildings.walls", 0);
            tDataConfig.set("buildings.gates", 0);
            tDataConfig.set("buildings.farm", 0);
            tDataConfig.set("buildings.apothecary", 0);
            tDataConfig.set("buildings.barn", 0);
            tDataConfig.set("buildings.forge", 0);
            tDataConfig.set("buildings.generalstore", 0);
            tDataConfig.set("buildings.bank", 0);
            tDataConfig.set("buildings.temple", 0);
            tDataConfig.set("buildings.teleporter", 0);
            tDataConfig.set("buildings.fireshrine", 0);
            tDataConfig.set("buildings.watershrine", 0);
            tDataConfig.set("buildings.airshrine", 0);
        }
    }
    //Make sure to reload file afterwards with PerkReloader don't have it in case of looping
    //Value as true adds, as false it removes
    public void setPlayerPerks(String name, boolean value){
        if(value){
            if(getBarackPerk()){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + name.toLowerCase() + " a:addperm v:mcmmo.perks.lucky.axes w:world");
            }
            if(getForgePerk()){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + name.toLowerCase() + " a:addperm v:mcmmo.skills.repair w:world");
            }
            if(getArcheryPerk()){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + name.toLowerCase() + " a:addperm v:mcmmo.perks.lucky.archery w:world");
            }
        }
        else{
            if(getBarackPerk()){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + name.toLowerCase() + " a:rmperm v:mcmmo.perks.lucky.axes w:world");
            }
            if(getForgePerk()){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + name.toLowerCase() + " a:rmperm v:mcmmo.skills.repair w:world");
            }
            if(getArcheryPerk()){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + name.toLowerCase() + " a:rmperm v:mcmmo.perks.lucky.archery w:world");
            }
        }
    }
//    public boolean hasPerk(String perkType){
//        if(perkType.equals("baracks")){
//            return baracksPerk;
//        }
//        else if(perkType.equals("forge")){
//            return forgePerk;
//        }
//        return false;
//    }
//
//    public void setPerk(String perkType, boolean value){
//        if(perkType.equals("baracks")){
//            baracksPerk = value;
//        }
//        else if(perkType.equals("forge")){
//            forgePerk = value;
//        }
//    }

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
        List<String> list = tDataConfig.getStringList("info.playerlist");
        List<String> newList = new ArrayList<>();
        newList.add(playername);
        list.addAll(newList);
        tDataConfig.set("info.playerlist", list);
        setPlayerPerks(playername, true);
        PerkReloader.reloadPermissions();
    }

    public void removePlayers(String playername){
        List<String> list = tDataConfig.getStringList("info.playerlist");
        list.remove(playername);
        tDataConfig.set("info.playerlist", list);
        setPlayerPerks(playername, false);
        PerkReloader.reloadPermissions();
    }
    public boolean getBankPerk(){
        return tDataConfig.getBoolean("info.bankperk");
    }
    public void setBankPerk(boolean value){
        tDataConfig.set("info.bankperk", value);
    }
    public boolean getWaterPerk(){
        return tDataConfig.getBoolean("info.templewater");
    }
    public boolean getFirePerk(){
        return tDataConfig.getBoolean("info.templefire");
    }
    public boolean getAirPerk(){
        return tDataConfig.getBoolean("info.templeair");
    }
    public void setAirPerk(boolean value){
        tDataConfig.set("info.templeair", value);
    }
    public void setFirePerk(boolean value){
        tDataConfig.set("info.templefire", value);
    }
    public void setWaterPerk(boolean value){
        tDataConfig.set("info.templewater", value);
    }
    public void setBarackPerk(boolean value){
        tDataConfig.set("info.baracksperk", value);
    }
    public void setShrineAirPerk(boolean value){
        tDataConfig.set("info.shrineair", value);
    }
    public void setShrineWaterPerk(boolean value){
        tDataConfig.set("info.shrinewater", value);
    }
    public void setShrineFirePerk(boolean value){
        tDataConfig.set("info.shrinefire", value);
    }
    public boolean getShrineWaterPerk(){
        return tDataConfig.getBoolean("info.shrinewater");
    }
    public boolean getShrineFirePerk(){
        return tDataConfig.getBoolean("info.shrinefire");
    }
    public boolean getShrineAirPerk(){
        return tDataConfig.getBoolean("info.shrineair");
    }
    public boolean getBarackPerk(){
        return tDataConfig.getBoolean("info.baracksperk");
    }
    public void setForgePerk(boolean value){
        tDataConfig.set("info.forgeperk", value);
    }
    public boolean getForgePerk(){
        return tDataConfig.getBoolean("info.forgeperk");
    }
    public void setArcheryPerk(boolean value){
        tDataConfig.set("info.archeryperk", value);
    }
    public boolean getArcheryPerk(){
        return tDataConfig.getBoolean("info.archeryperk");
    }

    public void setOwner(String ownerName){
        tDataConfig.set("info.owner",ownerName);
    }
    public String getOwner(){
        return tDataConfig.getString("info.owner");
    }

    public void setSpawn(String location){
        tDataConfig.set("info.townspawn", location);
    }

    public String getSpawn(){
        return tDataConfig.getString("info.townspawn");
    }


    public void setTownLimits(String location){
        tDataConfig.set("info.townlimits", location);
    }

    public String getTownLimits(){
        return tDataConfig.getString("info.townlimits");
    }


    public List<String> getPlayers(){
        return tDataConfig.getStringList("info.playerlist");
    }

    public boolean inTownBorder(Location location){
        Cuboid townBorder = Cuboid.newDeserialize(getTownLimits());
        return townBorder.containsLocation(location);
    }

    public boolean inBuildingBorder(Location location){
        List<String> bList = getBuildList();
        for (String name : bList){
            Cuboid temp = Cuboid.newDeserialize(tDataConfig.getString("building." + name + ".location"));
            if (temp.containsLocation(location)){
                return true;
            }
        }
        return false;
    }

    /**
     * Retuns a building name from a location
     * Must first check if the location has a building before calling
     *
     * @param location Needs the location to check
     * @return Returns the string of a building name given a location
     */
    public Building getBuildingFromLoc(Location location){
        List<String> bList = getBuildList();
        for (String name : bList){
            Cuboid temp = Cuboid.newDeserialize(tDataConfig.getString("building." + name + ".location"));
            if (temp.containsLocation(location)){
                return getBuildObject(name);
            }
        }
        return null;
    }

    public boolean inSpecificBuildingBorder(Location location, String type){
        List<String> bList = getBuildList();
        for (String name : bList){
            String bType = tDataConfig.getString("building." + name + ".type");
            //if the building is the correct type
            if(bType.equals(type)) {
                Cuboid temp = Cuboid.newDeserialize(tDataConfig.getString("building." + name + ".location"));
                if (temp.containsLocation(location)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean inBuildingBorder(Location max, Location min){
        Cuboid givenCuboid = new Cuboid(max, min);
        List<Block> lBlock = givenCuboid.getBlocks();
        List<String> bList = getBuildList();
        for (String name : bList){
            Cuboid temp = Cuboid.newDeserialize(tDataConfig.getString("building." + name + ".location"));
            for (Block block : lBlock){
                Location blockLoc = new Location(block.getWorld(),(double)block.getX(),(double)block.getY(), (double)block.getZ());
                if (temp.containsLocation(blockLoc)){
                    return true;
                }
            }

        }
        return false;
    }

    public String getBuildLocation(String buildName){
        return tDataConfig.getString("building." + buildName + ".location");
    }

//    if(type == "temple"){
//            Temple newTemple = (Temple) building;
//            tDataConfig.set("building." + name + ".templetype", newTemple.getTempleType());
//        }
//        if(type == "teleporter"){
//            Teleporter newTeleporter = (Teleporter) building;
//            tDataConfig.set("building." + name + ".linkedbuild", newTeleporter.getLinked());
//        }
    public void addBuild(Building building) {
        String type = building.getType();
        String name = building.getName();
//        String linkedBuild = building.getLinked();
        tDataConfig.set("building." + name, name);
        tDataConfig.set("building." + name + ".type", type);
        tDataConfig.set("building." + name + ".location", building.getLocation());
        tDataConfig.set("building." + name + ".entermessage", building.getEnterMessage());
        tDataConfig.set("building." + name + ".leavemessage", building.getLeaveMessage());
        tDataConfig.set("building." + name + ".linkedbuild", building.getLinked());
        tDataConfig.set("building." + name + ".templetype", building.getTempleType());
        //Adds new building to list
        List<String> bList = getBuildList();
        bList.add(name);
        tDataConfig.set("info.buildlist", bList);
        //Adds the count of the type of buildings
        tDataConfig.set("buildings." + type.toLowerCase(), (getBuildNum(type) + 1));
    }

    public Building getBuildObject(String buildName){
        String location = getBuildLocation(buildName);
        Building building = new Building(getBuildType(buildName),Cuboid.newDeserialize(location),buildName);
        return building;
    }

    private String getBuildType(String buildName) {
        return tDataConfig.getString("building." + buildName + ".type");
    }

    //Need to deal with perks when finishing this
    public void removeBuild(String name){
        String type = tDataConfig.getString("building." + name + ".type");
        //cannot actually delete the building
        tDataConfig.set("building." + name, "deleted");
        List<String> bList = getBuildList();
        bList.remove(name);
        tDataConfig.set("info.buildlist", bList);
        //Lessens the count of the type of buildings
        tDataConfig.set("buildings." + type, (getBuildNum(type) - 1));
    }

    public List<String> getBuildList(){
        return tDataConfig.getStringList("info.buildlist");
    }

    public boolean containsBuildName(String name){
        List<String> buildList = getBuildList();
        for (String temp : buildList){
            if (temp.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public int getBuildNum(String buildType) {
        return tDataConfig.getInt("buildings." + buildType.toLowerCase());
    }

    public void setEnterM(String name, String msg){
        tDataConfig.set("building." + name + ".entermessage", msg);
    }

    public String getEnterM(String buildName){
        return tDataConfig.getString("building." + buildName + ".entermessage");
    }

    public void setLeaveM(String name, String msg){
        tDataConfig.set("building." + name + ".leavemessage", msg);
    }

    public void setLinked(String name, String linked){
        tDataConfig.set("building." + name + ".linkedbuild", linked);
    }



}
