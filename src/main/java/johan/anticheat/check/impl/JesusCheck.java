/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.check.impl;
import johan.anticheat.check.Check;
import johan.anticheat.profile.PlayerProfile;
import johan.anticheat.util.MathUtil;
import org.bukkit.Material;

public class JesusCheck extends Check {
    public JesusCheck(){ super("Jesus",15,20000L,"Walking on water"); }
    @Override public void check(PlayerProfile profile){
        if(profile.getPlayer().getLocation().getBlock().getType()==Material.WATER&&
           !MathUtil.onGround(profile.getFrom())&&!profile.getPlayer().isSwimming()&&
           profile.getyDelta()==0D) flag(profile,"water-walk");
    }
}

