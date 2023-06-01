package dev.hepno.minigamecore;

import dev.hepno.minigamecore.listener.ConnectionHandler;
import dev.hepno.minigamecore.manager.ArenaManager;
import dev.hepno.minigamecore.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinigameCore extends JavaPlugin {

    /*
    Welcome to the MinigameCore source code!
    This plugin is meant to be a framework for creating minigames, I made it because
    after rewriting this exact code for the 30th time, I decided it was time to make
    a framework for it.

    This was made for me, and the target audience is myself, but if you want to use it,
    go ahead! I don't mind at all, but support will be limited. I will have a tiny
    explanation of the code in a comment above each class (like this one), but they won't
    be very in-depth.

    Credit to Stephen King for writing a large portion of the code.
     */

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);
        arenaManager = new ArenaManager(this);
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new GameListeners(this), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionHandler(this), this);
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }
}
