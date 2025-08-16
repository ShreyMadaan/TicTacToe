package com.shreymadaan.tictactoe.service.strategy;

import com.shreymadaan.tictactoe.model.Board;
import com.shreymadaan.tictactoe.model.Cell;
import com.shreymadaan.tictactoe.model.Move;
import com.shreymadaan.tictactoe.model.Player;
import com.shreymadaan.tictactoe.model.constants.CellState;

public class ColumnWinningStrategy implements WinnerCheckStrategy {
    @Override
    public Player checkWinner(Board board, Move currentMove) {
        if (currentMove == null || currentMove.getCell() == null) return null;
        Player player = currentMove.getPlayer();
        if (player == null) return null;
        int col = currentMove.getCell().getCol();
        int size = board.getSize();
        for (int r = 0; r < size; r++) {
            Cell cell = board.getCells().get(r).get(col);
            if (cell.getCellState() != CellState.FULL || cell.getPlayer() != player) {
                return null;
            }
        }
        return player;
    }
}
