package io.github.theolegaffer.civilizations.util;

import io.github.theolegaffer.civilizations.Civilizations;
import org.bukkit.Bukkit;

/**
 * Created by Sam on 10/31/2015.
 */
public class PerkReloader {
    public static void reloadPermissions(){
        Civilizations t = new Civilizations();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(t, new Runnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "permissions save");
            }
        }, 150L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(t, new Runnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "permissions reload");
            }
        }, 200L);
    }
}
