package com.pvphall.hallclientapi.impl.emotes;

import com.pvphall.hallclientapi.api.emotes.IEmote;

public class Emote implements IEmote {

    private String name;

    public Emote(String name) {

        this.name = name;
    }

    public String getName() {

        return this.name;
    }
}
