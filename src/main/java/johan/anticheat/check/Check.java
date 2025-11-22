/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.check;

import johan.anticheat.profile.PlayerProfile;
import org.bukkit.Bukkit;

public abstract class Check {
    protected final String name; protected final int max; protected final long decay; protected final String description;
    public Check(String name,int max,long decay,String description){
        this.name=name;this.max=max;this.decay=decay;this.description=description;
    }
    public abstract void check(PlayerProfile profile);
    protected void flag(PlayerProfile profile,String detail){
        profile.addViolation(this);
        Bukkit.getOnlinePlayers().stream().filter(p->p.hasPermission("anticheat.alert")).forEach(p->
            p.sendMessage("§c§lAC §8» §7"+profile.getPlayer().getName()+" §ffailed §e"+name+" §7("+detail+") §8[§f"+profile.getViolations(this)+"§8]"));
    }
}

