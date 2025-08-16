package com.shreymadaan.tictactoe.service.strategy;

import com.shreymadaan.tictactoe.model.Game;
import com.shreymadaan.tictactoe.model.Move;
import com.shreymadaan.tictactoe.model.Player;

public interface BotPlayingStrategy {
    Move executeMove(Player player, Game game);
}
