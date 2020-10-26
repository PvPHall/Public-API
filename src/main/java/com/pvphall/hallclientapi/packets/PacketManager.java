package com.pvphall.hallclientapi.packets;

import com.google.common.collect.BiMap;
import com.pvphall.hallclientapi.packets.client.CPacketHello;
import com.pvphall.hallclientapi.packets.server.SPacketHello;
import com.pvphall.hallclientapi.packets.server.SPacketRichPresence;
import net.minecraft.server.v1_8_R3.EnumProtocol;
import net.minecraft.server.v1_8_R3.EnumProtocolDirection;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PacketManager {

    private static final Map<Class<? extends HallPacket>, PacketType> packets = new HashMap<>();
    private static Map<EnumProtocolDirection, BiMap<Integer, Class<? extends Packet<?>>>> cacheMap;
    private static Map<Class<?>, EnumProtocol> cacheMapPacket;

    // Avoid multiple instance of PacketManager
    private PacketManager() { }

    public static void registerPackets() {

        registerPacket(SPacketHello.class, 74, PacketType.SERVER);
        registerPacket(SPacketRichPresence.class, 75, PacketType.SERVER);
        registerPacket(CPacketHello.class, 26, PacketType.CLIENT);
    }

    // Synchronize this function to avoid multiple map setter in multiple thread
    @SuppressWarnings("unchecked")
    private static synchronized void registerPacket(Class<? extends HallPacket> packet, int id, PacketType sender) {

        packets.put(packet, sender);

        EnumProtocolDirection direction = sender == PacketType.CLIENT ? EnumProtocolDirection.SERVERBOUND : EnumProtocolDirection.CLIENTBOUND;

        try {

            initializeCacheMap();

            BiMap<Integer, Class<? extends Packet<?>>> biMap = cacheMap.get(direction);
            biMap.put(id, (Class<? extends Packet<?>>) packet);
            cacheMap.put(direction, biMap);

            initializeCacheMapPacket();

            cacheMapPacket.put(packet, EnumProtocol.PLAY);

        } catch (IllegalAccessException | NoSuchFieldException e) {

            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static synchronized void initializeCacheMap() throws NoSuchFieldException, IllegalAccessException {

        if(cacheMap == null) {

            Field enumProtocolBiMap = EnumProtocol.class.getDeclaredField("j");
            enumProtocolBiMap.setAccessible(true);
            cacheMap = (Map<EnumProtocolDirection, BiMap<Integer, Class<? extends Packet<?>>>>) enumProtocolBiMap.get(EnumProtocol.PLAY);
        }
    }

    @SuppressWarnings("unchecked")
    private static synchronized void initializeCacheMapPacket() throws NoSuchFieldException, IllegalAccessException {

        if(cacheMapPacket == null) {

            Field enumProtocolMap = EnumProtocol.class.getDeclaredField("h");
            enumProtocolMap.setAccessible(true);
            cacheMapPacket = (Map<Class<?>, EnumProtocol>) enumProtocolMap.get(EnumProtocol.PLAY);
        }
    }

    public static void sendPacket(Player player, HallPacket packet) {

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
