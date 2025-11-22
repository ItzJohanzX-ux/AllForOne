package johan.anticheat.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class PacketUtil {

    /**
     * Sends a raw NMS packet to a player using reflection.
     * Works for Paper 1.20 – 1.21+
     */
    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = getHandle(player);

            Object connection = handle.getClass().getField("c").get(handle); // Player connection

            Method sendMethod = connection.getClass().getMethod("a", getPacketClass());
            sendMethod.invoke(connection, packet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the NMS entity handle for a Player
     */
    private static Object getHandle(Object craftPlayer) throws Exception {
        Method getHandle = craftPlayer.getClass().getMethod("getHandle");
        return getHandle.invoke(craftPlayer);
    }

    /**
     * Finds the Packet class dynamically
     */
    private static Class<?> getPacketClass() throws ClassNotFoundException {
        return Class.forName(getNMSClass("network.protocol.Packet"));
    }

    /**
     * Automatically handles new Paper package names (like 1.20+)
     */
    private static String getNMSClass(String name) {
        // Paper 1.20+ NMS path (Mojang-mapped)
        return "net.minecraft." + name;
    }

    /**
     * Broadcast a packet to all players
     */
    public static void broadcastPacket(Object packet) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendPacket(player, packet);
        }
    }
}
