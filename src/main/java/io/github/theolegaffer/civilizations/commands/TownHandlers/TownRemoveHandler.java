package io.github.theolegaffer.civilizations.commands.TownHandlers;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Sam on 7/22/2015.
 */
public class TownRemoveHandler implements SubHandler {

    @Override
    public void handle(CallInfo call) throws MondoFailure{
        String sender = call.getPlayer().getPlayerListName();
        Player player = Bukkit.getServer().getPlayer(sender);

        EconomyMethods econTarg = new EconomyMethods(call.getArg(0));
        String tName = econTarg.getTown();
        if(!(tName.equals("none"))){
            TownDataHandler newTown = new TownDataHandler(tName);
            if(sender.equals(newTown.getOwner())){
                econTarg.setTown("none");
                econTarg.savePlayerConfig();
                newTown.removePlayers(call.getArg(0));
                newTown.saveTownConfig();
            }
            else if(player.isOp()){
                tName = call.getArg(1);
                TownDataHandler opTown = new TownDataHandler(tName);
                econTarg.setTown("none");
                econTarg.savePlayerConfig();
                opTown.removePlayers(call.getArg(0));
                opTown.saveTownConfig();
            }
            else{
                call.reply("{RED}You either are not the owner of the town or do not have permission.");
            }
        }
        else{
            call.reply("{RED}That player is not part of a town!");
        }
    }
}
