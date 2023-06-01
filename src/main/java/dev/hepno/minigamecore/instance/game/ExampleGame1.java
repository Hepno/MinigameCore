package dev.hepno.minigamecore.instance.game;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.instance.Arena;
import dev.hepno.minigamecore.abstracted.Game;
import dev.hepno.minigamecore.instance.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.UUID;

public class ExampleGame1 extends Game {

    /*
    This class is an Example Game. It is a bare-bones example of a game.
    This game would not be fun to play at all, but it gives an example of how to use the Game class.

    To make a game, you must extend the Game class, and implement the onStart() method.
    The onStart() method is called when the game starts, in case that wasn't obvious.

    Games have Listeners built in, so you can register events in the game class.
    They are automatically registered and unregistered when the game starts and ends.

    NOTE: When creating a game, please add it to the Switch statement in the Arena class.
     */

    private HashMap<UUID, Integer> points; //Not all games will have points, but this is just an example

    public ExampleGame1(MinigameCore plugin, Arena arena) {
        super(plugin, arena);
        points = new HashMap<>();
    }

    /* REQUIRED METHODS. DO NOT REMOVE */
    @Override
    public void onStart() {
        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }
    }

    /* SETTERS */
    public void addPoint(UUID uuid) {
        points.put(uuid, points.get(uuid) + 1);
        if (points.get(uuid) >= 10) {
            arena.broadcastTitle("Game Over!", "");
            // TODO: Reset arena
        }
    }

    /* GETTERS */
    public int getPoints(UUID uuid) {
        return points.get(uuid);
    }

    /* EVENT HANDLERS - REGISTERS AUTOMATICALLY */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (arena == null) return;
        if (arena.getState() != GameState.INGAME) return;
        addPoint(event.getPlayer().getUniqueId());
    }


}
