package com.pvphall.hallclientapi.listeners;

import com.pvphall.hallclientapi.Main;
import com.pvphall.hallclientapi.impl.HallAPI;
import com.pvphall.hallclientapi.impl.players.HallPlayer;
import com.pvphall.hallclientapi.packets.PacketManager;
import com.pvphall.hallclientapi.packets.server.SPacketHello;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerListener implements Listener {

    private Main main;
    private List<UUID> registeredPlayers;

    public PlayerListener(Main main) {

        this.main = main;
        this.registeredPlayers = new ArrayList<>();
    }

    @EventHandler
    public void onChannelRegister(PlayerRegisterChannelEvent event) {

        if(!event.getChannel().equals(HallAPI.CHANNEL_NAME))
            return;

        this.registeredPlayers.add(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        // Delay the send of HelloPacket
        new BukkitRunnable() {

            @Override
            public void run() {

                UUID uuid = player.getUniqueId();

                if(PlayerListener.this.registeredPlayers.contains(uuid))
                    PacketManager.sendPacket(event.getPlayer(), new SPacketHello());
                else
                    HallAPI.getInstance().getPlayerManager().addHallClientPlayer(new HallPlayer(uuid, false, false));
            }

        }.runTaskLater(this.main, 2);

        // Set rich presence if enabled
        FileConfiguration config = this.main.getConfig();

        if(config.getBoolean("OPTIONS.RICH_PRESENCE_ENABLED"))
            HallAPI.getInstance().setRichPresence(player, config.getString("OPTIONS.RICH_PRESENCE_TEXT"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        this.onLeave(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {

        this.onLeave(event.getPlayer().getUniqueId());
    }

    private void onLeave(UUID uuid) {

        this.registeredPlayers.remove(uuid);
        HallAPI.getInstance().getPlayerManager().removeHallClientPlayer(uuid);
    }
}
