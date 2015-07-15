package io.github.theolegaffer.civilizations.Economy;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created by Sam on 7/13/2015.
 * Schedules all online players to receive money every 10 minutes
 */
public class incrementSched extends JavaPlugin {

    public void incrementTask(){
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player target:getServer().getOnlinePlayers()){
                    String name = target.getName();
                    econMethods playerData = new econMethods(name);
                    playerData.giveMoney(20);
                    playerData.savePlayerConfig();
                }
            }
        }, 0, 18000);
        this.getLogger().info("Added money");

    }
}
