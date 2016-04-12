package io.github.theolegaffer.civilizations.commands;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.util.HashSerializer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sam on 7/22/2015.
 */
public class DeclineCommand implements CommandExecutor{
    private final Civilizations plugin;

    public DeclineCommand(Civilizations plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("decline")){
            Player player = (Player) sender;
            String pName = player.getPlayerListName().toLowerCase();
            HashSerializer inviteMap = new HashSerializer(new File("plugins/Civilizations/TownData/invitedplayers.ser"));
            inviteMap.loadFromFile();
            if(inviteMap.containKey(pName)){
                //Deletes player from the hashmap for next use
                inviteMap.remove(pName);
                inviteMap.saveToFile();
                player.sendMessage(ChatColor.GREEN + "You successfully declined the offer.");
            }
            else{
                player.sendMessage(ChatColor.RED + "There is nothing for you to decline!");
                return true;
            }
        }
        return false;
    }
}

