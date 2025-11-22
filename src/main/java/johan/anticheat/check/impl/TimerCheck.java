/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.check.impl;
import johan.anticheat.check.Check;
import johan.anticheat.profile.PlayerProfile;

public class TimerCheck extends Check {
    public TimerCheck(){ super("Timer",35,20000L,"Too many packets per tick"); }
    @Override public void check(PlayerProfile profile){
        long now=System.currentTimeMillis(),diff=now-profile.getLastTimerReset();
        if(diff>=1000L){
            double rate=profile.getPackets()*1000.0D/diff;
            if(rate>22D) flag(profile,"rate="+String.format("%.1f",rate));
            profile.resetPackets(); profile.setLastTimerReset(now);
        }
    }
}

