package com.shreymadaan.tictactoe.service.strategy;

import com.shreymadaan.tictactoe.model.*;
import com.shreymadaan.tictactoe.model.constants.CellState;

import java.util.Arrays;
import java.util.List;

public class HardBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move executeMove(Player player, Game game){
        // Try winning or blocking like Medium first
        MediumBotPlayingStrategy medium = new MediumBotPlayingStrategy();
        Move m = medium.executeMove(player, game);
        if (m != null) return m;

        Board board = game.getBoard();
        int size = board.getSize();
        // 1) Take center if available
        int center = size/2;
        if (size % 2 == 1) {
            Cell centerCell = board.getCells().get(center).get(center);
            if (centerCell.getCellState() == CellState.EMPTY) {
                centerCell.setCellState(CellState.FULL);
                centerCell.setPlayer(player);
                Move move = new Move(centerCell, player);
                game.getMoves().add(move);
                game.getBoardState().add(board.clone());
                return move;
            }
        }
        // 2) Take a corner if available
        List<int[]> corners = Arrays.asList(
                new int[]{0,0}, new int[]{0,size-1}, new int[]{size-1,0}, new int[]{size-1,size-1}
        );
        for (int[] rc : corners){
            int r = rc[0], c = rc[1];
            if (r >= 0 && r < size && c >=0 && c < size){
                Cell cell = board.getCells().get(r).get(c);
                if (cell.getCellState() == CellState.EMPTY){
                    cell.setCellState(CellState.FULL);
                    cell.setPlayer(player);
                    Move move = new Move(cell, player);
                    game.getMoves().add(move);
                    game.getBoardState().add(board.clone());
                    return move;
                }
            }
        }
        // 3) Else play first available
        for(List<Cell> cells : board.getCells()){
            for(Cell cell : cells){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    cell.setCellState(CellState.FULL);
                    cell.setPlayer(player);
                    Move move = new Move(cell, player);
                    game.getMoves().add(move);
                    game.getBoardState().add(board.clone());
                    return move;
                }
            }
        }
        return null;
    }
}
