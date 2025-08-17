package com.shreymadaan.tictactoe.service;

import com.shreymadaan.tictactoe.model.Board;
import com.shreymadaan.tictactoe.model.Move;
import com.shreymadaan.tictactoe.model.constants.GameState;

public interface GameOutcomeEvaluator {
    GameState evaluate(Board board, Move lastMove);
}
