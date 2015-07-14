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
public class RemoveMoneyCommand implements CommandExecutor {
    private final Civilizations plugin;

    public RemoveMoneyCommand(Civilizations plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (cmd.getName().equalsIgnoreCase("removemoney")){
            Player target = (Bukkit.getServer().getPlayer(args[0]));
            Player player = (Player) sender;
            int amount = Integer.parseInt(args[1]);
            if (target != null) {
                econMethods econTarg = new econMethods(target.getPlayerListName());
                if (econTarg.getMoney() - amount < 0){
                    sender.sendMessage(ChatColor.RED + "You cannot remove more money than they have.");
                }
                else {
                    if (!(sender instanceof Player)) {
                        econTarg.takeMoney(amount);
                        econTarg.savePlayerConfig();
                        return true;
                    } else if (player.hasPermission("economy.removemoney")) {
                        econTarg.takeMoney(amount);
                        econTarg.savePlayerConfig();
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have the required permission.");
                        return false;
                    }
                }
            }
            else{
                sender.sendMessage(ChatColor.RED + "That player is not currently online.");
            }
        }
        return false;
    }
}
