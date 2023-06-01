package dev.hepno.minigamecore.listener;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.instance.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionHandler implements Listener {

    private MinigameCore plugin;
    public ConnectionHandler(MinigameCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Arena arena = plugin.getArenaManager().getArena(event.getPlayer());
        if (arena == null) return;
        arena.removePlayer(event.getPlayer());
    }

}
