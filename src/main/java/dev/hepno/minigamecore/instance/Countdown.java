package dev.hepno.minigamecore.instance;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.manager.ConfigManager;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private MinigameCore plugin;
    private Arena arena;
    private int seconds;

    public Countdown(MinigameCore plugin, Arena arena) {
        this.plugin = plugin;
        this.arena = arena;
        this.seconds = ConfigManager.getCountdown();
    }

    public void start() {
        arena.setState(GameState.STARTING);
        this.runTaskTimer(plugin, 0, 20);
    }

    @Override
    public void run() {
        if (seconds == 0) {
            arena.setState(GameState.INGAME);
            cancel();
            return;
        }

        seconds--;
    }

}
