package io.github.theolegaffer.civilizations.commands;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.Towns.Temple;
import io.github.theolegaffer.civilizations.util.Cuboid;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Sam on 4/10/2016.
 */
public class DedicateCommand {
    private final Civilizations plugin;

    public DedicateCommand(Civilizations plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("dedicate")){
            Player player = (Player) sender;
            String pName = player.getPlayerListName().toLowerCase();
            //Checks if they have entered correct number of arguments
            if(args.length == 1) {
                if(Civilizations.econ.has(pName, 50.0)) {
                    Civilizations.econ.bankWithdraw(pName, 50.0);
                    Location location = player.getLocation();
                    EconomyMethods econTarg = new EconomyMethods(pName);
                    String dedicateType = args[0].toLowerCase();
                    if (IsValidType(dedicateType)) {
                        //checks if the user is in a town first
                        if (econTarg.isInTown()) {
                            TownDataHandler newTown = new TownDataHandler(econTarg.getTown());
                            //check if they have a temple first
                            if (!(newTown.getBuildNum("buildings.temple") == 0)) {
                                //check if they are standing in a temple
                                if (newTown.inSpecificBuildingBorder(location, "temple")) {
                                    Temple temple = (Temple) newTown.getBuildingFromLoc(location);
                                    temple.setTempleType(dedicateType);
                                    SetTemplePerk(dedicateType, newTown);
                                    //need to remove perk on town deletion and player removal
                                    //todo
                                } else {
                                    player.sendMessage(ChatColor.RED + "You must be standing in a Temple!");
                                }

                            } else {
                                player.sendMessage(ChatColor.RED + "You don't have a temple to dedicate!");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You aren't in a town currently!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Must dedicate to either [fire], [water], or [air]!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have the necessary funds (50)!");
                }
            } else{
                player.sendMessage(ChatColor.RED + "Not enough arguments! Use /dedicate [type]");
            }
        }
        return true;
    }

    private void SetTemplePerk(String dedicateType, TownDataHandler dataHandler){
        switch (dedicateType) {
            case "water":
                dataHandler.setWaterPerk(true);
                dataHandler.setFirePerk(false);
                dataHandler.setAirPerk(false);
                break;
            case "air":
                dataHandler.setAirPerk(true);
                dataHandler.setWaterPerk(false);
                dataHandler.setFirePerk(false);
                break;
            case "fire":
                dataHandler.setFirePerk(true);
                dataHandler.setAirPerk(false);
                dataHandler.setWaterPerk(false);
                break;
            default:
                return;
        }
    }
    private boolean IsValidType(String dedicateType){
        switch (dedicateType){
            case "water":
                return true;
            case "air":
                return true;
            case "fire":
                return true;
            default:
                return false;
        }
    }

}
