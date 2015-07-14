package io.github.theolegaffer.civilizations.events;

import io.github.theolegaffer.civilizations.Economy.econMethods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by Sam on 7/14/2015.
 */
public class LoginListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        econMethods econ = new econMethods(player.getPlayerListName());

        econ.createPlayerConfig();
        econ.createPlayerDefaults();
        econ.savePlayerConfig();
    }
}
