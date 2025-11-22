/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.check.impl;
import johan.anticheat.check.Check;
import johan.anticheat.profile.PlayerProfile;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

public class ScaffoldCheck extends Check {
    public ScaffoldCheck(){ super("Scaffold",20,15000L,"Impossible placement angle"); }
    @Override public void check(PlayerProfile profile){
        if(profile.getLastPlaced()==null||profile.getLastPlacedFace()==null) return;
        Vector dir=profile.getPlayer().getLocation().getDirection();
        BlockFace face=profile.getLastPlacedFace();
        double yaw=Math.toDegrees(Math.atan2(-dir.getX(),dir.getZ()));
        double expectedYaw=switch(face){
            case SOUTH->0D; case WEST->90D; case NORTH->180D; case EAST->-90D;
            default->Double.NaN;
        };
        if(!Double.isNaN(expectedYaw)){
            double diff=Math.abs(Math.abs(yaw-expectedYaw)%360);
            if(diff>120D&&diff<240D) flag(profile,"yaw="+String.format("%.1f",yaw));
        }
    }
}

