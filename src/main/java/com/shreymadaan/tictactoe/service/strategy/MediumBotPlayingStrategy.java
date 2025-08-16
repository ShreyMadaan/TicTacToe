package com.shreymadaan.tictactoe.service.strategy;

import com.shreymadaan.tictactoe.model.*;
import com.shreymadaan.tictactoe.model.constants.CellState;

import java.util.List;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move executeMove(Player player, Game game){
        Board board = game.getBoard();
        // 1) Try to win in one move
        Move winningMove = findWinningMove(player, game);
        if (winningMove != null) return winningMove;
        // 2) Try to block opponent's winning move
        Player opponent = getOpponent(player, game);
        Move blockingMove = findBlockingMove(player, opponent, game);
        if (blockingMove != null) return blockingMove;
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

    private Move findWinningMove(Player player, Game game){
        Board board = game.getBoard();
        WinnerCheckStrategy row = new RowWinningStrategy();
        WinnerCheckStrategy col = new ColumnWinningStrategy();
        WinnerCheckStrategy diag = new DiagnolWinningStrategy();
        for(List<Cell> cells : board.getCells()){
            for(Cell cell : cells){
                if(cell.getCellState().equals(com.shreymadaan.tictactoe.model.constants.CellState.EMPTY)){
                    // simulate placing
                    cell.setCellState(com.shreymadaan.tictactoe.model.constants.CellState.FULL);
                    cell.setPlayer(player);
                    Move simulated = new Move(cell, player);
                    Player winner = firstNonNull(row.checkWinner(board, simulated),
                            col.checkWinner(board, simulated),
                            diag.checkWinner(board, simulated));
                    // revert
                    cell.setCellState(com.shreymadaan.tictactoe.model.constants.CellState.EMPTY);
                    cell.setPlayer(null);
                    if (winner != null && winner == player){
                        // actually play here
                        cell.setCellState(com.shreymadaan.tictactoe.model.constants.CellState.FULL);
                        cell.setPlayer(player);
                        Move move = new Move(cell, player);
                        game.getMoves().add(move);
                        game.getBoardState().add(board.clone());
                        return move;
                    }
                }
            }
        }
        return null;
    }

    private Move findBlockingMove(Player me, Player opponent, Game game){
        Board board = game.getBoard();
        WinnerCheckStrategy row = new RowWinningStrategy();
        WinnerCheckStrategy col = new ColumnWinningStrategy();
        WinnerCheckStrategy diag = new DiagnolWinningStrategy();
        for(List<Cell> cells : board.getCells()){
            for(Cell cell : cells){
                if(cell.getCellState().equals(com.shreymadaan.tictactoe.model.constants.CellState.EMPTY)){
                    // simulate opponent placing here
                    cell.setCellState(com.shreymadaan.tictactoe.model.constants.CellState.FULL);
                    cell.setPlayer(opponent);
                    Move simulated = new Move(cell, opponent);
                    Player winner = firstNonNull(row.checkWinner(board, simulated),
                            col.checkWinner(board, simulated),
                            diag.checkWinner(board, simulated));
                    // revert
                    cell.setCellState(com.shreymadaan.tictactoe.model.constants.CellState.EMPTY);
                    cell.setPlayer(null);
                    if (winner != null && winner == opponent){
                        // block by playing here
                        cell.setCellState(com.shreymadaan.tictactoe.model.constants.CellState.FULL);
                        cell.setPlayer(me);
                        Move move = new Move(cell, me);
                        game.getMoves().add(move);
                        game.getBoardState().add(board.clone());
                        return move;
                    }
                }
            }
        }
        return null;
    }

    private Player firstNonNull(Player... players){
        for(Player p : players){
            if(p != null) return p;
        }
        return null;
    }

    private Player getOpponent(Player player, Game game){
        for(Player p : game.getPlayers()){
            if(p != player) return p;
        }
        return null;
    }
}
