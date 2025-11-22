package johan.anticheat.listener;

import 
com.github.retrooper.packetevents.event.PacketListenerAbstract;
import 
com.github.retrooper.packetevents.event.PacketListenerPriority;
import 
com.github.retrooper.packetevents.event.PacketReceiveEvent;
import 
com.github.retrooper.packetevents.protocol.packettype.PacketType;
import johan.anticheat.AdvancedAnticheat;
import johan.anticheat.profile.PlayerProfile;

public class PacketListener extends 
PacketListenerAbstract {

    public PacketListener(){ 
super(PacketListenerPriority.LOW); }

    @Override
    public void onPacketReceive(PacketReceiveEvent 
e){
        // PE 2.10.1 constant names
        if(e.getPacketType() == 
PacketType.Play.Client.PLAYER_FLYING ||
           e.getPacketType() == 
PacketType.Play.Client.PLAYER_POSITION ||
           e.getPacketType() == 
PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION) {

            PlayerProfile prof = 
AdvancedAnticheat.getInstance()
                    .getCheckManager()
                    .getProfile(e.getUser().getUUID());
            if(prof != null) 
prof.incrementPackets();
        }
    }
}


