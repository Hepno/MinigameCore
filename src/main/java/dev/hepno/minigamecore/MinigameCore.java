package dev.hepno.minigamecore;

import dev.hepno.minigamecore.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinigameCore extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
