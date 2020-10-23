package com.pvphall.hallclientapi.impl.players;

import com.pvphall.hallclientapi.api.players.IHallPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HallPlayer implements IHallPlayer {

    private UUID uuid;
    private boolean runningHallClient;
    private boolean runningAC;

    public HallPlayer(UUID uuid, boolean runningHallClient, boolean runningAC) {

        this.uuid = uuid;
        this.runningHallClient = runningHallClient;
        this.runningAC = runningAC;
    }

    @Override
    public UUID getUUID() {

        return this.uuid;
    }

    @Override
    public Player toPlayer() {

        return Bukkit.getPlayer(this.uuid);
    }

    @Override
    public boolean isRunningHallClient() {

        return this.runningHallClient;
    }

    @Override
    public boolean isRunningAC() {

        return this.runningAC;
    }
}
