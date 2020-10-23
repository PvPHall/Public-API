package com.pvphall.hallclientapi.api.players;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface IHallPlayer {

    UUID getUUID();
    Player toPlayer();
    boolean isRunningHallClient();
    boolean isRunningAC();
}
