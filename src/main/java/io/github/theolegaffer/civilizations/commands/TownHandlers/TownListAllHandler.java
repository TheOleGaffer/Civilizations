package io.github.theolegaffer.civilizations.commands.TownHandlers;

import io.github.theolegaffer.civilizations.util.ListStore;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sam on 7/22/2015.
 */
public class TownListAllHandler implements SubHandler{

    @Override
    public void handle(CallInfo call) throws MondoFailure{
        ListStore townList = new ListStore(new File("plugins/Civilizations/TownData/town-list.txt"));
        townList.load();
        ArrayList<String> list = townList.listReturn();
        String sList = "";
        for (String temp : list){
            sList = sList + temp + " ";
        }
        call.reply("{AQUA}" + sList);
    }

}
