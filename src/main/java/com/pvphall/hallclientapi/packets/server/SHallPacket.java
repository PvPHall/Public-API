package com.pvphall.hallclientapi.packets.server;

import com.pvphall.hallclientapi.packets.HallPacket;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketListener;

public abstract class SHallPacket extends HallPacket {

    public void read(PacketDataSerializer packetDataSerializer) { }
    public void handle(PacketListener packetListener) { }
}
