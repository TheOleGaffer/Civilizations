package io.github.theolegaffer.civilizations.commands;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.econMethods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Sam on 7/14/2015.
 */
public class AddMoneyCommand implements CommandExecutor {
    private final Civilizations plugin;

    public AddMoneyCommand(Civilizations plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("addmoney")){
            if (!(sender instanceof Player) || (player.hasPermission("economy.addmoney"))){
                int amount = Integer.parseInt(args[1]);
                Player target = (Bukkit.getServer().getPlayer(args[0]));
                econMethods econTarg = new econMethods(target.getPlayerListName());

                econTarg.giveMoney(amount);
                econTarg.savePlayerConfig();
                target.sendMessage("You have been given " + args[1] + " dollars");
                return true;
            }
            else {
                player.sendMessage("You do not have the required permission.");
                return false;
            }
        }
    return false;
    }
}
