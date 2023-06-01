package dev.hepno.minigamecore;

import dev.hepno.minigamecore.listener.ConnectionHandler;
import dev.hepno.minigamecore.listener.GameListeners;
import dev.hepno.minigamecore.manager.ArenaManager;
import dev.hepno.minigamecore.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinigameCore extends JavaPlugin {

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
