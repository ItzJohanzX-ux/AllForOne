/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.listener;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import johan.anticheat.AdvancedAnticheat;
import johan.anticheat.check.impl.KillAuraCheck;

public class PacketListener extends PacketListenerAbstract {
    private final KillAuraCheck killAuraCheck=new KillAuraCheck();
    public PacketListener(){ super(PacketListenerPriority.LOW); }
    @Override public void onPacketReceive(PacketReceiveEvent e){
        PlayerListener pl=(PlayerListener)AdvancedAnticheat.getInstance().getServer().getPluginManager().getPlugin("AdvancedAnticheat")
            .getClass().getClassLoader().loadClass("johan.anticheat.listener.PlayerListener").newInstance();
        PlayerProfile prof=pl.getProfile(e.getUser().getUUID());
        if(prof==null) return;
        if(e.getPacketType()==PacketType.Play.Client.PONG) prof.incrementPackets();
        killAuraCheck.onPacketReceive(e);
    }
}

