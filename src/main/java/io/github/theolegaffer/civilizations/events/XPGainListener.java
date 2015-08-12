package io.github.theolegaffer.civilizations.events;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.datatypes.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerExperienceEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.gmail.nossr50.util.Users;
import io.github.theolegaffer.civilizations.util.ListStore;
import io.github.theolegaffer.civilizations.util.TownDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sam on 8/5/2015.
 */
public class XPGainListener implements Listener{



    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerGainXp(McMMOPlayerXpGainEvent event){
        ListStore townList = new ListStore(new File("plugins/Civilizations/TownData/town-list.txt"));
        townList.load();
        ArrayList<String> tNames = townList.listReturn();
        ArrayList<String> axeAdditionalXp = new ArrayList<>();
        for (String temp : tNames){
            TownDataHandler newTown = new TownDataHandler(temp);
            if (newTown.getBuildNum("baracks") > 0){
                for (String newP : newTown.getPlayers()){
                    axeAdditionalXp.add(newP);
                }
            }
        }
        Player player = event.getPlayer();
        String pName = player.getPlayerListName();
        SkillType skill = event.getSkill();
        int amount = event.getXpGained();
        if (skill == SkillType.AXES){
            for (String temp : axeAdditionalXp){
                if(pName.equalsIgnoreCase(temp)){
                    //this api call seems fine both cause errors
//                    ExperienceAPI.addXP(player, SkillType.AXES, amount);
                    PlayerProfile playerName = Users.getProfile(pName);
                    playerName.addXPOverride(SkillType.AXES, amount);
                    Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "successfully added extra xp");
                }
            }
        }
    }
}
