package io.github.theolegaffer.civilizations;

//import com.sk89q.worldedit.Location;
//import com.sk89q.worldedit.Vector;
//import com.sk89q.worldedit.bukkit.WorldEditPlugin;
//import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
//import com.sk89q.worldedit.bukkit.selections.Selection;
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.Material;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import org.bukkit.plugin.Plugin;
import io.github.theolegaffer.civilizations.util.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * Created by Sam on 7/13/2015.
 */
public class Tools {
    public static HashMap<String, Player> replaceBlockMap = null;
    public static HashMap<String, >

    public static void setupBlockChanger(){
        replaceBlockMap = new HashMap<String, Player>();
    }
    public static void resetBlocks(){
        if (!(replaceBlockMap.isEmpty())) {
            for (String key : replaceBlockMap.keySet()) {
                Location location = LocationSerializer.getDeserializedLocation(key);
                Block block = location.getBlock();
                block.setType(Material.OBSIDIAN);
            }
        }
    }
    public static void addToReplaceBlock(String locationOfBlock, Player player){
        replaceBlockMap.put(locationOfBlock,player);
    }
    public static void removeFromReplaceBlock(String locationOfBlock){
        replaceBlockMap.remove(locationOfBlock);
    }
}

