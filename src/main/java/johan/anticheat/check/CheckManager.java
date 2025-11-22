/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.check;
import johan.anticheat.check.impl.*;
import java.util.*;

public class CheckManager {
    private final List<Check> checks=new ArrayList<>();
    public CheckManager(){
        checks.add(new FlightCheck());
        checks.add(new SpeedCheck());
        checks.add(new KillAuraCheck());
        checks.add(new ScaffoldCheck());
        checks.add(new TimerCheck());
        checks.add(new JesusCheck());
    }
    public void runChecks(johan.anticheat.profile.PlayerProfile profile){ checks.forEach(c->c.check(profile)); }
}

