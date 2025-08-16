package com.shreymadaan.tictactoe.service;

import com.shreymadaan.tictactoe.exception.InvalidCellException;
import com.shreymadaan.tictactoe.model.*;
import com.shreymadaan.tictactoe.model.constants.BotDifficultyLevel;
import com.shreymadaan.tictactoe.model.constants.CellState;
import com.shreymadaan.tictactoe.service.strategy.BotPlayingStrategy;
import com.shreymadaan.tictactoe.service.strategy.BotPlayingStrategyFactory;

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

    public Move executeMove(Bot bot, Game game){
        BotDifficultyLevel botDifficultyLevel = bot.getDifficultyLevel();
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
        return botPlayingStrategy.executeMove(bot, game);
    }
}
