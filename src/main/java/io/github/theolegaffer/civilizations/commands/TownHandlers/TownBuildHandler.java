package io.github.theolegaffer.civilizations.commands.TownHandlers;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Sam on 7/23/2015.
 */
public class TownBuildHandler implements SubHandler{
    private Selection sel;

    public WorldEditPlugin getWorldEdit(){
        Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (p instanceof WorldEditPlugin){
            return (WorldEditPlugin) p;
        }
        else return null;
    }

    @Override
    public void handle(CallInfo call)throws MondoFailure{
        String pName = call.getPlayer().getPlayerListName();
        EconomyMethods econTarg = new EconomyMethods(pName);
        String tName = econTarg.getTown();
        TownDataHandler newTown = new TownDataHandler(tName);
        Player player = Bukkit.getServer().getPlayer(pName);
        String buildName = call.getArg(0);
        if (!(tName.equals("none"))){
            sel = getWorldEdit().getSelection(player);
            if (!(sel == null)){
                //NEEDS TO CHECK WHAT WORLD TOWN IS IN OR MAKE TOWN ONLY IN NORM
                //NEEDS TO CHECK IF sel IS IN TOWN
                if (!(newTown.getBuild("towncenter") == 0)){
                    if (buildName.equalsIgnoreCase("walls")){
                        wallBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("baracks")){
                        baracksBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("archery")){
                        archeryBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("gates")){
                        gatesBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("farm")){
                        farmBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("apothecary")){
                        apothecaryBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("barn")){
                        barnBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("forge")){
                        forgeBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("generalstore")){
                        generalBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("bank")){
                        bankBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("temple")){
                        templeBuild(tName,pName,sel);
                    }
                    else if (buildName.equalsIgnoreCase("teleporter")){
                        teleporterBuild(tName,pName,sel);
                    }
                }
                else{
                    call.reply("{RED}You must first have a TownCenter!");
                }
            }
            else{
                call.reply("{RED}You need to first make a selection!");
            }
        }
        else{
            call.reply("{RED}You must be part of a town!");
        }
    }

    public void wallBuild(String tName, String pName, Selection sel){
        int height = sel.getHeight();
        int width = sel.getWidth();
        int length = sel.getLength();
        int small;
        int large;
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
        if (width >= length){
            large = width;
            small = length;
        }
        else{
            large = length;
            small = width;
        }
        if (!(tData.getBuild("baracks") == 0)){ //must have a baracks first

        }
        else{
            player.sendMessage(ChatColor.RED + "You must have a Baracks first!");
        }
    }
    public void baracksBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void archeryBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void gatesBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void farmBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void apothecaryBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void barnBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void forgeBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void generalBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void bankBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void templeBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void teleporterBuild(String tName, String pName, Selection sel){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
}
