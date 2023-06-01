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

    I do intend on making this a maintained project, with Modules to make adding Teams,
    Scoreboards, Bungee support, etc easier, but right now I don't have the time.

    If you want to contribute, feel free to make a pull request. I'll eventually make a website
    to build your own version with the modules you want, but that's a long way off.



    NOTE: When creating a game, please add it to the Switch statement in the Arena class, both
    in the constructor and in the reset() method, otherwise it won't work.
     */

    private HashMap<UUID, Integer> points; //Not all games will have points, but this is just an example

    public ExampleGame1(MinigameCore plugin, Arena arena) {
        super(plugin, arena);
        System.out.println("DEBUG 5");
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
        System.out.println("DEBUG 6");
        points.put(uuid, points.get(uuid) + 1);
        if (points.get(uuid) >= 10) {
            arena.broadcastTitle("Game Over!", "");
            arena.reset(false);
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
