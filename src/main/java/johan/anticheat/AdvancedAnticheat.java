package johan.anticheat;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PostInitEvent;
import johan.anticheat.listener.PlayerListener;
import johan.anticheat.listener.PacketListener;
import johan.anticheat.check.CheckManager;
import johan.anticheat.command.AnticheatCommand;

public class AdvancedAnticheat extends JavaPlugin {

    // Singleton (so other classes can access your plugin instance)
    private static AdvancedAnticheat instance;

    @Override
    public void onEnable() {
        instance = this;

        // Step 1: Check if PacketEvents is installed (fail fast if not)
        if (getServer().getPluginManager().getPlugin("PacketEvents") == null) {
            getLogger().severe("❌ PacketEvents is NOT installed! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Step 2: Wait for PacketEvents to FULLY initialize (API is ready now)
        PacketEvents.getAPI().getEventManager().registerListener(
            new PacketListenerAbstract(PacketListenerPriority.NORMAL) {
                @Override
                public void onPostInit(PostInitEvent event) {
                    // Step 3: Initialize ALL your anticheat features HERE
                    initializeAnticheat();
                }
            }
        );

        getLogger().info("✅ AdvancedAnticheat loaded—waiting for PacketEvents...");
    }

    // Initialize everything ONLY after PacketEvents is ready
    private void initializeAnticheat() {
        // Load config (if you have one)
        saveDefaultConfig();

        // Initialize CheckManager
        new CheckManager();

        // Register Bukkit listener (PlayerListener)
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        // Register PacketEvents listener (YOUR PacketListener.java)
        PacketEvents.getAPI().getEventManager().registerListener(new PacketListener());

        // Register commands (AnticheatCommand)
        getCommand("anticheat").setExecutor(new AnticheatCommand());

        getLogger().info("✅ AdvancedAnticheat fully initialized with PacketEvents!");
    }

    @Override
    public void onDisable() {
        getLogger().info("❌ AdvancedAnticheat disabled.");
        instance = null;
    }

    // Getter for singleton (use this in other classes: AdvancedAnticheat.getInstance())
    public static AdvancedAnticheat getInstance() {
        return instance;
    }
}

