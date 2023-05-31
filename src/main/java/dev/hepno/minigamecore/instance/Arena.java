package dev.hepno.minigamecore.instance;

import org.bukkit.Location;

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

    

}
