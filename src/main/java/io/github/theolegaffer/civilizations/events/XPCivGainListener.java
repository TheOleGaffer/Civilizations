//package io.github.theolegaffer.civilizations.events;
//
//import com.gmail.nossr50.api.ExperienceAPI;
//import com.gmail.nossr50.datatypes.McMMOPlayer;
//import com.gmail.nossr50.datatypes.PlayerProfile;
//import com.gmail.nossr50.datatypes.SkillType;
//import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
//import com.gmail.nossr50.util.Combat;
//import com.gmail.nossr50.util.Users;
//import io.github.theolegaffer.civilizations.util.ListStore;
//import io.github.theolegaffer.civilizations.util.TownDataHandler;
//import org.bukkit.*;
//import org.bukkit.block.Block;
//import org.bukkit.command.ConsoleCommandSender;
//import org.bukkit.entity.*;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.EventPriority;
//import org.bukkit.event.Listener;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
//import org.bukkit.event.entity.EntityDamageEvent;
//import org.bukkit.event.player.PlayerTeleportEvent;
//import org.bukkit.metadata.MetadataValue;
//import org.bukkit.plugin.Plugin;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;
//import org.bukkit.util.Vector;
//
//import java.io.File;
//import java.util.*;
//
///**
// * Created by Sam on 8/5/2015.
// */
//public class XPCivGainListener implements Listener{
//
////can't get mcmmo event to play ice so swapped over to manually do it
//    @EventHandler(priority = EventPriority.LOWEST)
//    public void onPlayerGainXp(EntityDamageByEntityEvent event){
//        Entity damager = event.getDamager();
//        if (damager instanceof Player){
//            ListStore townList = new ListStore(new File("plugins/Civilizations/TownData/town-list.txt"));
//            townList.load();
//            ArrayList<String> tNames = townList.listReturn();
//            ArrayList<String> axeAdditionalXp = new ArrayList<>();
//            for (String temp : tNames){
//                TownDataHandler newTown = new TownDataHandler(temp);
//                if (newTown.getBuildNum("baracks") > 0){
//                    for (String newP : newTown.getPlayers()){
//                        axeAdditionalXp.add(newP);
//                    }
//                }
//            }
//            Player player = (Player) damager;
//            String pName = player.getPlayerListName().toLowerCase();
////            SkillType skill = event.getSkill();
////            int amount = event.getXpGained();
////            if (amount < 0){
////                try{
////                    throw new Exception("Gained negative XP!");
////                }
////                catch (Exception e){
////                    System.out.println(e.getMessage());
////                    e.printStackTrace();
////                }
////            }
////            else {
//            if (skill == SkillType.AXES) {
//                for (String temp : axeAdditionalXp) {
//                    if (pName.equalsIgnoreCase(temp)) {
//                            //README
//                            //now will install pex instead of this
//                            //use bukkit dispatch command to give the perks permission
//                            //this api call seems fine both cause errors
//    //                        ExperienceAPI.addXP(player, SkillType.AXES, amount);
//    //                        McMMOPlayer testPlayer = new McMMOPlayer(player);
//    //                        testPlayer.addXP(SkillType.AXES,amount);
//                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exec u:" + pName + "a:addperm v:mcmmo.perks.xp.double.axes w:world");
//                        Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "successfully added extra xp");
//                    }
//                }
//            }
//        }
//    }
//}
