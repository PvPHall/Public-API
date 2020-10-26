package com.pvphall.hallclientapi.api.events;

import com.pvphall.hallclientapi.api.players.IHallPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HCPlayerLeaveEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();
    private final IHallPlayer hallPlayer;

    public HCPlayerLeaveEvent(IHallPlayer hallPlayer) {

        this.hallPlayer = hallPlayer;
    }

    public IHallPlayer getHallPlayer() {

        return this.hallPlayer;
    }

    public static HandlerList getHandlerList() {

        return handlerList;
    }

    public HandlerList getHandlers() {

        return handlerList;
    }
}
