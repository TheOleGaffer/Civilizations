package io.github.theolegaffer.civilizations.events;

import io.github.theolegaffer.civilizations.Civilizations;
import io.github.theolegaffer.civilizations.Economy.EconomyMethods;
import io.github.theolegaffer.civilizations.Tools;
import io.github.theolegaffer.civilizations.util.LocationSerializer;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import net.minecraft.server.BlockObsidian;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 * Created by Sam on 7/10/2016.
 */
public class PlayerInteractListener implements Listener{
    private static final Logger log = Logger.getLogger("Minecraft");

    @EventHandler
    //it may be handled poorly with mcmmo
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getClickedBlock().getType() == Material.OBSIDIAN){
            Player player = event.getPlayer();
            EconomyMethods economyMethods = new EconomyMethods(player.getPlayerListName().toLowerCase());
            if(economyMethods.isInTown()){
                TownDataHandler newTown = new TownDataHandler(economyMethods.getTown());
                if(newTown.getShrineFirePerk()) {
                    event.getClickedBlock().setType(Material.STONE);
                    String blockLocation = LocationSerializer.getSerializedLocation(event.getClickedBlock().getLocation());
                    log.info("Added obsidian to hashmap " + blockLocation);
                    Tools.addToReplaceBlock(blockLocation, player);
                }
            }
        }
    }
}
