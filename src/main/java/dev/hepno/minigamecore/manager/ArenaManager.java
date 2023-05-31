package dev.hepno.minigamecore.manager;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.instance.Arena;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();

    public ArenaManager(MinigameCore plugin) {
        FileConfiguration config = plugin.getConfig();

        for (String str : config.getConfigurationSection("arenas.").getKeys(false)) {
            arenas.add(new Arena(Integer.parseInt(str), new Location(
                    plugin.getServer().getWorld(config.getString("arenas." + str + ".spawn.world")),
                    config.getDouble("arenas." + str + ".spawn.x"),
                    config.getDouble("arenas." + str + ".spawn.y"),
                    config.getDouble("arenas." + str + ".spawn.z"),
                    (float) config.getDouble("arenas." + str + ".spawn.yaw"),
                    (float) config.getDouble("arenas." + str + ".spawn.pitch")
            )));
        }
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public Arena getArena(int id) {
        for (Arena arena : arenas) {
            if (arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(Player player) {
        for (Arena arena : arenas) {
            if (arena.getPlayers().contains(player.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }

}
