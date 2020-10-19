package com.pvphall.hallclientapi.impl;

import com.pvphall.hallclientapi.api.IHallAPI;
import com.pvphall.hallclientapi.api.emotes.IEmote;
import org.bukkit.entity.Player;

import java.util.List;

public class HallAPI implements IHallAPI {

    private static HallAPI instance;

    public HallAPI() {

        instance = this;
    }

    public void sendEmote(Player player, IEmote emote, boolean toEveryone) {

    }

    public void setDisallowedMods(Player player, List<String> disallowedMods) {

    }

    public void setRichPresence(Player player, String richPresence) {

    }

    public void setHallClientOnly(boolean hallClientOnly) {

    }

    public void setACOnly(boolean acOnly) {

    }

    public boolean isRunningHallClient(Player player) {
        return false;
    }

    public boolean isRunningAC(Player player) {
        return false;
    }

    public static HallAPI getInstance() {

        return instance;
    }
}
