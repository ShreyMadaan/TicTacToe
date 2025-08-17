package com.shreymadaan.tictactoe.service;

import com.shreymadaan.tictactoe.model.Board;
import com.shreymadaan.tictactoe.model.Cell;
import com.shreymadaan.tictactoe.model.Move;
import com.shreymadaan.tictactoe.model.Player;
import com.shreymadaan.tictactoe.model.constants.CellState;
import com.shreymadaan.tictactoe.model.constants.GameState;
import com.shreymadaan.tictactoe.service.strategy.WinnerCheckStrategy;

import java.util.List;

public class DefaultGameOutcomeEvaluator implements GameOutcomeEvaluator {
    private final List<WinnerCheckStrategy> winnerStrategies;

    public DefaultGameOutcomeEvaluator(List<WinnerCheckStrategy> winnerStrategies) {
        this.winnerStrategies = winnerStrategies;
    }

    @Override
    public GameState evaluate(Board board, Move lastMove) {
        if (board == null) return GameState.YET_TO_START;
        Player winner = null;
        if (winnerStrategies != null) {
            for (WinnerCheckStrategy strategy : winnerStrategies) {
                if (strategy == null) continue;
                Player w = strategy.checkWinner(board, lastMove);
                if (w != null) { winner = w; break; }
            }
        }
        if (winner != null) return GameState.WON;
        // Draw or in-progress check
        boolean anyEmpty = false;
        for (List<Cell> row : board.getCells()) {
            for (Cell cell : row) {
                if (cell.getCellState() == CellState.EMPTY) { anyEmpty = true; break; }
            }
            if (anyEmpty) break;
        }
        return anyEmpty ? GameState.IN_PROGRESS : GameState.DRAW;
    }
}
