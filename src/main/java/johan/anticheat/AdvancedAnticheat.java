package johan.anticheat;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.manager.event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

import johan.anticheat.listener.PacketListener;

public class AdvancedAnticheat extends JavaPlugin {

    @Override
    public void onLoad() {
        // Create PacketEvents instance
        PacketEvents.create(this);
        PacketEvents.get().load();
    }

    @Override
    public void onEnable() {
        // Initialize PacketEvents
        PacketEvents.get().init();

        // Register packet listener
        EventManager eventManager = PacketEvents.get().getEventManager();
        eventManager.registerListener(new PacketListener());

        getLogger().info("AdvancedAnticheat enabled with PacketEvents 2.3.0.");
    }

    @Override
    public void onDisable() {
        PacketEvents.get().terminate();
        getLogger().info("AdvancedAnticheat disabled.");
    }
}
