package io.github.theolegaffer.civilizations.commands.TownHandlers;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.ListStore;
import io.github.theolegaffer.civilizations.util.PerkReloader;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;

import java.io.File;
import java.util.List;

/**
 * Created by Sam on 7/20/2015.
 */
public class TownDeleteHandler implements SubHandler{
    @Override
    public void handle(CallInfo call) throws MondoFailure{
        ListStore townList = new ListStore(new File("plugins/Civilizations/TownData/town-list.txt"));
        townList.load();
        String tName = call.getArg(0);
        String pName = call.getPlayer().getPlayerListName();
        if (townList.contains(tName)) {
            TownDataHandler delTown = new TownDataHandler(tName);
            List<String> nList = delTown.getPlayers();
            if (pName == null || pName.equals(delTown.getOwner())) {
                //Changes player configs
                for (String temp : nList) {
                    EconomyMethods econTarg = new EconomyMethods(temp);
                    econTarg.setTown("none");
                    econTarg.savePlayerConfig();
                    delTown.setPlayerPerks(temp, false);
                }
                PerkReloader.reloadPermissions();
                delTown.deleteTownConfig();
                townList.remove(tName);
                townList.save();
                call.reply("{GREEN}Town successfully deleted.");
            }
            else{
                call.reply("{RED}You are not the owner of that town!");
            }

        }
        else{
            call.reply("{RED}That town does not exist!");
        }
    }
}
