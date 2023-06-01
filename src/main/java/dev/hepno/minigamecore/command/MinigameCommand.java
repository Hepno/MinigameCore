package dev.hepno.minigamecore.command;

import dev.hepno.minigamecore.MinigameCore;
import dev.hepno.minigamecore.abstracted.Command;
import dev.hepno.minigamecore.instance.Arena;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MinigameCommand extends Command {

    private final MinigameCore plugin;

    public MinigameCommand(String command, String permission, String[] aliases, String description, MinigameCore plugin) {
        super(command, permission, aliases, description);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) return;

        if (args.length == 0) {
            player.sendMessage("§cUsage: /minigame <join|leave|list>");
            return;
        }

        switch (args[0]) {
            case "join" -> {
                if (args.length != 2) {
                    player.sendMessage(ChatColor.RED + "Usage: /minigame join <arena>");
                    return;
                }

                if (plugin.getArenaManager().getArena(player) != null) {
                    player.sendMessage(ChatColor.RED + "You are already in an arena!");
                    return;
                }

                Arena arena = plugin.getArenaManager().getArena(Integer.parseInt(args[1]));
                if (arena == null) {
                    player.sendMessage(ChatColor.RED + "That arena does not exist!");
                    return;
                }

                if (arena.getState() != arena.getState().RECRUITING && arena.getState() != arena.getState().STARTING) {
                    player.sendMessage(ChatColor.RED + "That arena is mid-game!");
                    return;
                }

                arena.addPlayer(player);
                player.sendMessage(ChatColor.GREEN + "You have joined the arena!");


            }

            case "leave" -> {
                if (args.length != 1) {
                    player.sendMessage(ChatColor.RED + "Usage: /minigame leave");
                    return;
                }

                Arena arena = plugin.getArenaManager().getArena(player);
                if (arena == null) {
                    player.sendMessage(ChatColor.RED + "You are not in an arena!");
                    return;
                }

                arena.removePlayer(player);
                player.sendMessage(ChatColor.GREEN + "You have left the arena!");

                // Code
            }

            case "list" -> {
                if (args.length != 1) {
                    player.sendMessage(ChatColor.RED + "Usage: /minigame list");
                    return;
                }

                player.sendMessage(ChatColor.GOLD + "Arenas: ");
                for (Arena arena : plugin.getArenaManager().getArenas()) {
                    player.sendMessage(ChatColor.GRAY + " - " + arena.getId() + "(" + arena.getState().name() + ")");
                }

                // Code
            }

            default -> player.sendMessage(ChatColor.RED + "Usage: /minigame <join|leave|list>");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
