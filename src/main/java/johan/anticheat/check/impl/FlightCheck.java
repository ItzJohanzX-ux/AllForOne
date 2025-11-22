/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.check.impl;
import johan.anticheat.check.Check;
import johan.anticheat.profile.PlayerProfile;
import johan.anticheat.util.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

public class FlightCheck extends Check {
    public FlightCheck(){ super("Flight",20,20000L,"Impossible vertical movement"); }
    @Override public void check(PlayerProfile profile){
        if(!profile.isMovement()||profile.getPlayer().getGameMode()==GameMode.CREATIVE||profile.getPlayer().isInsideVehicle()||profile.getPlayer().getLocation().getBlock().getType()==Material.WATER) return;
        double yDelta=profile.getTo().getY()-profile.getFrom().getY(),lastY=profile.getLastYDelta();
        double predicted=(lastY-0.08D)*0.9800000190734863D,diff=Math.abs(yDelta-predicted);
        double threshold=profile.getPlayer().hasPotionEffect(PotionEffectType.JUMP)?.42D:.02D;
        if(diff>threshold&&yDelta>0&&!MathUtil.onGround(profile.getTo())) flag(profile,"yd="+String.format("%.3f",yDelta));
    }
}

