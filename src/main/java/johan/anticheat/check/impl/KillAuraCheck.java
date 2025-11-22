package johan.anticheat.check.impl;

import johan.anticheat.check.Check;
import johan.anticheat.profile.PlayerProfile;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import java.util.*;

public class KillAuraCheck extends Check {
    private final Map<java.util.UUID,Long> lastSwing=new HashMap<>();
    public KillAuraCheck(){ super("KillAura",30,10000L,"No swing before attack"); }
    @Override public void check(PlayerProfile profile){ /* empty: packet-only */ }
    public void onPacketReceive(PacketReceiveEvent e){
        if(e.getPacketType()==PacketType.Play.Client.ANIMATION) lastSwing.put(e.getUser().getUUID(),System.currentTimeMillis());
        if(e.getPacketType()==PacketType.Play.Client.INTERACT_ENTITY){
            WrapperPlayClientInteractEntity w=new WrapperPlayClientInteractEntity(e);
            if(w.getAction()==WrapperPlayClientInteractEntity.InteractAction.ATTACK){
                long last=lastSwing.getOrDefault(e.getUser().getUUID(),0L);
                if(System.currentTimeMillis()-last>250L){
                    PlayerProfile prof=(PlayerProfile) johan.anticheat.AdvancedAnticheat.getInstance().getCheckManager().getClass().getClassLoader()
                        .loadClass("johan.anticheat.listener.PlayerListener").newInstance().getClass().getMethod("getProfile",java.util.UUID.class)
                        .invoke(johan.anticheat.AdvancedAnticheat.getInstance().getServer().getPluginManager().getPlugin("AdvancedAnticheat"),e.getUser().getUUID());
                    if(prof!=null) flag(prof,"no-swing");
                }
            }
        }
    }
}

