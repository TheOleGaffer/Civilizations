package io.github.theolegaffer.civilizations.commands;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.econMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        if (cmd.getName().equalsIgnoreCase("addmoney")){
            Player target = (Bukkit.getServer().getPlayer(args[0]));
            Player player = (Player) sender;
            if (target != null) {
                if (!(sender instanceof Player)) {
                    int amount = Integer.parseInt(args[1]);

                    econMethods econTarg = new econMethods(target.getPlayerListName());

                    econTarg.giveMoney(amount);
                    econTarg.savePlayerConfig();
                    target.sendMessage("You have been given " + args[1] + " dollars");
                    return true;

                }

                else if (player.hasPermission("economy.addmoney")){
                    int amount = Integer.parseInt(args[1]);

                    econMethods econTarg = new econMethods(target.getPlayerListName());

                    econTarg.giveMoney(amount);
                    econTarg.savePlayerConfig();
                    target.sendMessage("You have been given " + args[1] + " dollars");
                    return true;
                }
                else{
                    player.sendMessage(ChatColor.RED + "You do not have the required permission.");
                    return false;
                }
            }
            else{
                sender.sendMessage(ChatColor.RED + "That player is not currently online.");
            }
        }
        return false;
    }
}
