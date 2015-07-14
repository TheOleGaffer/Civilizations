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
public class GiveMoneyCommand implements CommandExecutor {
    private final Civilizations plugin;

    public GiveMoneyCommand(Civilizations plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("givemoney")){
            if(sender instanceof Player) {
                int amount = Integer.parseInt(args[1]);
                Player target = (Bukkit.getServer().getPlayer(args[0]));
                Player player = (Player) sender;

                if (target != null) {
                    econMethods econSend = new econMethods(player.getPlayerListName());
                    econMethods econTarg = new econMethods(target.getPlayerListName());

                    if (econSend.getMoney() - amount < 0) {
                        player.sendMessage(ChatColor.RED + "You cannot give more money then you have.");
                    } else {
                        econTarg.giveMoney(amount);
                        econTarg.savePlayerConfig();
                        econSend.takeMoney(amount);
                        econSend.savePlayerConfig();

                        player.sendMessage("You now have " + econSend.getMoney() + " dollars!");
                        target.sendMessage("You have been given " + args[1] + " dollars");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "That player is not currently online");
                }
            }
        }
        return false;
    }
}
