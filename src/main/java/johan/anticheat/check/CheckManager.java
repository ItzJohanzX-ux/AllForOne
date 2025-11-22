package johan.anticheat.check;

import johan.anticheat.check.impl.*;
import johan.anticheat.listener.PlayerListener;
import johan.anticheat.profile.PlayerProfile;
import org.bukkit.Bukkit;

import java.util.*;

public class CheckManager {

    private final List<Check> checks = new ArrayList<>();

    public CheckManager(){
        checks.add(new FlightCheck());
        checks.add(new SpeedCheck());
        checks.add(new KillAuraCheck());
        checks.add(new ScaffoldCheck());
        checks.add(new TimerCheck());
        checks.add(new JesusCheck());
    }

    public void runChecks(PlayerProfile profile){
        checks.forEach(c -> c.check(profile));
    }

    public List<Check> getChecks(){
        return checks;
    }

    // quick helper to grab a profile via the singleton listener
    public PlayerProfile getProfile(UUID uuid){
        PlayerListener listener = (PlayerListener) Bukkit.getPluginManager()
                .getPlugin("AdvancedAnticheat")
                .getClass()
                .getClassLoader()
                .loadClass("johan.anticheat.listener.PlayerListener")
                .newInstance();
        return listener.getProfile(uuid);
    }
}

