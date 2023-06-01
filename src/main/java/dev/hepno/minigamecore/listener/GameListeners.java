package dev.hepno.minigamecore.listener;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.instance.Arena;
import dev.hepno.minigamecore.instance.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameListeners implements Listener {

    private MinigameCore plugin;
    public GameListeners(MinigameCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Arena arena = plugin.getArenaManager().getArena(event.getPlayer());
        if (arena == null) return;
        if (arena.getState() != GameState.INGAME) return;
        arena.getGame().addPoint(event.getPlayer().getUniqueId());
    }

}
