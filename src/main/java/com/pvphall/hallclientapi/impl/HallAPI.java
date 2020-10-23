package com.pvphall.hallclientapi.impl;

import com.pvphall.hallclientapi.api.IHallAPI;
import com.pvphall.hallclientapi.api.emotes.IEmote;
import com.pvphall.hallclientapi.api.players.IPlayerManager;
import com.pvphall.hallclientapi.impl.players.PlayerManager;
import com.pvphall.hallclientapi.packets.PacketManager;
import com.pvphall.hallclientapi.packets.server.SPacketRichPresence;
import org.bukkit.entity.Player;

import java.util.List;

public class HallAPI implements IHallAPI {

    private static HallAPI instance;
    public static final String CHANNEL_NAME = "HallClient";
    private boolean hallClientOnly;
    private boolean acOnly;

    private IPlayerManager playerManager;

    public HallAPI() {

        instance = this;

        this.playerManager = new PlayerManager();
        this.hallClientOnly = false;
        this.acOnly = false;
    }

    @Override
    public void sendEmote(Player player, IEmote emote, boolean toEveryone) {

    }

    @Override
    public void setDisallowedMods(Player player, List<String> disallowedMods) {

    }

    @Override
    public void setRichPresence(Player player, String richPresence) {

        PacketManager.sendPacket(player, new SPacketRichPresence(richPresence));
    }

    @Override
    public void setHallClientOnly(boolean hallClientOnly) {

        this.hallClientOnly = hallClientOnly;
    }

    @Override
    public boolean isHallClientOnly() {

        return this.hallClientOnly;
    }

    @Override
    public void setACOnly(boolean acOnly) {

        this.acOnly = acOnly;
    }

    @Override
    public boolean isACOnly() {

        return this.acOnly;
    }

    @Override
    public boolean isRunningHallClient(Player player) {

        return this.playerManager.getHallPlayer(player.getUniqueId()).isRunningHallClient();
    }

    @Override
    public boolean isRunningAC(Player player) {

        return this.playerManager.getHallPlayer(player.getUniqueId()).isRunningAC();
    }

    @Override
    public IPlayerManager getPlayerManager() {

        return this.playerManager;
    }

    public static HallAPI getInstance() {

        return instance;
    }
}
