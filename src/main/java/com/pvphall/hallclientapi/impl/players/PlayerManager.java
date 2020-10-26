package com.pvphall.hallclientapi.impl.players;

import com.pvphall.hallclientapi.api.events.HCPlayerJoinEvent;
import com.pvphall.hallclientapi.api.events.HCPlayerLeaveEvent;
import com.pvphall.hallclientapi.api.players.IHallPlayer;
import com.pvphall.hallclientapi.api.players.IPlayerManager;
import com.pvphall.hallclientapi.exceptions.HallPlayerAlreadyRegisteredException;
import com.pvphall.hallclientapi.exceptions.NoSuchHallPlayerException;
import com.pvphall.hallclientapi.impl.HallAPI;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager implements IPlayerManager {

    private final Map<UUID, IHallPlayer> players;

    public PlayerManager() {

        this.players = new HashMap<>();
    }

    @Override
    public IHallPlayer getHallPlayer(UUID uuid) {

        if(this.players.containsKey(uuid))
            return this.players.get(uuid);
        else
            throw new NoSuchHallPlayerException();
    }

    @Override
    public void addHallClientPlayer(IHallPlayer player) {

        if(this.players.containsKey(player.getUUID()))
            throw new HallPlayerAlreadyRegisteredException();

        boolean hallClientOnly = HallAPI.getInstance().isHallClientOnly();
        boolean acOnly = HallAPI.getInstance().isACOnly();

        if(!player.isRunningHallClient() && (hallClientOnly || acOnly)) {

            player.toPlayer().kickPlayer("§cYou must use PvPHall Client.");
            return;
        }

        if(!player.isRunningAC() && acOnly) {

            player.toPlayer().kickPlayer("§cYou must use PvPHall Client with AC.");
            return;
        }

        this.players.put(player.getUUID(), player);

        Bukkit.getPluginManager().callEvent(new HCPlayerJoinEvent(player));
    }

    @Override
    public IHallPlayer removeHallClientPlayer(UUID uuid) {

        IHallPlayer player = this.players.remove(uuid);

        if(player != null)
            Bukkit.getPluginManager().callEvent(new HCPlayerLeaveEvent(player));

        return player;
    }
}
