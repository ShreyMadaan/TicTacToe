package com.shreymadaan.tictactoe.service.strategy;

import com.shreymadaan.tictactoe.model.Board;
import com.shreymadaan.tictactoe.model.Move;
import com.shreymadaan.tictactoe.model.Player;

public interface WinnerCheckStrategy {
    Player checkWinner(Board board, Move currentMove);
}
