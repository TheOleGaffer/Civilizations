package io.github.theolegaffer.civilizations.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

/**
 * Created by Sam on 7/23/2015.
 */
public class LocationSerializerWithPY {
    public static String getSerializedLocation(Location loc) { //Converts location -> String
        return loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch()
                + ";" + loc.getWorld().getUID();
    }

    public static Location getDeserializedLocation(String s) {//Converts String -> Location
        String [] parts = s.split(";");
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double z = Double.parseDouble(parts[2]);
        float yaw = Float.parseFloat(parts[3]);
        float pitch = Float.parseFloat(parts[4]);
        UUID u = UUID.fromString(parts[5]);
        World w = Bukkit.getServer().getWorld(u);
        return new Location(w, x, y, z, yaw, pitch); //can return null if the world no longer exists
    }
}
