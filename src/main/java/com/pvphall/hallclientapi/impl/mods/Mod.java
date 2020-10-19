package com.pvphall.hallclientapi.impl.mods;

import com.pvphall.hallclientapi.api.mods.IMod;

public class Mod implements IMod {

    private String name;

    public Mod(String name) {

        this.name = name;
    }

    public String getName() {

        return this.name;
    }
}
