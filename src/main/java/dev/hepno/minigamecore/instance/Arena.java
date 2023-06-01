package dev.hepno.minigamecore.instance;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.abstracted.Game;
import dev.hepno.minigamecore.instance.game.ExampleGame1;
import dev.hepno.minigamecore.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    /*
    Welcome to the Arena class! Whenever a new instance of a Game starts, an Arena is created.
    You can have one Game per Arena, but you can have multiple Arenas per game.

    This class is instantiated in the ArenaManager class, and is used to store information about the Arena.
    It also contains methods that are used to interact with the Arena.
     */

    private MinigameCore plugin;

    private int id;
    private Location spawn;
    private String gameName;

    private List<UUID> players = new ArrayList<>();
    private GameState state;
    private Countdown countdown;
    private Game game;

    public Arena(MinigameCore plugin, int id, Location spawn, String game) {
        this.plugin = plugin;
        this.id = id;
        this.spawn = spawn;
        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(plugin, this);
        this.gameName = game;

        /* IF YOU ARE FORKING THIS, ADD YOUR GAME HERE, AS WELL AS IN THE RESET METHOD */
        switch (game) {
            case "ExampleGame1" -> {
                this.game = new ExampleGame1(plugin, this);
            }
            case "ExampleGame2" -> {
                this.game = new dev.hepno.minigamecore.instance.game.ExampleGame2(plugin, this);
            }
        }

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
        game.removeEvents();

        switch (gameName) {
            case "ExampleGame1" -> {
                this.game = new ExampleGame1(plugin, this);
            }
            case "ExampleGame2" -> {
                this.game = new dev.hepno.minigamecore.instance.game.ExampleGame2(plugin, this);
            }
        }
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

        if (state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers(String.valueOf(id))) {
            countdown.start();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("", "", 0, 0, 0);

        if (state.equals(GameState.STARTING) && players.size() < ConfigManager.getRequiredPlayers(String.valueOf(id))) {
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
