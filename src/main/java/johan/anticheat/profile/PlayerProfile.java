/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.profile;
import johan.anticheat.check.Check;
import johan.anticheat.util.MathUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.*;

public class PlayerProfile {
    private final Player player; private Location from,to; private double yDelta,lastYDelta; private int packets; private long lastTimerReset=System.currentTimeMillis();
    private final Map<Check,Integer> violations=new HashMap<>(); private Location lastPlaced; private org.bukkit.block.BlockFace lastPlacedFace; private boolean movement,onIce;
    public PlayerProfile(Player player){ this.player=player; }
    public void updateMovement(Location from,Location to){
        this.from=from.clone(); this.to=to.clone(); this.yDelta=to.getY()-from.getY(); this.movement=true; this.onIce=MathUtil.onIce(from)||MathUtil.onIce(to);
    }
    public void addViolation(Check check){ violations.merge(check,1,Integer::sum); }
    public int getViolations(Check check){ return violations.getOrDefault(check,0); }
    public void incrementPackets(){ packets++; }
    public void resetPackets(){ packets=0; }
    public Player getPlayer(){ return player; }
    public Location getFrom(){ return from; }
    public Location getTo(){ return to; }
    public double getyDelta(){ return yDelta; }
    public double getLastYDelta(){ return lastYDelta; }
    public void setLastYDelta(double v){ lastYDelta=v; }
    public int getPackets(){ return packets; }
    public long getLastTimerReset(){ return lastTimerReset; }
    public void setLastTimerReset(long t){ lastTimerReset=t; }
    public boolean isMovement(){ return movement; }
    public boolean isOnIce(){ return onIce; }
    public Location getLastPlaced(){ return lastPlaced; }
    public void setLastPlaced(Location l){ lastPlaced=l; }
    public org.bukkit.block.BlockFace getLastPlacedFace(){ return lastPlacedFace; }
    public void setLastPlacedFace(org.bukkit.block.BlockFace f){ lastPlacedFace=f; }
}

