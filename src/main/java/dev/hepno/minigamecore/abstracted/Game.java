package dev.hepno.minigamecore.abstracted;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.instance.Arena;
import dev.hepno.minigamecore.instance.GameState;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class Game implements Listener {

    protected Arena arena;

    public Game(MinigameCore plugin, Arena arena) {
        this.arena = arena;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void start() {
        onStart();
        arena.setState(GameState.INGAME);
        arena.broadcastTitle("Game Started!", "");
    }

    public void removeEvents() {
        HandlerList.unregisterAll(this);
    }

    public abstract void onStart();

}
