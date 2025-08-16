package com.shreymadaan.tictactoe.service;

import com.shreymadaan.tictactoe.exception.InvalidCellException;
import com.shreymadaan.tictactoe.model.Cell;
import com.shreymadaan.tictactoe.model.Game;
import com.shreymadaan.tictactoe.model.Move;
import com.shreymadaan.tictactoe.model.Player;
import com.shreymadaan.tictactoe.model.constants.CellState;

public class GameService {
    public Move executeMove(Player player, Game game, int row, int col){
        Cell cell = game.getBoard().getCells().get(row).get(col);
        if(!cell.getCellState().equals(CellState.EMPTY)){
            throw new InvalidCellException("Cell is already occupied");
        }
        cell.setCellState(CellState.FULL);
        cell.setPlayer(player);
        Move move = new Move(cell, player);
        game.getMoves().add(move);
        game.getBoardState().add(game.getBoard().clone());
        return move;
    }
}
