package io.github.theolegaffer.civilizations.commands.TownHandlers;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.ListStore;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;

import java.io.File;
import java.util.List;

/**
 * Created by Sam on 7/20/2015.
 */
public class TownInfoHandler implements SubHandler{

    @Override
    public void handle(CallInfo call) throws MondoFailure{
        ListStore townList = new ListStore(new File("plugins/Civilizations/TownData/town-list.txt"));
        townList.load();
        int num = call.numArgs();
        String tName;
        String pName =  call.getPlayer().getPlayerListName();
        if (!(num == 0)){
            if (townList.contains(call.getArg(0))) {
                tName = call.getArg(0);
            }
            else{
                tName = "none";
            }
        }
        else{
            EconomyMethods econTarg = new EconomyMethods(pName);
            tName = econTarg.getTown();
        }

        if (tName.equals("none")){
            call.reply("{RED}You need to specify a correct town name.");
        }
        else {
            TownDataHandler tData = new TownDataHandler(tName);
            List<String> nList = tData.getPlayers();
            List<String> buildList = tData.getBuildList();
            String names = "";
            String building = "";
            for (String temp : nList) {
                names = names + temp + " ";
            }
            for (String temp : buildList){
                building = building + temp + " ";
            }
            call.reply("{AQUA}Owner: " + tData.getOwner());
            call.reply("{AQUA}Townmembers: " + names);
            call.reply("{AQUA}Buildings: " + building);
        }
    }
}
