/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.listener;
import johan.anticheat.AdvancedAnticheat;
import johan.anticheat.profile.PlayerProfile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import java.util.*;

public class PlayerListener implements Listener {
    private final Map<java.util.UUID,PlayerProfile> profiles=new java.util.concurrent.ConcurrentHashMap<>();
    @EventHandler public void onJoin(PlayerJoinEvent e){ profiles.put(e.getPlayer().getUniqueId(),new PlayerProfile(e.getPlayer())); }
    @EventHandler public void onQuit(PlayerQuitEvent e){ profiles.remove(e.getPlayer().getUniqueId()); }
    @EventHandler public void onMove(PlayerMoveEvent e){
        if(e.hasChangedBlock()){
            PlayerProfile prof=profiles.get(e.getPlayer().getUniqueId());
            if(prof!=null){ prof.updateMovement(e.getFrom(),e.getTo()); AdvancedAnticheat.getInstance().getCheckManager().runChecks(prof); prof.setLastYDelta(prof.getyDelta()); }
        }
    }
    @EventHandler public void onPlace(BlockPlaceEvent e){
        PlayerProfile prof=profiles.get(e.getPlayer().getUniqueId());
        if(prof!=null){ prof.setLastPlaced(e.getBlock().getLocation()); prof.setLastPlacedFace(e.getBlockAgainst().getFace(e.getBlock())); }
    }
    public PlayerProfile getProfile(java.util.UUID uuid){ return profiles.get(uuid); }
}

