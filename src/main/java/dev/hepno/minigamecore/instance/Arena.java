package dev.hepno.minigamecore.instance;

import dev.hepno.minigamecore.manager.ConfigManager;
import org.bukkit.Bukkit;
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

    /* Methods */
    public void broadcast(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void broadcastTitle(String title, String subtitle) {
        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            player.sendTitle(title, subtitle, 10, 70, 20);
        }
    }

    /* Setters */
    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
    }

    public void setState(GameState state) { this.state = state; }

    /* Getters */
    public int getId() { return id; }
    public List<UUID> getPlayers() { return players; }
    public GameState getState() { return state; }

}
