package com.pvphall.hallclientapi.api.players;

import com.pvphall.hallclientapi.exceptions.NoSuchHallPlayerException;

import java.util.UUID;

public interface IPlayerManager {

    /**
     * Get the IHallPlayer instance for the given uuid.
     *
     * @param uuid - The uuid of the player
     * @return The register IHallPlayer
     *
     * @throws NoSuchHallPlayerException if no player is found
     */
    IHallPlayer getHallPlayer(UUID uuid);

    /**
     * Register a new IHallPlayer
     *
     * @param player - The player to register
     */
    void addHallClientPlayer(IHallPlayer player);

    /**
     * Unregister the HallPlayer instance of the given uuid.
     *
     * @param uuid - The uuid of the player to unregister
     * @return The unregistered IHallPlayer
     */
    IHallPlayer removeHallClientPlayer(UUID uuid);
}
