package io.github.theolegaffer.civilizations.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

/**
 * Created by Sam on 7/23/2015.
 * taken from https://bukkit.org/threads/serializing-locations.367466/
 */
public class LocationSerializer {

    public static String getSerializedLocation(Location loc) { //Converts location -> String
        return loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getWorld().getUID();

    }

    public static Location getDeserializedLocation(String s) {//Converts String -> Location
        String [] parts = s.split(";");
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double z = Double.parseDouble(parts[2]);
        UUID u = UUID.fromString(parts[3]);
        World w = Bukkit.getServer().getWorld(u);
        return new Location(w, x, y, z); //can return null if the world no longer exists
    }

}
