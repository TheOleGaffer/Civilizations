package io.github.theolegaffer.civilizations.commands.TownHandlers;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerExperienceEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.Towns.Building;
import io.github.theolegaffer.civilizations.util.Cuboid;
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
        Player player = Bukkit.getServer().getPlayer(pName);
        String buildType = call.getArg(0);
        String bName = call.getArg(1);
        if (!(tName.equals("none"))){
            TownDataHandler newTown = new TownDataHandler(tName);
            sel = getWorldEdit().getSelection(player);
            if (!(sel == null)){
                //NEEDS TO CHECK WHAT WORLD TOWN IS IN OR MAKE TOWN ONLY IN NORM

                //Checks if it is in the town borders
                if(Cuboid.newDeserialize(newTown.getTownLimits()).containsLocation(sel.getMaximumPoint()) && Cuboid.newDeserialize(newTown.getTownLimits()).containsLocation(sel.getMinimumPoint())) {
                    //Checks if the name is taken
                    if (!(newTown.containsBuildName(bName))){
                        //Checks if any of the blocks are in another building
                        if (!(newTown.inBuildingBorder(sel.getMaximumPoint(), sel.getMinimumPoint()))) {
                            if (buildType.equalsIgnoreCase("towncenter")) {
                                towncenterBuild(tName, pName, sel, bName);
                            } else {
                                if (!(newTown.getBuildNum("towncenter") == 0)) {
                                    if (buildType.equalsIgnoreCase("walls")) {
                                        wallBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("baracks")) {
                                        baracksBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("archery")) {
                                        archeryBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("gates")) {
                                        gatesBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("farm")) {
                                        farmBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("apothecary")) {
                                        apothecaryBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("barn")) {
                                        barnBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("forge")) {
                                        forgeBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("generalstore")) {
                                        generalBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("bank")) {
                                        bankBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("temple")) {
                                        templeBuild(tName, pName, sel, bName);
                                    } else if (buildType.equalsIgnoreCase("teleporter")) {
                                        teleporterBuild(tName, pName, sel, bName);
                                    }
                                } else {
                                    call.reply("{RED}You must first have a TownCenter!");
                                }
                            }
                        }
                        else call.reply("{RED}That selection contains part of another building!");

                    }
                    else call.reply("{RED}That building name is already taken!");
                }
                else call.reply("{RED}You must make the building inside the town!");
            }
            else{
                call.reply("{RED}You need to first make a selection!");
            }
        }
        else{
            call.reply("{RED}You must be part of a town!");
        }
    }

    public void towncenterBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler newTown = new TownDataHandler(tName);
        Cuboid buildSel = new Cuboid(sel.getMaximumPoint(), sel.getMinimumPoint(),tName);
        if (buildSel.allowedSizeBig()) {
            Building newBuild = new Building("TownCenter", buildSel, bName);
            newTown.addBuild(newBuild);
            newTown.saveTownConfig();
            player.sendMessage(ChatColor.GREEN + "You successfully created a Towncenter!");
        }
        else player.sendMessage(ChatColor.RED + "That selection is not large enough!");
    }

    public void wallBuild(String tName, String pName, Selection sel, String bName){
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
        if (!(tData.getBuildNum("baracks") == 0)){ //must have a baracks first

        }
        else{
            player.sendMessage(ChatColor.RED + "You must have a Baracks first!");
        }
    }
    public void baracksBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
        EconomyMethods playerEcon = new EconomyMethods(pName);
        Cuboid buildSel = new Cuboid(sel.getMaximumPoint(), sel.getMinimumPoint(),tName);
        if (tData.getBuildNum("baracks") == 0) {
            if (buildSel.allowedSizeBig()) {
                if (playerEcon.takeMoneyAllowed(150)) {
                    //neither did anything
//                    McMMOPlayerXpGainEvent event = new McMMOPlayerXpGainEvent(player, SkillType.AXES, 5000);
//                    ExperienceAPI.addLevel(player, SkillType.AXES, 5, true);
                    playerEcon.takeMoney(150);
                    Building newBuild = new Building("Baracks", buildSel, bName);
                    tData.addBuild(newBuild);
                    tData.saveTownConfig();
                    player.sendMessage(ChatColor.GREEN + "You successfully created a Baracks!");
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have the necessary funds!");
                }
            }
            else{
                player.sendMessage(ChatColor.RED + "That selection is not large enough!");
            }
        }
        else{
            player.sendMessage(ChatColor.RED+ "You may only have one Baracks at a time!");
        }
    }
    public void archeryBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void gatesBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void farmBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void apothecaryBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void barnBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void forgeBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void generalBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void bankBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void templeBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
    public void teleporterBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
}
