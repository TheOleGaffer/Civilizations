package io.github.theolegaffer.civilizations.commands.TownHandlers.SetHandlers;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.Cuboid;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by Sam on 7/28/2015.
 */
public class SetBorderHandler implements SubHandler{

    @Override
    public void handle(CallInfo call) throws MondoFailure{
        Player player = call.getPlayer();
        Location loc = player.getLocation();
        String pName = player.getPlayerListName();
        Double locX = loc.getX();
        Double locZ = loc.getZ();
        Double newUpX; Double newUpZ; Double newLowX; Double newLowZ;
        String worldName = loc.getWorld().getName();
        EconomyMethods econTarg = new EconomyMethods(pName);
        String tName = econTarg.getTown();

        if (!(tName.equals("none"))){ //if they are part of a town
            TownDataHandler newTown = new TownDataHandler(tName);
            if (pName.equalsIgnoreCase(newTown.getOwner())){ //if they are the owner of the town
                if (!(newTown.inTownBorder(player.getLocation()))){ //if they are not standing in the town
                    Cuboid border = Cuboid.newDeserialize(newTown.getTownLimits());
                    if(locX > border.getUpperX()){
                        newUpX = locX;
                    }
                    else{
                        newUpX = border.getUpperX();
                    }
                    if (locX < border.getLowerX()){
                        newLowX = locX;
                    }
                    else{
                        newLowX = border.getLowerX();
                    }

                    if(locZ > border.getUpperZ()){
                        newUpZ = locZ;
                    }
                    else{
                        newUpZ = border.getUpperZ();
                    }
                    if (locZ < border.getLowerZ()){
                        newLowZ = locZ;
                    }
                    else{
                        newLowZ = border.getLowerZ();
                    }
                    Cuboid newBorder = new Cuboid(worldName, tName, newLowX, border.getLowerY(), newLowZ, newUpX,border.getUpperY(),newUpZ);
                    newTown.setTownLimits(newBorder.newSerialize());
                    newTown.saveTownConfig();

                    //Won't properly reload the border list right now for playermoveevent (welcome message)
//                    Civilizations plugin = new Civilizations();
//                    plugin.loadTownBordersArray();
                    call.reply("{GREEN}You successfully set the new town border");
                }
                else{
                    call.reply("{RED}You must be standing outside the town!");
                }



            }
            else{
                call.reply("{RED}You must be the owner in order to change the border!");
            }

        }
        else{
            call.reply("{RED}You are not a member of a town!");
        }
    }
}
