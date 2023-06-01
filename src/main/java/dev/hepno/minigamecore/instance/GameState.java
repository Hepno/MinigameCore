package dev.hepno.minigamecore.instance;

public enum GameState {

    /*
    This is just a very simple enum to keep track of the game state.
    Each Arena has a GameState attached to it.

    RECRUITING: The game is waiting for players to join.
    STARTING: The game has enough players to start, and is counting down.
    INGAME: The game is in progress.
     */

    RECRUITING,
    STARTING,
    INGAME;

}
