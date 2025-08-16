package com.shreymadaan.tictactoe.service.strategy;

import com.shreymadaan.tictactoe.model.*;
import com.shreymadaan.tictactoe.model.constants.CellState;

import java.util.List;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{
    Move executeMove(Player player, Game game){
        Board board = game.getBoard();
        Move move = null;
        for(List<Cell> cells : board.getCells()){
            for(Cell cell : cells){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    cell.setCellState(CellState.FULL);
                    cell.setPlayer(player);
                    Move move = new Move(cell, player);
                    game.getMoves().add(move);
                    game.getBoardState().add(board.clone());
                }
            }
        }
        return move;
//        TODO: look for all the empty cells and choose randomly
    }
}
