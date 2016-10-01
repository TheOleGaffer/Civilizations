package io.github.theolegaffer.civilizations.events;

import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.util.ListStore;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Sam on 7/9/2016.
 */
public class OnDamageListener implements Listener{

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event)
    {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player)event.getEntity();
        if ((event.getCause() == EntityDamageEvent.DamageCause.DROWNING)) {
            EconomyMethods economyMethods = new EconomyMethods(player.getPlayerListName().toLowerCase());
            if(economyMethods.isInTown()){
                TownDataHandler newTown = new TownDataHandler(economyMethods.getTown());
                if(newTown.getWaterPerk()) {
                    event.setCancelled(true);
                }
            }
        }
        if ((event.getCause() == EntityDamageEvent.DamageCause.FIRE) ||(event.getCause() == EntityDamageEvent.DamageCause.LAVA)) {
            EconomyMethods economyMethods = new EconomyMethods(player.getPlayerListName().toLowerCase());
            if(economyMethods.isInTown()){
                TownDataHandler newTown = new TownDataHandler(economyMethods.getTown());
                if(newTown.getFirePerk()) {
                    event.setCancelled(true);
                }
            }
        }
        if ((event.getCause() == EntityDamageEvent.DamageCause.FALL)) {
            EconomyMethods economyMethods = new EconomyMethods(player.getPlayerListName().toLowerCase());
            if(economyMethods.isInTown()){
                TownDataHandler newTown = new TownDataHandler(economyMethods.getTown());
                if(newTown.getAirPerk()) {
                    event.setCancelled(true);
                }
            }
        }

    }

}
