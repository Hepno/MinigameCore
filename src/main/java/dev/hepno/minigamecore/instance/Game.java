package dev.hepno.minigamecore.instance;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points; //Not all games will have points, but this is just an example

    public Game(Arena arena) {
        this.arena = arena;
    }

    public void start() {
        arena.setState(GameState.INGAME);
        arena.broadcastTitle("Game Started!", "");

        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }

    }

    public void addPoint(UUID uuid) {
        points.put(uuid, points.get(uuid) + 1);
        if (points.get(uuid) >= 10) {
            arena.broadcastTitle("Game Over!", "");
            // TODO: Reset arena
        }
    }

    public int getPoints(UUID uuid) {
        return points.get(uuid);
    }

}
