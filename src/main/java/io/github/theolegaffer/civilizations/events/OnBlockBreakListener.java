package io.github.theolegaffer.civilizations.events;

import io.github.theolegaffer.civilizations.Tools;
import io.github.theolegaffer.civilizations.util.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Logger;

/**
 * Created by Sam on 7/13/2016.
 */
public class OnBlockBreakListener implements Listener{
    private static final Logger log = Logger.getLogger("Minecraft");

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(event.isCancelled()) return;
        Block block = event.getBlock();
        String blockLocation = LocationSerializer.getSerializedLocation(block.getLocation());
        if (Tools.replaceBlockMap.containsKey(blockLocation)){
            Player player = event.getPlayer();
            if(Tools.replaceBlockMap.get(blockLocation) == player){
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.OBSIDIAN));
                block.setType(Material.AIR);
                Tools.removeFromReplaceBlock(blockLocation);
                log.info("BlockBreakEvent just dropped obsidian!");
                event.setCancelled(true);
            }
        }
    }
}
