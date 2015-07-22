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
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Sam on 7/13/2015.
 */
public class Tools extends JavaPlugin {
//    private Selection s;
//
//    public WorldEditPlugin getWorldEdit(){
//        Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
//        if (p instanceof WorldEditPlugin) return (WorldEditPlugin) p;
//        else{
//            this.getLogger().info("WorldEdit is not installed on this server.");
//
//            return null;
//        }
//
//    }
//
//    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
//        if (!(sender instanceof Player)){
//            sender.sendMessage(ChatColor.RED + "The console cannot use /setbuild");
//            return true;
//        }
//        Player p = (Player) sender;
//
//        if (cmd.getName().equalsIgnoreCase("setbuild")){
//            s = getWorldEdit().getSelection(p);
//            if (s == null) {
//                sender.sendMessage(ChatColor.RED + "Select two points first!");
//                return true;
//            }
//            else{
//                sender.sendMessage(ChatColor.GREEN + "Set area!");
//            }
//
//        }
//        return false;
//    }

}
//    Selection sel = worldEditPlugin.getSelection(player);
//
//    if (sel instanceof CuboidSelection) {
//        Vector min = sel.getNativeMinimumPoint();
//        Vector max = sel.getNativeMaximumPoint();
//        for(int x = min.getBlockX();x <= max.getBlockX(); x=x+1){
//            for(int y = min.getBlockY();y <= max.getBlockY(); y=y+1){
//                for(int z = min.getBlockZ();z <= max.getBlockZ(); z=z+1){
//                    Location tmpblock = new Location(player.getWorld(), x, y, z);
//                    tmpblock.getBlock().setType(Material.AIR); //Just For debug
//                }
//            }
//        }
//        player.sendMessage(ChatColor.AQUA + "Undo Complete!");
//    }else{
//        player.sendMessage(ChatColor.DARK_RED + "Invalid Selection!");
//    }

