package com.pvphall.hallclientapi;

import com.pvphall.hallclientapi.api.IHallAPI;
import com.pvphall.hallclientapi.impl.HallAPI;
import com.pvphall.hallclientapi.listeners.PlayerListener;
import com.pvphall.hallclientapi.packets.PacketManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // Create the instance of HallAPI
        IHallAPI hallAPI = new HallAPI();

        Messenger messenger = this.getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(this, HallAPI.CHANNEL_NAME);

        PacketManager.registerPackets();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);

        this.loadConfig();

        hallAPI.setHallClientOnly(this.getConfig().getBoolean("ALLOW_ONLY_HALLCLIENT"));
        hallAPI.setACOnly(this.getConfig().getBoolean("ALLOW_ONLY_AC"));
    }

    private void loadConfig() {

        FileConfiguration config = this.getConfig();
        config.addDefault("OPTIONS.RICH_PRESENCE_ENABLED", true);
        config.addDefault("OPTIONS.RICH_PRESENCE_TEXT", "PvPHall Network");
        config.addDefault("CONNECTION.ALLOW_ONLY_HALLCLIENT", false);
        config.addDefault("CONNECTION.ALLOW_ONLY_AC", false);

        config.options().header("File configuration for HallClient API. Check https://github.com/PvPHall/Public-API for help.");
        config.options().copyDefaults(true);
        this.saveConfig();
    }
}
