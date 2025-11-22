/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.check.impl;
import johan.anticheat.check.Check;
import johan.anticheat.profile.PlayerProfile;
import johan.anticheat.util.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.potion.PotionEffectType;

public class SpeedCheck extends Check {
    public SpeedCheck(){ super("Speed",25,15000L,"Impossible horizontal movement"); }
    @Override public void check(PlayerProfile profile){
        if(profile.getPlayer().getGameMode()==GameMode.CREATIVE||profile.getPlayer().isInsideVehicle()) return;
        double hDist=MathUtil.hDistance(profile.getFrom(),profile.getTo()),base=0.2873D;
        if(profile.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) base+=0.2D*(profile.getPlayer().getPotionEffect(PotionEffectType.SPEED).getAmplifier()+1);
        if(profile.getPlayer().isSprinting()) base*=1.3D;
        if(profile.isOnIce()) base*=2.5D;
        if(hDist>base+0.1D) flag(profile,"hd="+String.format("%.3f",hDist));
    }
}

