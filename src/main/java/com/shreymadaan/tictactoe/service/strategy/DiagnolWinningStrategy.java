package com.shreymadaan.tictactoe.service.strategy;

import com.shreymadaan.tictactoe.model.Board;
import com.shreymadaan.tictactoe.model.Cell;
import com.shreymadaan.tictactoe.model.Move;
import com.shreymadaan.tictactoe.model.Player;
import com.shreymadaan.tictactoe.model.constants.CellState;

public class DiagnolWinningStrategy implements WinnerCheckStrategy {
    @Override
    public Player checkWinner(Board board, Move currentMove) {
        if (currentMove == null || currentMove.getCell() == null) return null;
        Player player = currentMove.getPlayer();
        if (player == null) return null;
        int row = currentMove.getCell().getRow();
        int col = currentMove.getCell().getCol();
        int size = board.getSize();
        boolean onMain = (row == col);
        boolean onAnti = (row + col == size - 1);
        boolean mainWin = true;
        boolean antiWin = true;
        if (onMain) {
            for (int i = 0; i < size; i++) {
                Cell cell = board.getCells().get(i).get(i);
                if (cell.getCellState() != CellState.FULL || cell.getPlayer() != player) {
                    mainWin = false; break;
                }
            }
            if (mainWin) return player;
        }
        if (onAnti) {
            for (int i = 0; i < size; i++) {
                Cell cell = board.getCells().get(i).get(size - 1 - i);
                if (cell.getCellState() != CellState.FULL || cell.getPlayer() != player) {
                    antiWin = false; break;
                }
            }
            if (antiWin) return player;
        }
        return null;
    }
}
