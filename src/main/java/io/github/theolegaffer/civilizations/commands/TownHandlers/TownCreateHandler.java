package io.github.theolegaffer.civilizations.commands.TownHandlers;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.*;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;


/**
 * Created by Sam on 7/20/2015.
 */
public class TownCreateHandler implements SubHandler{

    @Override
    public void handle(CallInfo call) throws MondoFailure{
        ListStore townList = new ListStore(new File("plugins/Civilizations/TownData/town-list.txt"));
        townList.load();
        String tName = call.getArg(0);
        Location loc1,loc2;
        if (!(townList.contains(tName))) {
            TownDataHandler newTown = new TownDataHandler(tName);
            EconomyMethods econTarg = new EconomyMethods(call.getPlayer().getPlayerListName());
            if (!(econTarg.getTown().equals("none"))) {
                call.reply("{RED}You cannot be in a town already!");
            } else {
                econTarg.setTown(tName);
                econTarg.savePlayerConfig();

                newTown.createTownConfig();
                newTown.createTownDefaults();
                newTown.saveTownConfig();
                newTown.setOwner(call.getPlayer().getPlayerListName());
                newTown.saveTownConfig();
                newTown.addPlayers(call.getPlayer().getPlayerListName());
                newTown.saveTownConfig();
                townList.add(tName);
                townList.save();
                Player player = Bukkit.getServer().getPlayer(call.getPlayer().getPlayerListName());
                newTown.setSpawn(LocationSerializerWithPY.getSerializedLocation(player.getLocation()));
                newTown.saveTownConfig();

                loc1 = player.getLocation();
                loc2 = player.getLocation();
                // the location becomes this number multiplied by 2 (so 30 becomes a 60 by 60 square
                loc1.setX(loc1.getX() + 30);
                loc1.setY(255);
                loc1.setZ(loc1.getZ() + 30);
                loc2.setX(loc2.getX() - 30);
                loc2.setY(0);
                loc2.setZ(loc2.getZ() - 30);
                Cuboid cuboid = new Cuboid(loc1, loc2);
                cuboid.setTownName(tName);
                newTown.setTownLimits(cuboid.newSerialize());
                newTown.saveTownConfig();

                call.reply("{GREEN}You successfully created a new town named " + tName);
                Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "The new town " + tName + " was formed.");
            }
        }
        else{
            call.reply("{RED}That name is already taken!");
        }
    }
}
