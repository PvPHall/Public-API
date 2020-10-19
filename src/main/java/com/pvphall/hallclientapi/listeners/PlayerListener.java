package com.pvphall.hallclientapi.listeners;

import com.pvphall.hallclientapi.Main;
import com.pvphall.hallclientapi.packets.PacketManager;
import com.pvphall.hallclientapi.packets.server.SPacketHello;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private Main main;

    public PlayerListener(Main main) {

        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        PacketManager.sendPacket(event.getPlayer(), new SPacketHello());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {


    }
}
