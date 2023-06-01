package dev.hepno.minigamecore.instance.game;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.instance.Arena;
import dev.hepno.minigamecore.abstracted.Game;
import dev.hepno.minigamecore.instance.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ExampleGame2 extends Game {

    /*
    This class is a more bare-bones example of a game. For a more in-depth example, see ExampleGame1.java
    This class exists only to show that you can have multiple games in a single plugin.
     */

    public ExampleGame2(MinigameCore plugin, Arena arena) {
        super(plugin, arena);
    }

    @Override
    public void onStart() {
        for (UUID uuid : arena.getPlayers()) {
            Bukkit.getPlayer(uuid).getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
        }
    }

    public void setWinner(UUID uuid) {
        // In this example game, points don't matter, first player to get a kill wins
        arena.broadcastTitle("Game Over!", "");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (arena == null) return;
        if (arena.getState() != GameState.INGAME) return;
        setWinner(event.getEntity().getKiller().getUniqueId());
    }

}
