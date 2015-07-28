package io.github.theolegaffer.civilizations.commands.TownHandlers;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;

/**
 * Created by Sam on 7/22/2015.
 */
public class TownLeaveHandler implements SubHandler{

    @Override
    public void handle(CallInfo call) throws MondoFailure{
        String sender = call.getPlayer().getPlayerListName();
        EconomyMethods econTarg = new EconomyMethods(sender);
        String tName = econTarg.getTown();
        if (!(tName.equals("none"))) {
            TownDataHandler newTown = new TownDataHandler(tName);
            if (!(sender.equals(newTown.getOwner()))) {
                econTarg.setTown("none");
                econTarg.savePlayerConfig();
                newTown.removePlayers(sender);
                newTown.saveTownConfig();
            }
            else{
                call.reply("{RED}You cannot leave the town if you are the owner. Use the /town delete command instead.");
            }
        }
        else{
            call.reply("{RED}You are not part of a town right now!");
        }
    }
}
