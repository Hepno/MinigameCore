package dev.hepno.minigamecore.manager;

import dev.hepno.minigamecore.MinigameCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    /*
    This class serves as an easy way to access config fields, so that the File doesn't have to be accessed every time,
    as that would cause a lot of lag. It does contain a getConfig() method if needed, but it's not recommended to use it
    unless you really need to, instead, use the individual getters for each field.
     */

    private static FileConfiguration config;

    public static void setupConfig(MinigameCore plugin) {
        ConfigManager.config = plugin.getConfig();
        plugin.saveDefaultConfig();
    }

    public static FileConfiguration getConfig() {
        return ConfigManager.config;
    }

    public static int getRequiredPlayers() {
        return ConfigManager.config.getInt("required-players");
    }

    public static int getCountdown() {
        return ConfigManager.config.getInt("countdown");
    }

    public static int getArenaCount() {
        return ConfigManager.config.getStringList("arenas").size();
    }

    public static Location getLobbySpawn() {
        return new Location(
                Bukkit.getWorld(ConfigManager.config.getString("lobby-spawn.world")),
                ConfigManager.config.getDouble("lobby-spawn.x"),
                ConfigManager.config.getDouble("lobby-spawn.y"),
                ConfigManager.config.getDouble("lobby-spawn.z"),
                (float) ConfigManager.config.getDouble("lobby-spawn.yaw"),
                (float) ConfigManager.config.getDouble("lobby-spawn.pitch")
        );
    }



}
