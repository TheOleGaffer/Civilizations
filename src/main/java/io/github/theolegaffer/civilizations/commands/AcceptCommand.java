package io.github.theolegaffer.civilizations.commands;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.HashSerializer;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Set;


/**
 * Created by Sam on 7/22/2015.
 */
public class AcceptCommand implements CommandExecutor {
    private final Civilizations plugin;

    public AcceptCommand(Civilizations plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("accept")) {
            Player player = (Player) sender;
            String pName = player.getPlayerListName().toLowerCase();
            HashSerializer inviteMap = new HashSerializer(new File("plugins/Civilizations/TownData/invitedplayers.ser"));
            inviteMap.loadFromFile();
            if (inviteMap.containKey(pName)) {
                String tName = inviteMap.getValue(pName);
                TownDataHandler newTown = new TownDataHandler(tName);
                EconomyMethods econTarg = new EconomyMethods(pName);
                //Sets the players config and town config
                econTarg.setTown(tName);
                econTarg.savePlayerConfig();
                newTown.addPlayers(pName);
                newTown.saveTownConfig();
                //Deletes player from hashmap for next use
                inviteMap.remove(pName);
                inviteMap.saveToFile();
                //Will work on later with town chat
                player.sendMessage(ChatColor.GREEN + "You are now part of " + tName + "!");
                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + pName + " has joined " + tName + "!");
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "There is nothing for you to accept!");
                player.sendMessage(inviteMap.getValue(pName) + "..." + inviteMap.getValue("spre44") + "..." + inviteMap.getValue("Spre44"));
                return true;
            }
        }
        return false;
    }
}
