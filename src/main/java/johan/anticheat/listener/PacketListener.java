package johan.anticheat.listener;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import johan.anticheat.AdvancedAnticheat;
import johan.anticheat.check.impl.KillAuraCheck;
import johan.anticheat.profile.PlayerProfile;

public class PacketListener extends PacketListenerAbstract {
    private final KillAuraCheck killAuraCheck=new KillAuraCheck();
    public PacketListener(){ super(PacketListenerPriority.LOW); }
    @Override public void onPacketReceive(PacketReceiveEvent e){
        PlayerListener pl=(PlayerListener) AdvancedAnticheat.getInstance().getServer().getPluginManager().getPlugin("AdvancedAnticheat");
        PlayerProfile prof=pl==null?null:pl.getProfile(e.getUser().getUUID());
        if(prof==null) return;
        if(e.getPacketType()==PacketType.Play.Client.PONG) prof.incrementPackets();
        killAuraCheck.onPacketReceive(e);
    }
}

