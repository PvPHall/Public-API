package com.pvphall.hallclientapi.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.FieldUtils;
import com.comphenix.protocol.reflect.accessors.Accessors;
import com.google.common.collect.BiMap;
import com.pvphall.hallclientapi.packets.client.CPacketHello;
import com.pvphall.hallclientapi.packets.server.SPacketHello;
import com.pvphall.hallclientapi.packets.server.SPacketRichPresence;
import net.minecraft.server.v1_8_R3.EnumProtocol;
import net.minecraft.server.v1_8_R3.EnumProtocolDirection;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PacketManager {

    private static final Map<Class<? extends HallPacket>, PacketType> packets = new HashMap<Class<? extends HallPacket>, PacketType>();

    public static void registerPackets() {

        registerPacket(SPacketHello.class, 74, PacketType.Sender.SERVER);
        registerPacket(SPacketRichPresence.class, 75, PacketType.Sender.SERVER);
        registerPacket(CPacketHello.class, 26, PacketType.Sender.CLIENT);
    }

    private static void registerPacket(Class<? extends HallPacket> packet, int id, PacketType.Sender sender) {

        PacketType packetType = new PacketType(PacketType.Protocol.PLAY, sender, id, id);
        packets.put(packet, packetType);

        EnumProtocolDirection direction = packetType.isClient() ? EnumProtocolDirection.SERVERBOUND : EnumProtocolDirection.CLIENTBOUND;

        try {

            Map<EnumProtocolDirection, BiMap<Integer, Class<? extends Packet<?>>>> map = (Map<EnumProtocolDirection, BiMap<Integer, Class<? extends Packet<?>>>>) FieldUtils.readField(EnumProtocol.PLAY, "j", true);
            BiMap<Integer, Class<? extends Packet<?>>> biMap = map.get(direction);
            biMap.put(id, (Class<? extends Packet<?>>) packet);
            map.put(direction, biMap);

        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }

        Map<Class<?>, EnumProtocol> map = (Map<Class<?>, EnumProtocol>) Accessors.getFieldAccessor(EnumProtocol.class, Map.class, true).get(EnumProtocol.PLAY);
        map.put(packet, EnumProtocol.PLAY);
    }

    public static void sendPacket(Player player, HallPacket packet) {

        PacketContainer container = new PacketContainer(packets.get(packet.getClass()), packet);

        try {

            ProtocolLibrary.getProtocolManager().sendServerPacket(player, container);

        } catch (InvocationTargetException e) {

            e.printStackTrace();
        }
    }

    public static PacketType getPacketType(Class<? extends HallPacket> packet) {

        return packets.get(packet);
    }
}
