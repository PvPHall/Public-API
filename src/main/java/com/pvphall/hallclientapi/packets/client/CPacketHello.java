package com.pvphall.hallclientapi.packets.client;

import com.pvphall.hallclientapi.impl.HallAPI;
import com.pvphall.hallclientapi.impl.players.HallPlayer;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketListener;

import java.util.UUID;

public class CPacketHello extends CHallPacket {

    private UUID uuid;
    private boolean acEnabled;

    @Override
    protected void read(PacketDataSerializer packetDataSerializer) {

        this.uuid = packetDataSerializer.g();
        this.acEnabled = packetDataSerializer.readBoolean();
    }

    @Override
    protected void handle(PacketListener packetListener) {

        HallAPI.getInstance().getPlayerManager().addHallClientPlayer(new HallPlayer(this.uuid, true, this.acEnabled));
    }
}
