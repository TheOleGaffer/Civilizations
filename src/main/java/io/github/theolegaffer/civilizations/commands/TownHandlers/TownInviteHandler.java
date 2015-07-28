package io.github.theolegaffer.civilizations.commands.TownHandlers;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.HashSerializer;
import mondocommand.CallInfo;
import mondocommand.MondoFailure;
import mondocommand.SubHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.io.File;

/**
 * Created by Sam on 7/21/2015.
 */
public class TownInviteHandler implements SubHandler{
    @Override
    public void handle(CallInfo call) throws MondoFailure{
        HashSerializer inviteMap = new HashSerializer(new File("plugins/Civilizations/TownData/invitedplayers.ser"));
        inviteMap.loadFromFile();
        Player pInvName = call.getPlayer();
        String invName = call.getPlayer().getPlayerListName();
        EconomyMethods econTargInv = new EconomyMethods(invName);
        String tName = econTargInv.getTown();
        if(!(tName.equals("none"))){ //the inviter has to be part of a town
            String pName = call.getArg(0);
            Player target = (Bukkit.getServer().getPlayer(pName));
            EconomyMethods econTarg = new EconomyMethods(pName);
            if (!(target == null)) {
                if (econTarg.getTown().equals("none")) { //the invited player cannot be part of a town
                    if (!(inviteMap.containKey(pName))) { //makes sure they aren't already in the hashmap
                        inviteMap.add(pName, tName);
                        inviteMap.saveToFile();
                        target.sendMessage(ChatColor.GREEN + "You have been invited to " + tName + " type /accept or /decline.");
                    }
                    else{
                        call.reply("{RED}That player first needs to accept or decline their previous offer!");
                    }
                } else {
                    pInvName.sendMessage(ChatColor.RED + "They are already part of a town!");
                }
            }
            else {
                pInvName.sendMessage(ChatColor.RED + "That player is not currently online!");
            }
        }
        else{
            pInvName.sendMessage(ChatColor.RED + "You are not part of a town!");
        }
    }




}
