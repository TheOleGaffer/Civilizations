package io.github.theolegaffer.civilizations.commands;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Sam on 7/14/2015.
 */
public class SetMoneyCommand implements CommandExecutor {
    private final Civilizations plugin;

    public SetMoneyCommand(Civilizations plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (cmd.getName().equalsIgnoreCase("setmoney")){
            Player target = (Bukkit.getServer().getPlayer(args[0]));
            Player player = (Player) sender;
            int amount = Integer.parseInt(args[1]);
            //checks if the player is online
            if (target != null) {
                EconomyMethods econTarg = new EconomyMethods(target.getPlayerListName());
                if (amount < 0){
                    sender.sendMessage(ChatColor.RED + "You set a value less than 0.");
                }
                else {
                    //checks if it is the console sending the command or not
                    if (!(sender instanceof Player)) {
                        econTarg.setMoney(amount);
                        econTarg.savePlayerConfig();
                        return true;
                    } else if (player.hasPermission("economy.setmoney")) {
                        econTarg.setMoney(amount);
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
