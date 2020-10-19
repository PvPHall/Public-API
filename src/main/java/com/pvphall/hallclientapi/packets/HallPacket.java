package com.pvphall.hallclientapi.packets;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketListener;

import java.io.IOException;

public abstract class HallPacket implements Packet {

    @Override
    public void a(PacketDataSerializer packetDataSerializer) throws IOException {

        this.read(packetDataSerializer);
    }

    @Override
    public void b(PacketDataSerializer packetDataSerializer) throws IOException {

        this.write(packetDataSerializer);
    }

    @Override
    public void a(PacketListener packetListener) {

        this.handle(packetListener);
    }

    protected abstract void read(PacketDataSerializer packetDataSerializer);
    protected abstract void write(PacketDataSerializer packetDataSerializer);
    protected abstract void handle(PacketListener packetListener);
}
