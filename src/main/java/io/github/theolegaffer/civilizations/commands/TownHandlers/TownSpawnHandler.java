package io.github.theolegaffer.civilizations.commands.TownHandlers;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.LocationSerializerWithPY;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Sam on 7/23/2015.
 */
public class TownSpawnHandler implements SubHandler{

    @Override
    public void handle(CallInfo call) throws MondoFailure{
        String pName = call.getPlayer().getPlayerListName();
        Player player = Bukkit.getServer().getPlayer(pName);
        EconomyMethods econTarg = new EconomyMethods(pName);
        String tName = econTarg.getTown();
        TownDataHandler newTown = new TownDataHandler(tName);
        String serializedString = newTown.getSpawn();
        if (!(tName.equals("none"))){
            player.teleport(LocationSerializerWithPY.getDeserializedLocation(serializedString));
            call.reply("{AQUA}Welcome to " + tName);
        }
        else {
            call.reply("{RED}You are not part of a town!");
        }
    }
}
