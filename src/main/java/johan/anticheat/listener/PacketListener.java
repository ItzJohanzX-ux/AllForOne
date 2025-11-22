package johan.anticheat.listener;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import johan.anticheat.profile.PlayerProfile;
import johan.anticheat.util.PacketUtil;

public class PacketListener extends PacketListenerAbstract {

    // Set priority (NORMAL is fine for most checks)
    public PacketListener() {
        super(PacketListenerPriority.NORMAL);
    }

    // Example: Listen for player movement packets
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.POSITION ||
            event.getPacketType() == PacketType.Play.Client.POSITION_AND_ROTATION) {

            // Get the player's profile (replace with your profile system)
            PlayerProfile profile = PlayerProfile.getByPlayer(event.getPlayer());

            // Run your checks (e.g., FlightCheck, SpeedCheck)
            profile.getCheckManager().runMovementChecks(event);
        }
    }
}

