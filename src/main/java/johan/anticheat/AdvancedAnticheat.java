package johan.anticheat;

import johan.anticheat.check.CheckManager;
import johan.anticheat.command.AnticheatCommand;
import johan.anticheat.listener.PlayerListener;
import johan.anticheat.listener.PacketListener;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AdvancedAnticheat extends JavaPlugin {

    private static AdvancedAnticheat instance;
    private CheckManager checkManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        checkManager = new CheckManager();

        // register PacketEvents listener
        PacketEvents.getAPI().getEventManager().registerListener(
                new com.github.retrooper.packetevents.event.PacketListener() {
                    private final PacketListener impl = new PacketListener(); // reuse your class
                    @Override
                    public void onPacketReceive(com.github.retrooper.packetevents.event.PacketReceiveEvent event) {
                        impl.onPacketReceive(event);
                    }
                },
                PacketListenerPriority.LOW
        );

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("anticheat").setExecutor(new AnticheatCommand());

        getLogger().info("§a✅ AdvancedAnticheat has now been Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("§c❌ AdvancedAnticheat has now been Disabled");
        // PacketEvents auto-stops – nothing else to do
    }

    public static AdvancedAnticheat getInstance() {
        return instance;
    }

    public CheckManager getCheckManager() {
        return checkManager;
    }
}

