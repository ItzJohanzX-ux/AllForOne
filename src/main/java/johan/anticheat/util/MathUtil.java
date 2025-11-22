/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.util;
import org.bukkit.Location;
import org.bukkit.Material;

public class MathUtil {
    public static boolean onGround(Location loc){ return loc.clone().subtract(0,0.1,0).getBlock().getType().isSolid(); }
    public static boolean onIce(Location loc){
        Material b=loc.getBlock().getType();
        return b==Material.ICE||b==Material.FROSTED_ICE||b==Material.PACKED_ICE||b==Material.BLUE_ICE;
    }
    public static double hDistance(Location a,Location b){
        double dx=a.getX()-b.getX(),dz=a.getZ()-b.getZ(); return Math.sqrt(dx*dx+dz*dz);
    }
}

