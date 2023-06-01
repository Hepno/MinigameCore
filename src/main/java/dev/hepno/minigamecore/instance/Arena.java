package dev.hepno.minigamecore.instance;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.abstracted.Game;
import dev.hepno.minigamecore.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private MinigameCore plugin;

    private int id;
    private Location spawn;

    private List<UUID> players = new ArrayList<>();
    private GameState state;
    private Countdown countdown;
    private Game game;

    public Arena(MinigameCore plugin, int id, Location spawn) {
        this.plugin = plugin;
        this.id = id;
        this.spawn = spawn;
        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(plugin, this);
        this.game = new Game(this);
    }

    /* Game Methods */
    public void start() {
        state = GameState.INGAME;
        game.start();
    }

    public void reset(boolean keepPlayers) {
        if (!keepPlayers) {
            for (UUID uuid : players) {
                Bukkit.getPlayer(uuid).teleport(ConfigManager.getLobbySpawn());
            }
            players.clear();
        }

        state = GameState.RECRUITING;
        countdown.cancel();
        players.clear();
        countdown = new Countdown(plugin, this);
        game = new Game(this);
    }

    /* General Methods */
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

        if (state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers()) {
            countdown.start();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("", "", 0, 0, 0);

        if (state.equals(GameState.STARTING) && players.size() < ConfigManager.getRequiredPlayers()) {
            countdown.cancel();
        }

        if (state.equals(GameState.INGAME) && players.size() < 2) {
            reset(false);
        }


    }

    public void setState(GameState state) { this.state = state; }

    /* Getters */
    public int getId() { return id; }
    public List<UUID> getPlayers() { return players; }
    public GameState getState() { return state; }
    public Game getGame() { return game; }

}
