package io.github.theolegaffer.civilizations.events;

import io.github.theolegaffer.civilizations.util.Cuboid;
import io.github.theolegaffer.civilizations.util.ListStore;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 8/6/2015.
 */
public class PlayerMoveListener implements Listener{


//    public void loadTownBordersArray(){
//        ListStore townList = new ListStore(new File("plugins/Civilizations/TownData/town-list.txt"));
//        townList.load();
//        ArrayList<String> tNames = townList.listReturn();
//        ArrayList<Cuboid> townBorders = new ArrayList<>();
//        for (String temp : tNames){
//            TownDataHandler newTown = new TownDataHandler(temp);
//            townBorders.add(Cuboid.newDeserialize(newTown.getTownLimits()));
//        }
//    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        //Loads town borders to an array
        ListStore townList = new ListStore(new File("plugins/Civilizations/TownData/town-list.txt"));
        townList.load();
        ArrayList<String> tNames = townList.listReturn();
        ArrayList<Cuboid> townBorders = new ArrayList<>();
        ArrayList<Cuboid> buildingBorders = new ArrayList<>();
        ArrayList<String> buildMessage = new ArrayList<>();
        for (String temp : tNames){
            TownDataHandler newTown = new TownDataHandler(temp);
            List<String> bList = newTown.getBuildList();

            //Adds the town borders to the list
            townBorders.add(Cuboid.newDeserialize(newTown.getTownLimits()));
            //adds the building borders to the list
            for (String buildName : bList){
                buildingBorders.add(Cuboid.newDeserialize(newTown.getBuildLocation(buildName)));
                buildMessage.add(newTown.getEnterM(buildName));
            }

        }



        //Checks if the player is entering a border
        for (Cuboid temp : townBorders){
            if (temp == null) return;
            if (temp.containsLocation(event.getTo()) && !temp.containsLocation(event.getFrom())){
                player.sendMessage(ChatColor.AQUA + "Welcome to " + temp.getTownName());
            }
        }
        //Checks if they are entering into a building
        int count = 0;
        for (Cuboid temp : buildingBorders){
            if (temp == null) return;
            if (temp.containsLocation(event.getTo()) && !temp.containsLocation(event.getFrom())){
                player.sendMessage(ChatColor.GREEN + buildMessage.get(count));
            }
            count ++;
        }
    }

}
