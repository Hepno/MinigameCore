package dev.hepno.minigamecore.instance;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    /*
    This class is instanced by Arenas when they enter the STARTING game state. It handles
    counting down and starting the game when the timer reaches 0, if that wasn't apparent from the name.
     */

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
            arena.start();
            cancel();
            return;
        }

        if (seconds % 15 == 0 || seconds <= 10) {
            arena.broadcastTitle(ChatColor.GREEN.toString() + seconds, "");
        }

        seconds--;
    }

}
