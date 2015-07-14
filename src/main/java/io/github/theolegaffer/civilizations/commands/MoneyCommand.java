package io.github.theolegaffer.civilizations.commands;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.econMethods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Sam on 7/14/2015.
 */

public class MoneyCommand implements CommandExecutor {
    private final Civilizations plugin;

    public MoneyCommand(Civilizations plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("money")){
            Player player = (Player) sender;
            econMethods econ = new econMethods(player.getPlayerListName());
            sender.sendMessage("You have " + econ.getMoney() + " dollars!");
            return true;
        }
        return false;
    }

}
