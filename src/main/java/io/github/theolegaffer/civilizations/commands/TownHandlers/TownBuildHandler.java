package io.github.theolegaffer.civilizations.commands.TownHandlers;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.Towns.Building;
import io.github.theolegaffer.civilizations.util.Cuboid;
import io.github.theolegaffer.civilizations.util.PerkReloader;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.MobType;
import net.dtl.citizens.trader.CitizensTrader;
import net.dtl.citizens.trader.TraderCharacterTrait;
import net.dtl.citizens.trader.TraderCommandExecutor;
import net.dtl.citizens.trader.traits.TraderTrait;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
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
                                townCenterBuild(tName, pName, sel, bName);
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

    public void townCenterBuild(String tName, String pName, Selection sel, String bName){
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
//        EconomyMethods playerEcon = new EconomyMethods(pName);
        Cuboid buildSel = new Cuboid(sel.getMaximumPoint(), sel.getMinimumPoint(),tName);
        if (tData.getBuildNum("baracks") == 0) {
            if (buildSel.allowedSizeBig()) {
                if (Civilizations.econ.has(pName, 200.0)) {
                    Civilizations.econ.bankWithdraw(pName, 200.0);
                    Building newBuild = new Building("Baracks", buildSel, bName);
                    tData.addBuild(newBuild);
                    tData.setBarackPerk(true);
                    tData.saveTownConfig();
                    //gets list of players to add perk to and adds perm via bPerm plugin
                    for (String playersInTown : tData.getPlayers()){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + playersInTown.toLowerCase() + " a:addperm v:mcmmo.perks.lucky.axes w:world");
                    }
                    PerkReloader.reloadPermissions();
                    player.sendMessage(ChatColor.GREEN + "You successfully created a Baracks.");
                    player.sendMessage(ChatColor.GREEN + "The town now has the axe perk!");
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
        Cuboid buildSel = new Cuboid(sel.getMaximumPoint(), sel.getMinimumPoint(),tName);
        if (tData.getBuildNum("archery") == 0) {
            if (buildSel.allowedSizeBig()) {
                if (Civilizations.econ.has(pName, 200.0)) {
                    Civilizations.econ.bankWithdraw(pName, 200.0);
                    Building newBuild = new Building("Archery", buildSel, bName);
                    tData.addBuild(newBuild);
                    tData.setArcheryPerk(true);
                    tData.saveTownConfig();
                    //gets list of players to add perk to and adds perm via bPerm plugin
                    for (String playersInTown : tData.getPlayers()){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + playersInTown.toLowerCase() + " a:addperm v:mcmmo.perks.lucky.archery w:world");
                    }
                    PerkReloader.reloadPermissions();
                    player.sendMessage(ChatColor.GREEN + "You successfully created an Archery.");
                    player.sendMessage(ChatColor.GREEN + "The town now has the archery perk!");
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have the necessary funds!");
                }
            }
            else{
                player.sendMessage(ChatColor.RED + "That selection is not large enough!");
            }
        }
        else{
            player.sendMessage(ChatColor.RED+ "You may only have one Archery at a time!");
        }
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
//        EconomyMethods playerEcon = new EconomyMethods(pName);
        Cuboid buildSel = new Cuboid(sel.getMaximumPoint(), sel.getMinimumPoint(),tName);
        if (tData.getBuildNum("forge") == 0) {
            if (buildSel.allowedSizeBig()) {
                if (Civilizations.econ.has(pName, 150.0)) {
                    Civilizations.econ.bankWithdraw(pName, 150.0);
                    Building newBuild = new Building("Forge", buildSel, bName);
                    tData.addBuild(newBuild);
                    tData.setForgePerk(true);
                    tData.saveTownConfig();
                    //gets list of players to add perk to and adds perm via bPerm plugin
                    //had to add in delays because the perm plugin would go to slow and reload before the other command had finished
                    for (String playersInTown : tData.getPlayers()){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + playersInTown.toLowerCase() + " a:addperm v:mcmmo.skills.repair w:world");
                    }
                    PerkReloader.reloadPermissions();
                    player.sendMessage(ChatColor.GREEN + "You successfully created a Forge.");
                    player.sendMessage(ChatColor.GREEN + "You now have access to the repair skill!");
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have the necessary funds!");
                }
            }
            else{
                player.sendMessage(ChatColor.RED + "That selection is not large enough!");
            }
        }
        else{
            player.sendMessage(ChatColor.RED+ "You may only have one Forge at a time!");
        }
    }
    //npc is buggy right now
    public void generalBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
        Cuboid buildSel = new Cuboid(sel.getMaximumPoint(), sel.getMinimumPoint(),tName);
        if(tData.getBuildNum("generalstore") == 0){
            if (buildSel.allowedSizeBig()) {
                if (Civilizations.econ.has(pName, 100.0)) {
                    Civilizations.econ.bankWithdraw(pName, 100.0);
                    Building newBuild = new Building("GeneralStore", buildSel, bName);
                    tData.addBuild(newBuild);
                    tData.saveTownConfig();

                    NPCRegistry registry = CitizensAPI.getNPCRegistry();
                    NPC npc = registry.createNPC(EntityType.PLAYER, "Shop Keeper");
                    npc.addTrait(TraderCharacterTrait.class);
                    npc.addTrait(MobType.class);
                    ((MobType)npc.getTrait(MobType.class)).setType(EntityType.PLAYER);
                    npc.spawn(player.getLocation());
                    TraderCommandExecutor test = new TraderCommandExecutor(CitizensTrader.getInstance());
                    TraderCharacterTrait.TraderType traderType = test.getDefaultTraderType(player);
                    TraderTrait.WalletType walletType = test.getDefaultWalletType(player, traderType);
                    TraderTrait settings = ((TraderCharacterTrait)npc.getTrait(TraderCharacterTrait.class)).getTraderTrait();
                    ((TraderCharacterTrait)npc.getTrait(TraderCharacterTrait.class)).setTraderType(traderType);
                    settings.setWalletType(walletType);
                    settings.setOwner(pName);
                    player.sendMessage(ChatColor.GREEN + "You successfully created a GeneralStore.");
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have the necessary funds!");
                }
            }
            else{
                player.sendMessage(ChatColor.RED + "That selection is not large enough!");
            }
        }
        else{
            player.sendMessage(ChatColor.RED + "You may only have one GeneralStore at a time!");
        }
    }

    /**
     * Creates a bank which gives players in the town the perk of getting double the amount every 15 minutes
     * This is done directly in the Civilizations method which checks the playername for the perk
     * @param tName TownName
     * @param pName PlayerName
     * @param sel AreaSelection
     * @param bName NameofBuilding
     */
    public void bankBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
        Cuboid buildSel = new Cuboid(sel.getMaximumPoint(), sel.getMinimumPoint(),tName);
        if (tData.getBuildNum("generalstore") > 0) {
            if (tData.getBuildNum("bank") == 0) {
                if (buildSel.allowedSizeBig()) {
                    if (Civilizations.econ.has(pName, 200.0)) {
                        Civilizations.econ.bankWithdraw(pName, 200.0);
                        Building newBuild = new Building("Bank", buildSel, bName);
                        tData.addBuild(newBuild);
                        tData.setBankPerk(true);
                        tData.saveTownConfig();
                        player.sendMessage(ChatColor.GREEN + "You successfully created a Bank.");
                        player.sendMessage(ChatColor.GREEN + "You now get double the normal amount every 15 minutes!");
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have the necessary funds!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "That selection is not large enough!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You may only have one Bank at a time!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You must first build a GeneralStore!");
        }
    }
    public void templeBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
        Cuboid buildSel = new Cuboid(sel.getMaximumPoint(), sel.getMinimumPoint(), tName);
        //Need to later figure out what buildings to check for first
        if (tData.getBuildNum("temple") < 4) {
            if (buildSel.allowedSizeBig()) {
                if(buildSel.containsAmountBlock(Material.GOLD_BLOCK, 5)) {
                    if (Civilizations.econ.has(pName, 1000.0)) {
                        Civilizations.econ.bankWithdraw(pName, 1000.0);
                        Building newBuild = new Building("Temple", buildSel, bName);
                        tData.addBuild(newBuild);
                        tData.saveTownConfig();
                        player.sendMessage(ChatColor.GREEN + "You successfully created a Temple.");
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have the necessary funds!");
                    }
                } else{
                    player.sendMessage(ChatColor.RED + "That selection does not contain enough Gold");
                }
            } else {
                player.sendMessage(ChatColor.RED + "That selection is not large enough!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You may only have up to three Temples at a time!");
        }
    }


    public void teleporterBuild(String tName, String pName, Selection sel, String bName){
        Player player = Bukkit.getServer().getPlayer(pName);
        TownDataHandler tData = new TownDataHandler(tName);
    }
}
