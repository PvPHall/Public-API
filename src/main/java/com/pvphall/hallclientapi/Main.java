package com.pvphall.hallclientapi;

import com.pvphall.hallclientapi.impl.HallAPI;
import com.pvphall.hallclientapi.listeners.PlayerListener;
import com.pvphall.hallclientapi.packets.PacketManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // Create the instance of HallAPI
        new HallAPI();

        PacketManager.registerPackets();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
    }
}
