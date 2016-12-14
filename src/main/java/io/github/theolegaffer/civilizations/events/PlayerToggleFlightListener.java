package io.github.theolegaffer.civilizations.events;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.logging.Logger;

/**
 * Created by Sam on 7/13/2016.
 */
public class PlayerToggleFlightListener implements Listener{
    private static final Logger log = Logger.getLogger("Minecraft");

    @EventHandler
    public void onDoubleTapSpace(PlayerToggleFlightEvent event){
        Player player = event.getPlayer();
        EconomyMethods storedPlayer = new EconomyMethods(player.getPlayerListName().toLowerCase());
        if(storedPlayer.isInTown()){
            TownDataHandler playersTown = new TownDataHandler(storedPlayer.getTown());
            if(playersTown.getShrineAirPerk()){
//todo
            }
        }
    }
}
