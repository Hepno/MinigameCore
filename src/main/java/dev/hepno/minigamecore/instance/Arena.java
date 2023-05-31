package dev.hepno.minigamecore.instance;

import dev.hepno.minigamecore.manager.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private int id;
    private Location spawn;

    private List<UUID> players = new ArrayList<>();
    private GameState state;

    public Arena(int id, Location spawn) {
        this.id = id;
        this.spawn = spawn;
        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
    }

    public int getId() { return id; }
    public List<UUID> getPlayers() { return players; }
    public GameState getState() { return state; }

}
