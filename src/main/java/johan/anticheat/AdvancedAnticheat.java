package johan.anticheat;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import com.github.retrooper.packetevents.manager.event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;
import johan.anticheat.listener.PacketListener;

public class AdvancedAnticheat extends JavaPlugin {

    @Override
    public void onLoad() {
        // Initialize PacketEvents API
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
    }

    @Override
    public void onEnable() {
        // Start PacketEvents
        PacketEvents.getAPI().load();

        // Register packet listeners
        EventManager eventManager = PacketEvents.getAPI().getEventManager();
        eventManager.registerListener(new PacketListener());

        getLogger().info("AdvancedAnticheat enabled with PacketEvents v2.");
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
        getLogger().info("AdvancedAnticheat disabled.");
    }
}
