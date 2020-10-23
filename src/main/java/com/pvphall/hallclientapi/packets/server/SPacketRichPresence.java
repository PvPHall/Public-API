package com.pvphall.hallclientapi.packets.server;

import net.minecraft.server.v1_8_R3.PacketDataSerializer;

public class SPacketRichPresence extends SHallPacket {

    private String richPresence;

    public SPacketRichPresence(String richPresence) {

        this.richPresence = richPresence;
    }

    @Override
    protected void write(PacketDataSerializer packetDataSerializer) {

        packetDataSerializer.a(this.richPresence);
    }
}
