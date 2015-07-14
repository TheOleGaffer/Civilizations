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
public class GiveMoneyCommand implements CommandExecutor {
    private final Civilizations plugin;

    public GiveMoneyCommand(Civilizations plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("givemoney")){
            int amount = Integer.parseInt(args[1]);
            Player target = (Bukkit.getServer().getPlayer(args[0]));
            Player player = (Player) sender;

            econMethods econSend = new econMethods(player.getPlayerListName());
            econMethods econTarg = new econMethods(target.getPlayerListName());

            econTarg.giveMoney(amount);
            econTarg.savePlayerConfig();
            econSend.takeMoney(amount);
            econSend.savePlayerConfig();

            player.sendMessage("You now have " + econSend.getMoney() + " dollars!");
            target.sendMessage("You have been given " + args[1] + " dollars");
            return true;
        }
        return false;
    }
}
