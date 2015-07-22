package io.github.theolegaffer.civilizations.commands.TownHandlers;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.ListStore;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;

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
                call.reply("{GREEN}You successfully created a new town named " + tName);
            }
        }
        else{
            call.reply("{RED}That name is already taken!");
        }
    }
}
