package com.pvphall.hallclientapi.packets.client;

import com.pvphall.hallclientapi.packets.HallPacket;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;

public abstract class CHallPacket extends HallPacket {

    public void write(PacketDataSerializer packetDataSerializer) { }
}
