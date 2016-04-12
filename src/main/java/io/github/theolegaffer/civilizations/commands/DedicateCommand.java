package io.github.theolegaffer.civilizations.commands;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            //Checks if they have entered correct number of arguments
            if(!(args.length < 2)) {
                String pName = player.getPlayerListName().toLowerCase();
                Location location = player.getLocation();
                EconomyMethods econTarg = new EconomyMethods(pName);
                String tName = econTarg.getTown();
                //checks if the user is in a town first
                if(!(tName.equals("none"))){
                    TownDataHandler newTown = new TownDataHandler(tName);
                    //check if they have a temple first
                    if(!(newTown.getBuildNum("buildings.temple") == 0)){
                        //todo
                    } else{
                        player.sendMessage(ChatColor.RED + "You don't have a temple to dedicate!");
                    }
                } else{
                    player.sendMessage(ChatColor.RED + "You aren't in a town currently!");
                }

            } else{
                player.sendMessage(ChatColor.RED + "Not enough arguments! Use /dedicate [templename] [type]");
            }
        }
        return true;
    }

}
