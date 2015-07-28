package io.github.theolegaffer.civilizations.events;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.util.Cuboid;
import io.github.theolegaffer.civilizations.util.ListStore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


/**
 * Created by Sam on 7/27/2015.
 */
public class PlayerMoveListener extends JavaPlugin implements Listener{

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Civilizations plugin = new Civilizations();
        ArrayList<Cuboid> border = plugin.getBorderArray();
        for (Cuboid temp : border){
            getLogger().info("inside loop");
            if (temp == null) return;
            getLogger().info("after null");
            if (temp.containsLocation(event.getTo()) && !temp.containsLocation(event.getFrom())){
                player.sendMessage(ChatColor.AQUA + "Welcome to " + temp.getTownName());
            }
        }
    }
}
